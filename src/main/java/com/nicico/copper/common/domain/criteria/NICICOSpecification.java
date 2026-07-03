package com.nicico.copper.common.domain.criteria;

import com.nicico.copper.common.dto.search.EOperator;
import com.nicico.copper.common.dto.search.SearchDTO;
import com.nicico.copper.common.enumeration.date.EUniCalendar;
import com.nicico.copper.common.enumeration.date.EUniDateField;
import com.nicico.copper.common.util.NICICOBaseContext;
import com.nicico.copper.common.util.StringUtil;
import com.nicico.copper.common.util.date.DateUtil;
import com.nicico.copper.common.util.date.UniDate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import jakarta.persistence.criteria.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

public class NICICOSpecification<T> implements Specification<T> {
	private NICICOBaseContext currentContext = new NICICOBaseContext();

	private final SearchDTO.CriteriaRq criteria;
	private final List<SearchDTO.SortByRq> sortByList;
	private final Boolean distinct;
	private final List<String> selectFields;

	// ------------------------------
	private NICICOSpecification(SearchDTO.CriteriaRq criteria, List<SearchDTO.SortByRq> sortByList, Boolean distinct, List<String> selectFields) {
		this.criteria = criteria;
		this.sortByList = sortByList;
		this.distinct = distinct;
		this.selectFields = selectFields;
	}

	// ------------------------------

	public static <T> NICICOSpecification<T> of(SearchDTO.CriteriaRq criteria) {
		return new NICICOSpecification<>(criteria, null, false, null);
	}

	public static <T> NICICOSpecification<T> of(SearchDTO.CriteriaRq criteria, List<SearchDTO.SortByRq> sortByList) {
		return new NICICOSpecification<>(criteria, sortByList, false, null);
	}

	public static <T> NICICOSpecification<T> of(SearchDTO.SearchRq request) {
		// We assume request.getSelectFields() is added to your SearchRq DTO
		return new NICICOSpecification<>(request.getCriteria(), request.getSortBy(), request.getDistinct(), request.getSelectFields());
	}

	// ------------------------------

	private static Join<?, ?> getOrCreateJoin(From<?, ?> from, String attribute) {
//		for (Join<?, ?> join : from.getJoins()) {
//			boolean sameName = join.getAttribute().getName().equals(attribute);
//			if (sameName && join.getJoinType().equals(JoinType.LEFT)) {
//				return join;
//			}
//		}

		return from.join(attribute, JoinType.LEFT);
	}

	// ------------------------------

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Predicate predicate = null;



		if (criteria != null) {
			predicate = createPredicate(root, builder, criteria);
		}

		if (sortByList != null && !sortByList.isEmpty()) {
			final List<Order> orderList = sortByList.stream().map(order -> {
				final Path<Object> path = findPath(root, order.getFieldName());
				return order.getDescendingSafe() ? builder.desc(path) : builder.asc(path);
			}).collect(Collectors.toList());
			query.orderBy(orderList);
		}

		query.distinct(distinct);


		return predicate;
	}

	private Predicate createPredicate(Root<T> root, CriteriaBuilder builder, SearchDTO.CriteriaRq criteria) {
		Predicate predicate = null;

		switch (criteria.getOperator()) {
			case and:
				List<Predicate> andPredicates = new ArrayList<>();
				if (!CollectionUtils.isEmpty(criteria.getCriteria())) {
					andPredicates = criteria.getCriteria().stream().map(subCriteria ->
							createPredicate(root, builder, subCriteria)).filter(subPredicate ->
							subPredicate != null).collect(Collectors.toList());
				}

				if (andPredicates.size() != 0)
					predicate = builder.and(andPredicates.toArray(new Predicate[0]));
				break;
			case or:
				List<Predicate> orPredicates = new ArrayList<>();
				if (!CollectionUtils.isEmpty(criteria.getCriteria())) {
					orPredicates = criteria.getCriteria().stream().map(subCriteria ->
							createPredicate(root, builder, subCriteria)).filter(subPredicate ->
							subPredicate != null).collect(Collectors.toList());
				}

				if (orPredicates.size() != 0)
					predicate = builder.or(orPredicates.toArray(new Predicate[0]));
				break;
			case not:
				break;
			default:
				return createComparisonPredicate(root, builder, criteria);
		}

		return predicate;
	}

	private <P> Path<P> findPath(Root<T> root, String property) {
		final String[] split = property.split("\\.");

		Path path = root;
		for (String part : split) {
			if (Collection.class.isAssignableFrom(path.get(part).getJavaType())) {
				path = getOrCreateJoin(root, part);
			} else {
				path = path.get(part);
			}
		}

		if (path == null) {
			throw new RuntimeException("Invalid property: " + property + ", " + split[0]);
		}

		return path;
	}

	private Predicate createComparisonPredicate(Root<T> root, CriteriaBuilder builder, SearchDTO.CriteriaRq criteria) {
		Predicate predicate = null;

		if (!(criteria.getOperator().equals(EOperator.isBlank) || criteria.getOperator().equals(EOperator.notBlank) || criteria.getOperator().equals(EOperator.isNull) || criteria.getOperator().equals(EOperator.notNull)) &&
				(criteria.getValue() == null || criteria.getValue().size() == 0))
			return null;

		final String fieldName = criteria.getFieldName();

		final Path findPath = findPath(root, fieldName);
		final Path<Comparable> path = findPath;
		final Path<String> path4str = findPath;

		if (path == null) {
			throw new RuntimeException("Invalid property: " + fieldName);
		}

		switch (criteria.getOperator()) {
			case notEqualField:
				if (criteria.getValue() != null && criteria.getValue().get(0) != null)
					predicate = builder.notEqual(root.get(criteria.getFieldName()), root.get((String) criteria.getValue().get(0)));
				break;
			case equalsField:
				if (criteria.getValue() != null && criteria.getValue().get(0) != null)
					predicate = builder.equal(root.get(criteria.getFieldName()), root.get((String) criteria.getValue().get(0)));
				break;
			case iEquals:
				if (path.getJavaType().equals(String.class))
					predicate = builder.lower(path4str).in(convertAsList(criteria, path, true));
				else
					predicate = path.in(convertAsList(criteria, path, false));
				break;
			case equals:
				predicate = path.in(convertAsList(criteria, path, false));
				break;

			case iNotEqual:
				if (path.getJavaType().equals(String.class))
					predicate = builder.not(builder.lower(path4str).in(convertAsList(criteria, path, true)));
				else
					predicate = builder.not(path.in(convertAsList(criteria, path, false)));
				break;
			case notEqual:
				predicate = builder.not(path.in(convertAsList(criteria, path, false)));
				break;

			case lessThan:
				predicate = builder.lessThan(path, convertAsSingle(criteria, path, false));
				break;

			case lessOrEqual:
				predicate = builder.lessThanOrEqualTo(path, convertAsSingle(criteria, path, false));
				break;

			case greaterThan:
				predicate = builder.greaterThan(path, convertAsSingle(criteria, path, false));
				break;

			case greaterOrEqual:
				predicate = builder.greaterThanOrEqualTo(path, convertAsSingle(criteria, path, false));
				break;

			case iStartsWith:
				predicate = builder.like(builder.lower(path4str), convertAsSingle(criteria, path4str, true) + "%");
				break;
			case startsWith:
				predicate = builder.like(path4str, convertAsSingle(criteria, path4str, false) + "%");
				break;

			case iNotStartsWith:
				predicate = builder.notLike(builder.lower(path4str), convertAsSingle(criteria, path4str, true) + "%");
				break;
			case notStartsWith:
				predicate = builder.notLike(path4str, convertAsSingle(criteria, path4str, false) + "%");
				break;

			case iEndsWith:
				predicate = builder.like(builder.lower(path4str), "%" + convertAsSingle(criteria, path4str, true));
				break;
			case endsWith:
				predicate = builder.like(path4str, "%" + convertAsSingle(criteria, path4str, false));
				break;

			case iNotEndsWith:
				predicate = builder.notLike(builder.lower(path4str), "%" + convertAsSingle(criteria, path4str, true));
				break;
			case notEndsWith:
				predicate = builder.notLike(path4str, "%" + convertAsSingle(criteria, path4str, false));
				break;
			case regexp:
				Pattern regexPattern = Pattern.compile(criteria.getRegex());
				Expression<String> patternExpression = builder.literal(regexPattern.pattern());
				predicate = builder.like(builder.function("regexp_like", String.class, path4str, patternExpression), convertAsSingle(criteria, path4str, true).toString());
				break;

			case iContains:
				if (path.getJavaType().equals(Date.class)) {
					final DateRange dateRange = criteria.getValue() != null && !criteria.getValue().isEmpty() ? getDateRange((String) criteria.getValue().get(0)) : new DateRange();
					predicate = builder.and(builder.greaterThanOrEqualTo(path, (Comparable) dateRange.fromDate), builder.lessThan(path, (Comparable) dateRange.toDate));
				} else if(Number.class.isAssignableFrom(findPath.getJavaType())) {
					predicate = builder.like(builder.function("to_char", String.class, path4str), "%" + convertAsSingle(criteria, path4str, true) + "%");
				} else {
					predicate = builder.like(builder.lower(path4str), "%" + convertAsSingle(criteria, path4str, true) + "%");
				}
				break;
			case contains:
				if (path.getJavaType().equals(Date.class)) {
					final DateRange dateRange = criteria.getValue() != null && !criteria.getValue().isEmpty() ? getDateRange((String) criteria.getValue().get(0)) : new DateRange();
					predicate = builder.and(builder.greaterThanOrEqualTo(path, (Comparable) dateRange.fromDate), builder.lessThan(path, (Comparable) dateRange.toDate));
				} else if(Number.class.isAssignableFrom(findPath.getJavaType())) {
					predicate = builder.like(builder.function("to_char", String.class, path4str), "%" + convertAsSingle(criteria, path4str, true) + "%");
				} else {
					predicate = builder.like(path4str, "%" + convertAsSingle(criteria, path4str, false) + "%");
				}
				break;

			case iNotContains:
				predicate = builder.notLike(builder.lower(path4str), "%" + convertAsSingle(criteria, path4str, true) + "%");
				break;
			case notContains:
				predicate = builder.notLike(path4str, "%" + convertAsSingle(criteria, path4str, false) + "%");
				break;

			case iBetween:
			case between:
				predicate = builder.and(builder.greaterThan(path, convertAsSingle(criteria, path, 0, false)), builder.lessThan(path, convertAsSingle(criteria, path, 1, false)));
				break;

			case iBetweenInclusive:
			case betweenInclusive:
				predicate = builder.between(path, convertAsSingle(criteria, path, 0, false), convertAsSingle(criteria, path, 1, false));
				break;

			case isBlank:
				predicate = builder.equal(path4str, "");
				break;

			case notBlank:
				predicate = builder.notEqual(path4str, "");
				break;

			case isNull:
				predicate = builder.isNull(path);
				break;

			case notNull:
				predicate = builder.isNotNull(path);
				break;

			case inSet:
				predicate = path.in(convertAsList(criteria, path, false));
				break;

			case notInSet:
				predicate = path.in(convertAsList(criteria, path, false)).not();
				break;

			case replace:
				Expression<String> replacedSlash = builder.function("replace", String.class, path4str, builder.literal("/"), builder.literal(""));
				predicate = builder.like(replacedSlash, ((String) convertAsSingle(criteria, path4str, false)).replace("/", "") + "%");
				break;

			default:
				throw new RuntimeException("Invalid comparison Operator: " + criteria.getOperator());
		}

		return predicate;
	}

	private Comparable convertAsSingle(SearchDTO.CriteriaRq criteria, Path<? extends Comparable> path, Boolean caseInsensitive) {
		return convertAsSingle(criteria, path, 0, caseInsensitive);
	}

	private Comparable convertAsSingle(SearchDTO.CriteriaRq criteria, Path<? extends Comparable> path, int index, Boolean caseInsensitive) {
		if (criteria.getValue() != null && criteria.getValue().size() > index) {
			return convert(path.getJavaType(), criteria.getValue().get(index), caseInsensitive);
		}

		return null;
	}

	private List<Comparable> convertAsList(SearchDTO.CriteriaRq criteria, Path<? extends Comparable> path, Boolean caseInsensitive) {
		if (criteria.getValue() != null && !criteria.getValue().isEmpty()) {
			return criteria.getValue().stream().map(v -> convert(path.getJavaType(), v, caseInsensitive)).collect(Collectors.toList());
		}

		return null;
	}

	private Comparable convert(Class<?> cls, Object value, Boolean caseInsensitive) {
		if (cls.equals(String.class)) {
			final String str = StringUtil.replaceSpecialArabic((String) value);
			return caseInsensitive ? str.toLowerCase() : str;
		}

		if (cls.equals(BigDecimal.class)) {
			return new BigDecimal(String.valueOf(value));
		}

		if (cls.equals(LocalDate.class)) {
			return LocalDate.parse(DateUtil.convertKhToMi1((String) value));
		}else if (cls.equals(LocalDateTime.class)){
			return LocalDateTime.parse(value.toString());
		}

		if (cls.isEnum()) {
			for (Object constant : cls.getEnumConstants()) {
				if (constant.toString().equals(value)) {
					return (Comparable) constant;
				}
			}
		}

		if (cls.equals(Date.class) && value instanceof Number) {
			final Date date = new Date();
			date.setTime(Long.parseLong(value.toString()));
			return date;
		}

		if (cls.equals(Timestamp.class) && value instanceof Number) {
			return Timestamp.from(Instant.ofEpochMilli(Long.parseLong(value.toString())));
		}

		return (Comparable) value;
	}

	private class DateRange {
		private Date fromDate;
		private Date toDate;
	}

	private DateRange getDateRange(String dateTimeStr) {
		currentContext.setCalendar("Persian").setTimeZoneId("Asia/Tehran");

		final Pattern dateTimePattern = Pattern.compile("(?<year>\\d{4})([/](?<month>\\d{1,2}))?([/](?<day>\\d{1,2}))?([ ](?<hour>\\d{1,2})([:](?<minute>\\d{1,2}))?([:](?<second>\\d{1,2}))?)?");

		EUniCalendar calendar = EUniCalendar.Gregorian;
		switch (currentContext.getCalendarSafely()) {
			case Persian:
				calendar = EUniCalendar.Persian;
				break;
			case Islamic:
				calendar = EUniCalendar.Islamic;
				break;
		}

		final EUniDateField[] fields = {EUniDateField.YEAR, EUniDateField.MONTH, EUniDateField.DAY_OF_MONTH, EUniDateField.HOUR_OF_DAY, EUniDateField.MINUTE, EUniDateField.SECOND};
		final String[] names = {"year", "month", "day", "hour", "minute", "second"};

		final Integer fromDateTime[] = {1, 1, 1, 0, 0, 0};

		final DateRange dateRange = new DateRange();

		EUniCalendar finalCalendar = calendar;
		final Matcher dateTimeMatcher = dateTimePattern.matcher(dateTimeStr);

		if (dateTimeMatcher.find()) {
			Integer idx = 0;
			for (idx = 0; idx < names.length; idx++) {
				if (!StringUtils.isEmpty(dateTimeMatcher.group(names[idx])))
					fromDateTime[idx] = Integer.valueOf(dateTimeMatcher.group(names[idx]));
				else
					break;
			}

			final UniDate fromUniDate = UniDate
					.of(finalCalendar, TimeZone.getTimeZone(currentContext.getTimeZoneIdSafely()))
					.setDate(fromDateTime[0], fromDateTime[1], fromDateTime[2])
					.setTime(fromDateTime[3], fromDateTime[4], fromDateTime[5]);

			dateRange.fromDate = fromUniDate.toDate();

			final UniDate toUniDate = fromUniDate.update(fields[idx - 1], 1);
			dateRange.toDate = toUniDate.toDate();
		}

		return dateRange;
	}
}

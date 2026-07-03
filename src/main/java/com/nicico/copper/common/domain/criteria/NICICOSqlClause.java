package com.nicico.copper.common.domain.criteria;

import com.nicico.copper.common.dto.search.SearchDTO;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NICICOSqlClause {
	private final String WHITESPACE = " ";
	private final SearchDTO.CriteriaRq criteria;
	private final List<SearchDTO.SortByRq> sortByList;

	// ------------------------------

	private NICICOSqlClause(SearchDTO.CriteriaRq criteria, List<SearchDTO.SortByRq> sortByList) {
		this.criteria = criteria;
		this.sortByList = sortByList;
	}

	// ------------------------------

	public static NICICOSqlClause of(SearchDTO.CriteriaRq criteria) {
		return new NICICOSqlClause(criteria, null);
	}

	public static NICICOSqlClause of(SearchDTO.CriteriaRq criteria, List<SearchDTO.SortByRq> sortByList) {
		return new NICICOSqlClause(criteria, sortByList);
	}

	public static NICICOSqlClause of(SearchDTO.SearchRq request) {
		if (request.getCriteria() != null)
			return new NICICOSqlClause(request.getCriteria(), request.getSortBy());
		else
			return new NICICOSqlClause(null, request.getSortBy());
	}

	// ------------------------------

	public String getWhereClause() {
		String whereClause = "";
		String sort = "";

		if (criteria != null) {
			whereClause += "WHERE " + createWhereClause(criteria);
		}

		if (sortByList != null && !sortByList.isEmpty()) {
			sort += "SORT BY " + createSort(sortByList);
		}

		return new StringBuilder()
			.append(whereClause)
			.append(WHITESPACE)
			.append(sort)
			.toString();
	}

	private String createWhereClause(SearchDTO.CriteriaRq criteria) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("(");

		switch (criteria.getOperator()) {
			case and:
				if (!CollectionUtils.isEmpty(criteria.getCriteria())) {
					stringBuilder.append(createWhereClause(criteria.getCriteria().get(0)));

					IntStream.range(1, criteria.getCriteria().size())
						.mapToObj(index -> criteria.getCriteria().get(index))
						.forEach(criteriaRq ->
							stringBuilder.append(" AND ")
								.append(createWhereClause(criteriaRq)));
				}
				break;
			case or:
				if (!CollectionUtils.isEmpty(criteria.getCriteria())) {
					stringBuilder.append(createWhereClause(criteria.getCriteria().get(0)));

					IntStream.range(1, criteria.getCriteria().size())
						.mapToObj(index -> criteria.getCriteria().get(index))
						.forEach(criteriaRq ->
							stringBuilder.append(" OR ")
								.append(createWhereClause(criteriaRq)));
				}
				break;
			case not:
				break;
			default:
				stringBuilder.append(createComparisonWhereClause(criteria));
		}

		stringBuilder.append(")");
		return stringBuilder.toString();
	}

	private String createSort(List<SearchDTO.SortByRq> sortByList) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(sortByList.get(0).getFieldName())
			.append(WHITESPACE)
			.append(sortByList.get(0).getDescendingSafe() ? "DESC" : "ASC");

		IntStream.range(1, sortByList.size())
			.mapToObj(index -> sortByList.get(index))
			.forEach(sortByRq ->
				stringBuilder.append(",")
					.append(WHITESPACE)
					.append(sortByRq.getFieldName())
					.append(WHITESPACE)
					.append(sortByRq.getDescendingSafe() ? "DESC" : "ASC"));

		return stringBuilder.toString();
	}

	private String createComparisonWhereClause(SearchDTO.CriteriaRq criteria) {
		StringBuilder stringBuilder = new StringBuilder();

		switch (criteria.getOperator()) {
			case iEquals:
			case equals:
				stringBuilder.append(criteria.getFieldName())
					.append(" IN ")
					.append(convertAsList(criteria));
				break;

			case iNotEqual:
			case notEqual:
				stringBuilder.append(criteria.getFieldName())
					.append(" NOT IN ")
					.append(convertAsList(criteria));
				break;

			case lessThan:
				stringBuilder.append(criteria.getFieldName())
					.append(" < ")
					.append(convertAsSingle(criteria));
				break;

			case lessOrEqual:
				stringBuilder.append(criteria.getFieldName())
					.append(" <= ")
					.append(convertAsSingle(criteria));
				break;

			case greaterThan:
				stringBuilder.append(criteria.getFieldName())
					.append(" > ")
					.append(convertAsSingle(criteria));
				break;

			case greaterOrEqual:
				stringBuilder.append(criteria.getFieldName())
					.append(" >= ")
					.append(convertAsSingle(criteria));
				break;

			case iStartsWith:
			case startsWith:
				stringBuilder.append(criteria.getFieldName())
					.append(" LIKE '")
					.append(convertAsSingle(criteria))
					.append("%'");
				break;

			case iNotStartsWith:
			case notStartsWith:
				stringBuilder.append(criteria.getFieldName())
					.append(" NOT LIKE '")
					.append(convertAsSingle(criteria))
					.append("%'");
				break;

			case iEndsWith:
			case endsWith:
				stringBuilder.append(criteria.getFieldName())
					.append(" LIKE '")
					.append("%")
					.append(convertAsSingle(criteria) + "'");
				break;

			case iNotEndsWith:
			case notEndsWith:
				stringBuilder.append(criteria.getFieldName())
					.append(" NOT LIKE '")
					.append("%")
					.append(convertAsSingle(criteria) + "'");
				break;

			case iContains:
			case contains:
				stringBuilder.append(criteria.getFieldName())
					.append(" LIKE ")
					.append("'%")
					.append(convertAsSingle(criteria))
					.append("%'");
				break;

			case iNotContains:
			case notContains:
				stringBuilder.append(criteria.getFieldName())
					.append(" NOT LIKE ")
					.append("'%")
					.append(convertAsSingle(criteria))
					.append("%'");
				break;

			case iBetween:
			case between:
				stringBuilder.append(criteria.getFieldName())
					.append(" > ")
					.append(convertAsSingle(criteria, 0))
					.append(" AND ")
					.append(criteria.getFieldName())
					.append(" < ")
					.append(convertAsSingle(criteria, 1));
				break;

			case iBetweenInclusive:
			case betweenInclusive:
				stringBuilder.append(criteria.getFieldName())
					.append(" BETWEEN ")
					.append(convertAsSingle(criteria, 0))
					.append(" AND ")
					.append(convertAsSingle(criteria, 1));
				break;

			case isBlank:
				stringBuilder.append(criteria.getFieldName())
					.append(" LIKE ")
					.append("''");
				break;

			case notBlank:
				stringBuilder.append(criteria.getFieldName())
					.append(" NOT LIKE ")
					.append("''");
				break;

			case isNull:
				stringBuilder.append(criteria.getFieldName())
					.append(" IS NULL");
				break;

			case notNull:
				stringBuilder.append(criteria.getFieldName())
					.append(" IS NOT NULL");
				break;

			case inSet:
				stringBuilder.append(criteria.getFieldName())
					.append(" IN ")
					.append(convertAsList(criteria));
				break;

			default:
				throw new RuntimeException("Invalid comparison Operator: " + criteria.getOperator());
		}

		return stringBuilder.toString();
	}

	private Comparable convertAsSingle(SearchDTO.CriteriaRq criteria) {
		return convertAsSingle(criteria, 0);
	}

	private Comparable convertAsSingle(SearchDTO.CriteriaRq criteria, int index) {
		if (criteria.getValue() != null && criteria.getValue().size() > index) {
			return convert(criteria.getValue().get(index));
		}

		return null;
	}

	private List<Comparable> convertAsList(SearchDTO.CriteriaRq criteria) {
		if (criteria.getValue() != null && !criteria.getValue().isEmpty()) {
			return criteria.getValue().stream().map(v -> convert(v)).collect(Collectors.toList());
		}

		return null;
	}

	private String convert(Object value) {
		return (String) value;
	}
}

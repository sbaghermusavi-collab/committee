package com.nicico.copper.common.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.*;

public class SearchDTO {

	@Getter
	@Setter
	@Accessors(chain = true)
	@ToString
	public static class SearchRq {
		public static final String FIELD_START_INDEX = "startIndex";
		public static final String FIELD_COUNT = "count";

		@NotNull
		@Min(0)
				private Integer startIndex;
		@NotNull
		@Min(1)
				private Integer count;
		private CriteriaRq criteria;
		private Object sortBy;
		private Boolean distinct = false;
		private List<String> selectFields;

		public List<SortByRq> getSortBy() {
			if (sortBy != null) {
				final List<SortByRq> sortByRqList = new ArrayList<>();

				if (sortBy instanceof Collection) {
					final List<String> fieldNames = (List<String>) sortBy;

					fieldNames.forEach(fieldName ->
						sortByRqList.add(getSortByRq(fieldName)));
				} else {
					final String fieldName = (String) sortBy;

					sortByRqList.add(getSortByRq(fieldName));
				}
				return sortByRqList;
			} else
				return null;
		}

		private SortByRq getSortByRq(String fieldName) {
			final SortByRq sortByRq = new SortByRq();

			if (fieldName.startsWith("-"))
				sortByRq.setFieldName(fieldName.substring(1))
					.setDescending(true);
			else
				sortByRq.setFieldName(fieldName);

			return sortByRq;
		}
	}

	@Getter
	@Setter
	@Accessors(chain = true)
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SortByRq {
		@NotEmpty
				private String fieldName;
		private Boolean descending;

				public boolean getDescendingSafe() {
			return getDescending() != null ? getDescending() : false;
		}
	}

	@Getter
	@Setter
	public static class SearchRs<T> {
		private List<T> list;
		private Long totalCount;
	}

	@Getter
	@Setter
	@Accessors(chain = true)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ToString
	public static class CriteriaRq {
		private String fieldName;
		private String regex;
		private EOperator operator;
		private Object value;
		private Object start;
		private Object end;
		private List<CriteriaRq> criteria;

		public List<Object> getValue() {
			if (value != null) {
				if (value instanceof Collection)
					return (List<Object>) value;
				else if (value instanceof Map && ((Map) value).size() == 0)
					return null;
				else
					return Collections.singletonList(value);
			} else {
				final List<Object> tempValue = new ArrayList<>();

				tempValue.add(start);
				tempValue.add(end);

				return tempValue;
			}
		}
	}
}

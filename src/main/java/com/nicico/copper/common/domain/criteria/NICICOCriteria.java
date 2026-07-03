package com.nicico.copper.common.domain.criteria;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.util.MultiValueMap;

@Getter
@Setter
@Accessors(chain = true)
public class NICICOCriteria {
	private Integer _startRow;
	private Integer _endRow;
	private String operator;
	private Object criteria;
	private Object _sortBy;
	private Boolean distinct;

	public static NICICOCriteria of(MultiValueMap<String, String> criteria) {
		return new NICICOCriteria()
				.set_startRow(criteria.containsKey("_startRow") ? Integer.valueOf(criteria.getFirst("_startRow")) : null)
				.set_endRow(criteria.containsKey("_endRow") ? Integer.valueOf(criteria.getFirst("_endRow")) : null)
				.setOperator(criteria.containsKey("operator") ? criteria.getFirst("operator") : null)
				.setCriteria(criteria.containsKey("criteria") ? criteria.get("criteria") : null)
				.set_sortBy(criteria.containsKey("_sortBy") ? criteria.get("_sortBy") : null)
				.setDistinct(criteria.containsKey("distinct") ? Boolean.valueOf(criteria.getFirst("distinct")) : false)
				;
	}
}

package com.nicico.copper.common.domain.criteria;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicico.copper.base.NICICOException;
import com.nicico.copper.common.dto.grid.GridResponse;
import com.nicico.copper.common.dto.grid.TotalResponse;
import com.nicico.copper.common.dto.search.EOperator;
import com.nicico.copper.common.dto.search.SearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SearchUtil {
	private static ObjectMapper OBJECT_MAPPER;

	// ------------------------------

	public SearchUtil(ObjectMapper mapper) {
		SearchUtil.OBJECT_MAPPER = mapper;
	}

	// ------------------------------

	public static <E, D> TotalResponse<D> search(JpaSpecificationExecutor<E> repository, NICICOCriteria criteria, Function<E, D> converter) {
		final SearchDTO.SearchRq request = SearchUtil.createSearchRq(criteria);
		final SearchDTO.SearchRs<D> response = search(repository, request, converter);

		return mapSearchRs(criteria, response);
	}

	public static <E, D> SearchDTO.SearchRs<D> search(JpaSpecificationExecutor<E> repository, SearchDTO.SearchRq request, Function<E, D> converter) {
		final List<D> result;
		final long totalCount;

		if (request.getStartIndex() != null) {
			final Page<E> all = repository.findAll(NICICOSpecification.of(request), NICICOPageable.of(request));
			totalCount = all.getTotalElements();
			result = all.getContent()
				.stream()
				.map(converter)
				.collect(Collectors.toList());
		} else {
			final List<E> all = repository.findAll(NICICOSpecification.of(request));
			result = all.stream()
				.map(converter)
				.collect(Collectors.toList());
			totalCount = all.size();
		}

		final SearchDTO.SearchRs<D> response = new SearchDTO.SearchRs<>();
		response.setList(result);
		response.setTotalCount(totalCount);
		return response;
	}

	public static SearchDTO.SearchRq createSearchRq(NICICOCriteria criteria) {
		final SearchDTO.SearchRq request = convert(criteria);

		if (criteria.get_startRow() != null && criteria.get_endRow() != null)
			request.setStartIndex(criteria.get_startRow())
				.setCount(criteria.get_endRow() - criteria.get_startRow());

		return request;
	}

	public static <T> TotalResponse<T> mapSearchRs(NICICOCriteria criteria, SearchDTO.SearchRs<T> response) {
		final GridResponse<T> gridResponse = new GridResponse<>();
		gridResponse.setData(response.getList());

		if (criteria.get_startRow() != null && criteria.get_endRow() != null)
			gridResponse.setStartRow(criteria.get_startRow())
				.setEndRow(criteria.get_startRow() + response.getTotalCount().intValue() < criteria.get_endRow() ? criteria.get_startRow() + response.getTotalCount().intValue() : criteria.get_endRow())
				.setTotalRows(response.getTotalCount().intValue());
		else
			gridResponse.setStartRow(0)
				.setEndRow(response.getTotalCount().intValue())
				.setTotalRows(response.getTotalCount().intValue());

		return new TotalResponse<>(gridResponse);
	}

	private static SearchDTO.SearchRq convert(NICICOCriteria criteria) {
		final SearchDTO.SearchRq request = new SearchDTO.SearchRq();

		if (criteria.getCriteria() != null)
			try {
				final List<SearchDTO.CriteriaRq> criteriaRqList = OBJECT_MAPPER.readValue(criteria.getCriteria().toString(), new TypeReference<List<SearchDTO.CriteriaRq>>() {
				});

				if (criteria.getOperator() != null) {
					final SearchDTO.CriteriaRq criteriaRq = new SearchDTO.CriteriaRq();
					criteriaRq.setOperator(EOperator.valueOf(criteria.getOperator()))
						.setCriteria(criteriaRqList);
					request.setCriteria(criteriaRq);
				} else {
					request.setCriteria(criteriaRqList.get(0));
				}
			} catch (IOException e) {
				throw new NICICOException(e);
			}

		if (criteria.get_sortBy() != null) {
			request.setSortBy(criteria.get_sortBy());
		}

		request.setDistinct(criteria.getDistinct());

		return request;
	}
}

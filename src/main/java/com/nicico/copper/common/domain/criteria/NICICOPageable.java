package com.nicico.copper.common.domain.criteria;

import com.nicico.copper.base.IErrorCode;
import com.nicico.copper.base.NICICOException;
import com.nicico.copper.common.dto.search.SearchDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class NICICOPageable implements Pageable, Serializable {
	private final int startIndex;
	private final int count;
	private final Sort sort;

	// ------------------------------

	private NICICOPageable(int startIndex, int count, Sort sort) {
		this.startIndex = startIndex;
		this.count = count;
		this.sort = sort;
	}

	// ------------------------------

	@Override
	public int getPageNumber() {
		return 0;
	}

	@Override
	public int getPageSize() {
		return count;
	}

	@Override
	public long getOffset() {
		return startIndex;
	}

	@Override
	public Sort getSort() {
		return sort;
	}

	@Override
	public Pageable next() {
		throw new RuntimeException("Not Implemented!");
	}

	@Override
	public Pageable previousOrFirst() {
		throw new RuntimeException("Not Implemented!");
	}

	@Override
	public Pageable first() {
		throw new RuntimeException("Not Implemented!");
	}

	@Override
	public Pageable withPage(int pageNumber) {
		return null;
	}

	@Override
	public boolean hasPrevious() {
		return false;
	}

	// ------------------------------

	public static Pageable of(int startIndex, int count) {
		return new NICICOPageable(startIndex, count, Sort.unsorted());
	}

	public static Pageable of(int startIndex, int count, Sort sort) {
		return new NICICOPageable(startIndex, count, sort);
	}

	public static Pageable of(SearchDTO.SearchRq request) {
		if (request.getStartIndex() == null) {
			throw new NICICOException(IErrorCode.NotFound, SearchDTO.SearchRq.FIELD_START_INDEX);
		}

		if (request.getCount() == null) {
			throw new NICICOException(IErrorCode.NotFound, SearchDTO.SearchRq.FIELD_COUNT);
		}

		return new NICICOPageable(request.getStartIndex(), request.getCount(), Sort.unsorted());
	}
}

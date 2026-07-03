package com.nicico.copper.common.dto.grid;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class GridResponse<T> {
	private List<T> data;
	private int status;
	private int startRow;
	private int endRow;
	private int totalRows;
	private boolean invalidateCache;

	public GridResponse(List<T> data) {
		this.data = data;
	}
}

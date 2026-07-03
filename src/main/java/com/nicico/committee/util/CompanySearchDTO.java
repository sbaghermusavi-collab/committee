package com.nicico.committee.util;

import com.nicico.copper.common.dto.search.SearchDTO;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CompanySearchDTO {

    private @NotNull
    @Min(0L) Integer startIndex;

    private @NotNull @Min(1L) Integer count;
    private SearchDTO.CriteriaRq criteria;
    private Object sortBy;
    private Boolean distinct = false;
    private List<String> selectFields;

    public SearchDTO.SearchRq getSearch() {
        return new SearchDTO.SearchRq()
                .setCriteria(this.criteria)
                .setSortBy(this.sortBy)
                .setStartIndex(startIndex)
                .setDistinct(this.distinct);
    }
}
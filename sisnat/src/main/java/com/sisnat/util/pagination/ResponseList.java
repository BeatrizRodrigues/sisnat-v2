package com.sisnat.util.pagination;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseList<DTO> {

    private List<DTO> data;
    private PaginationDTO pagination;

    public ResponseList(final List<DTO> data, final Integer page, final Integer pageSize, final Integer pageTotal, final Long totalElements) {
        this.data = data;

        this.pagination = new PaginationDTO(page, pageSize, pageTotal, totalElements);
    }

    public ResponseList(final List<DTO> data, final ResponseList<?> responseList) {
        this.data = data;

        this.pagination = new PaginationDTO(responseList.getPagination().getPage(), responseList.getPagination().getPageSize(), responseList.getPagination().getPageTotal(), responseList.getPagination().getTotalElements());
    }

}

package com.sisnat.util.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDTO {

    private Integer page;
    private Integer pageSize;
    private Integer pageTotal;
    private Long totalElements;

}

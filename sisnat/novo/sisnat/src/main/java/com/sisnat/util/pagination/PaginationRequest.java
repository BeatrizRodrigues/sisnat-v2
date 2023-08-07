package com.sisnat.util.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;

@Data
@ToString
@AllArgsConstructor
public class PaginationRequest<T> {

    private Example<T> where;
    private @NonNull Pageable page;

}

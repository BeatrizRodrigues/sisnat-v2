package com.sisnat.util.pagination;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;
import static org.springframework.data.domain.Sort.Order.asc;
import static org.springframework.data.domain.Sort.Order.desc;
import static org.springframework.util.CollectionUtils.isEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
public abstract class FindAbstract<MODEL> {

    private List<String> fields;
    private List<String> ordering;
    private int page;
    private int pageSize;


    protected abstract MODEL getProbe();

    public Pageable generatePagination() {
        final var page = this.getPage() > 0 ? this.getPage() - 1 : 0;
        final var pageSize = this.getPageSize() > 0 ? this.getPageSize() : 1000;

        final var orderList = this.getOrder();

        if (!isEmpty(orderList)) {
            return PageRequest.of(page, pageSize, Sort.by(orderList));
        } else {
            return PageRequest.of(page, pageSize);
        }
    }

    public Boolean isValid(final String field) {
        if (isNotBlank(field)) {
            return this.getNameFields().contains(field);
        }

        return false;
    }

    public Example<MODEL> generateWhere() {
        return Example.of(this.getProbe(),
                ExampleMatcher
                        .matching()
                        .withIgnoreCase()
                        .withStringMatcher(CONTAINING));
    }

    protected List<String> getNameFields() {
        return stream(this.getClass().getDeclaredFields())
                .map(Field::getName)
                .collect(toList());
    }

    private List<Sort.Order> getOrder() {
        final var list = new ArrayList<Sort.Order>();

        if (nonNull(ordering) && !this.ordering.isEmpty()) {
            this.ordering.forEach(o -> {
                final Sort.Order handler;

                char prefix = o.charAt(0);
                final String term;
                final boolean inverse;

                if ('-' == prefix) {
                    term = o.substring(1);
                    inverse = true;
                } else {
                    term = o;
                    inverse = false;
                }

                if (this.isValid(term)) {
                    if (inverse) {
                        handler = desc(term);
                    } else {
                        handler = asc(term);
                    }

                    list.add(handler);
                }
            });
        }

        return list;
    }

}

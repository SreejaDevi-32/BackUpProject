package com.virtusa.telecom.utility.commonlib;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PageableFactory {

    private final PaginationProperties properties;

    public Pageable create(Integer page, Integer size, String sort) {

        int pageNumber = (page == null || page < 0) ? 0 : page;

        int pageSize = (size == null || size <= 0)
                ? properties.getDefaultSize()
                : Math.min(size, properties.getMaxSize());

        Sort sortBy = (sort == null || sort.isBlank())
                ? defaultSort()
                : parseSort(sort);

        return PageRequest.of(pageNumber, pageSize, sortBy);
    }

    private Sort defaultSort() {
        PaginationProperties.SortConfig defaultSort =
                properties.getDefaultSort();

        return Sort.by(defaultSort.getDirection(), defaultSort.getField());
    }

    private Sort parseSort(String sort) {

        String[] parts = sort.split(",");

        if (parts.length != 2) {
            return defaultSort();
        }

        Sort.Direction direction = Sort.Direction
                .fromOptionalString(parts[1])
                .orElse(properties.getDefaultSort().getDirection());

        return Sort.by(direction, parts[0]);
    }
}
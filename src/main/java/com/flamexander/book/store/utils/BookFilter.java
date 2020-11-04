package com.flamexander.book.store.utils;

import com.flamexander.book.store.entities.Book;
import com.flamexander.book.store.repositories.specifications.BookSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;

@Getter
public class BookFilter {
    private static final String FILTER_TITLE_REQUEST_PARAM = "book_title";
    private static final String FILTER_MIN_PRICE_REQUEST_PARAM = "min_price";
    private static final String FILTER_MAX_PRICE_REQUEST_PARAM = "max_price";
    private static final String FILTER_GENRE_ID_REQUEST_PARAM = "genre_id";

    private Specification<Book> spec;

    public BookFilter(MultiValueMap<String, String> params) {
        spec = Specification.where(null);
        if (params.containsKey(FILTER_TITLE_REQUEST_PARAM) && !params.get(FILTER_TITLE_REQUEST_PARAM).get(0).isEmpty()) {
            spec = spec.and(BookSpecifications.titleLike(params.get(FILTER_TITLE_REQUEST_PARAM).get(0)));
        }
        if (params.containsKey(FILTER_MIN_PRICE_REQUEST_PARAM) && !params.get(FILTER_MIN_PRICE_REQUEST_PARAM).get(0).isEmpty()) {
            spec = spec.and(BookSpecifications.priceGreaterOrEqualsThan(new BigDecimal(params.get(FILTER_MIN_PRICE_REQUEST_PARAM).get(0))));
        }
        if (params.containsKey(FILTER_MAX_PRICE_REQUEST_PARAM) && !params.get(FILTER_MAX_PRICE_REQUEST_PARAM).get(0).isEmpty()) {
            spec = spec.and(BookSpecifications.priceLesserOrEqualsThan(new BigDecimal(params.get(FILTER_MAX_PRICE_REQUEST_PARAM).get(0))));
        }
        if (params.containsKey(FILTER_GENRE_ID_REQUEST_PARAM) && !params.get(FILTER_GENRE_ID_REQUEST_PARAM).get(0).isEmpty()) {
            spec = spec.and(BookSpecifications.genreIs(Long.parseLong(params.get(FILTER_GENRE_ID_REQUEST_PARAM).get(0))));
        }
    }
}

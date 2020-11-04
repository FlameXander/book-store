package com.flamexander.book.store.dto;

import com.flamexander.book.store.entities.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long bookId;
    private String bookTitle;
    private String genreTitle;
    private String authorName;
    private int quantity;
    private BigDecimal pricePerItem;
    private BigDecimal price;

    public OrderItemDto(OrderItem o) {
        this.bookId = o.getBook().getId();
        this.bookTitle = o.getBook().getTitle();
        this.genreTitle = o.getBook().getGenre().getTitle();
        this.authorName = o.getBook().getAuthor().getName();
        this.quantity = o.getQuantity();
        this.pricePerItem = o.getPricePerItem();
        this.price = o.getPrice();
    }
}

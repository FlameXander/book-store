package com.flamexander.book.store.dto;

import com.flamexander.book.store.entities.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String address;
    private BigDecimal price;

    public OrderDto(Order o) {
        this.id = o.getId();
        this.address = o.getAddress();
        this.price = o.getPrice();
    }
}
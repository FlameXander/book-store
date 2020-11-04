package com.flamexander.book.store.dto;

import com.flamexander.book.store.beans.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CartDto {
    private List<OrderItemDto> items;
    private BigDecimal price;

    public CartDto(Cart cart) {
        this.price = new BigDecimal(cart.getPrice().doubleValue());
        this.items = cart.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}

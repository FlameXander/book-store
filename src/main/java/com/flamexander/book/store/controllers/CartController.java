package com.flamexander.book.store.controllers;

import com.flamexander.book.store.dto.CartDto;
import com.flamexander.book.store.beans.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;

    @GetMapping("/add/{book_id}")
    public void addToCart(@PathVariable(name = "book_id") Long bookId) {
        cart.addOrIncrement(bookId);
    }

    @GetMapping("/dec/{book_id}")
    public void decrementOrRemoveProduct(@PathVariable(name = "book_id") Long bookId) {
        cart.decrementOrRemove(bookId);
    }

    @GetMapping("/remove/{book_id}")
    public void removeProduct(@PathVariable(name = "book_id") Long bookId) {
        cart.remove(bookId);
    }

    @GetMapping
    public CartDto getCartDto() {
        cart.recalculate();
        return new CartDto(cart);
    }
}


package com.flamexander.book.store.services;

import com.flamexander.book.store.beans.Cart;
import com.flamexander.book.store.entities.Book;
import com.flamexander.book.store.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final String CART_PREFIX = "book_store_cart_";

    private final BookService bookService;
    private final RedisTemplate<String, Object> redisTemplate;

    public void addToCart(String cartId, Long productId) {
        Cart cart = getCurrentCart(cartId);
        if (cart.addToCart(productId)) {
            save(cartId, cart);
            return;
        }
        Book book = bookService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Book with id " + productId + " is missed. (Add to cart)"));
        cart.addToCart(book);
        save(cartId, cart);
    }

    public void decrementProduct(String cartId, Long productId) {
        Cart cart = getCurrentCart(cartId);
        cart.decrementProduct(productId);
        save(cartId, cart);
    }

    public boolean isCartExists(String cartId) {
        return redisTemplate.hasKey(CART_PREFIX + cartId);
    }

    public Cart getCurrentCart(String cartId) {
        if (!redisTemplate.hasKey(CART_PREFIX + cartId)) {
            redisTemplate.opsForValue().set(CART_PREFIX + cartId, new Cart());
        }
        Cart cart = (Cart) redisTemplate.opsForValue().get(CART_PREFIX + cartId);
        return cart;
    }

    public void save(String cartId, Cart cart) {
        redisTemplate.opsForValue().set(CART_PREFIX + cartId, cart);
    }

    public void clearCart(String cartId) {
        Cart cart = getCurrentCart(cartId);
        cart.clear();
        save(cartId, cart);
    }

    public void merge(String userCartId, String guestCartId) {
        Cart userCart = getCurrentCart(userCartId);
        Cart guestCart = getCurrentCart(guestCartId);
        userCart.merge(guestCart);
        save(userCartId, userCart);
        save(guestCartId, guestCart);
    }
}

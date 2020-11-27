package com.flamexander.book.store;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.flamexander.book.store.beans.Cart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Cart.class)
public class CartTest {
    @Autowired
    private Cart cart;

    @Test
    public void cartFillingTest() {
//        for (int i = 0; i < 10; i++) {
//            Book book = new Book();
//            long bookId = i / 2 + 1;
//            book.setId(bookId);
//            book.setPrice(new BigDecimal(100 + bookId * 10));
//            book.setTitle("Book #" + bookId);
//            cart.addOrIncrement(book);
//        }
//        Assertions.assertEquals(5, cart.getItems().size());
//        cart.clear();
//        Assertions.assertEquals(0, cart.getItems().size());
    }
}
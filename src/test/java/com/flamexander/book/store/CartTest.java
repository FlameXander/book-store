package com.flamexander.book.store;

import com.flamexander.book.store.dto.OrderItemDto;
import com.flamexander.book.store.entities.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class CartTest {
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
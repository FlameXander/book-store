package com.flamexander.book.store;

import com.flamexander.book.store.entities.Book;
import com.flamexander.book.store.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoriesTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void productRepositoryTest() {
//        Book book = new Book(null, "Harry Potter", "Good book", new BigDecimal(350.0), 1996, Book.Genre.FANTASY, null);
//        Book out = entityManager.persist(book);
//        entityManager.flush();
//
//        List<Book> productsList = bookRepository.findAll();
//
//        Assertions.assertEquals(2, productsList.size());
//        Assertions.assertEquals("Harry Potter", productsList.get(1).getTitle());
    }

    @Test
    public void initDbTest() {
        List<Book> productsList = bookRepository.findAll();
        Assertions.assertEquals(1, productsList.size());
    }
}

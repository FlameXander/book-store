package com.flamexander.book.store;

import com.flamexander.book.store.entities.Book;
import com.flamexander.book.store.entities.Genre;
import com.flamexander.book.store.repositories.BookRepository;
import com.flamexander.book.store.repositories.GenreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoriesTest {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void genreRepositoryTest() {
        Genre genre = new Genre();
        genre.setTitle("Fiction");
        entityManager.persist(genre);
        entityManager.flush();

        List<Genre> genresList = genreRepository.findAll();

        Assertions.assertEquals(2, genresList.size());
        Assertions.assertEquals("Fiction", genresList.get(1).getTitle());
    }

    @Test
    public void initDbTest() {
        List<Genre> genresList = genreRepository.findAll();
        Assertions.assertEquals(1, genresList.size());
    }
}

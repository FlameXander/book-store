package com.flamexander.book.store;

import com.flamexander.book.store.dto.BookDto;
import com.flamexander.book.store.entities.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FullServerRunTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void fullRestTest() {
        // Spring page class ...
        List<Genre> genres = restTemplate.getForObject("/api/v1/genres", List.class);
        assertThat(genres).isNotNull();
        assertThat(genres).isNotEmpty();
    }
}

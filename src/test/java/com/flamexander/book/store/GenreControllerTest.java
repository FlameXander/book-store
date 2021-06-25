package com.flamexander.book.store;

import com.flamexander.book.store.controllers.BookController;
import com.flamexander.book.store.dto.BookDto;
import com.flamexander.book.store.entities.Book;
import com.flamexander.book.store.entities.Genre;
import com.flamexander.book.store.repositories.GenreRepository;
import com.flamexander.book.store.services.BookService;
import com.flamexander.book.store.services.GenreService;
import com.flamexander.book.store.utils.BookFilter;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GenreControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private GenreRepository genreRepository;

    @Test
    @WithMockUser(username = "Bob", authorities = "USER")
    public void getAllGenresTest() throws Exception {
//        ProjectionFactory factory = new SpelAwareProxyProjectionFactory(); // для интерфейсов
//        BookDto bookDto = factory.createProjection(BookDto.class);
//        bookDto.setTitle("Harry Potter");

        Genre fantasy = new Genre();
        fantasy.setId(1L);
        fantasy.setTitle("Fantasy");
        List<Genre> allGenres = new ArrayList<>(Arrays.asList(
                fantasy
        ));
        given(genreRepository.findAll()).willReturn(allGenres);

        mvc
                .perform(
                        get("/api/v1/genres")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(allGenres.get(0).getTitle())));
    }
}

package com.flamexander.book.store;

import com.flamexander.book.store.controllers.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebMvcTest(controllers = BookController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
public class OnlyBookControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void ooo() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
    }
//
//    @MockBean
//    private BookService bookService;
//
//    @Test
//    public void getAllProductsTest() throws Exception {
//        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
//        BookDto bookDto = factory.createProjection(BookDto.class);
//        bookDto.setTitle("Harry Potter");
//        List<BookDto> allBooks = Arrays.asList(
//                bookDto
//        );
//        given(bookService.findAll()).willReturn(allBooks);
//        mvc.perform(get("/api/v1/books")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$[0].title", is(allBooks.get(0).getTitle())));
//    }
}

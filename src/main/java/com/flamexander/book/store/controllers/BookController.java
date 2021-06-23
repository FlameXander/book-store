package com.flamexander.book.store.controllers;

import com.flamexander.book.store.exceptions.ResourceNotFoundException;
import com.flamexander.book.store.entities.Book;
import com.flamexander.book.store.dto.BookDto;
import com.flamexander.book.store.services.BookService;
import com.flamexander.book.store.utils.BookFilter;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
@Api("Set of endpoints for books")
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;

    @GetMapping
    @ApiOperation("Returns list of all books in the store.")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "book_title", example = "Harry", type = "String", required = false, paramType = "query"),
            @ApiImplicitParam(name = "min_price", example = "100.0", type = "BigDecimal", required = false, paramType = "query"),
            @ApiImplicitParam(name = "max_price", example = "2000.0", type = "BigDecimal", required = false, paramType = "query"),
            @ApiImplicitParam(name = "genres_ids", example = "[ 1, 2 ]", type = "List<Long>", required = false, paramType = "query")
    })
    public Page<BookDto> getAllBooks(@RequestParam MultiValueMap<String, String> requestParams, @RequestParam(name = "p", defaultValue = "1") Integer page) {
        if (page < 1) {
            page = 1;
        }
        return bookService.findAll(new BookFilter(requestParams).getSpec(), page - 1, 20);
    }

    @GetMapping("/{id}")
    @ApiOperation("Returns a specific book by their identifier. 404 if does not exist.")
    public BookDto getBookById(@ApiParam("Id of the book to be obtained. Cannot be empty.") @PathVariable Long id) {
        Book book = bookService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book with id: " + id + " not found"));
        return new BookDto(book);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation("Creates a new book. If id != null, then it will be cleared")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createNewBook(@RequestBody @Validated BookDto bookDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // todo throw exception
        }
        if (bookDto.getId() != null) {
            bookDto.setId(null);
        }
        return bookService.save(bookDto);
    }

//    @PutMapping(consumes = "application/json", produces = "application/json")
//    @ApiOperation("Update book")
//    public Book modifyBook(@RequestBody Book book) {
//        if (!bookService.existsById(book.getId())) {
//            throw new ResourceNotFoundException("Book with id: " + book.getId() + " doesn't exists (for modification)");
//        }
//        return bookService.saveOrUpdate(book);
//    }

//    @DeleteMapping
//    @ApiOperation("Delete all books from the system")
//    public void deleteAllBooks() {
//        bookService.deleteAll();
//    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletes a book from the system. 404 if the book's identifier is not found.")
    public void deleteById(@ApiParam("Id of the book to be deleted. Cannot be empty.") @PathVariable Long id) {
        if (!bookService.existsById(id)) {
            throw new ResourceNotFoundException("Book with id: " + id + " doesn't exists (for delete)");
        }
        bookService.deleteById(id);
    }
}

package com.flamexander.book.store.services;

import com.flamexander.book.store.entities.Author;
import com.flamexander.book.store.entities.Book;
import com.flamexander.book.store.dto.BookDto;
import com.flamexander.book.store.exceptions.ResourceNotFoundException;
import com.flamexander.book.store.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    public Page<BookDto> findAll(Specification<Book> spec, int page, int size) {
        Page<Book> content = bookRepository.findAll(spec, PageRequest.of(page, size));
        Page<BookDto> out = new PageImpl<>(content.getContent().stream().map(BookDto::new).collect(Collectors.toList()), content.getPageable(), content.getTotalElements());
        return out;
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public BookDto save(BookDto bookDto) {
        Book book = new Book();
        book.setPrice(bookDto.getPrice());
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
        book.setPublishYear(bookDto.getPublishYear());
        book.setAuthor(authorService.findByName(bookDto.getAuthorName()).get()); // todo exception throwing
        book.setGenre(genreService.findByTitle(bookDto.getGenre()).get()); // todo exception throwing
        book = bookRepository.save(book);
        return new BookDto(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }

    public void deleteAll() {
        bookRepository.deleteAll();
    }

    public List<BookDto> mapEntityToDto(List<Book> books) {
        return books.stream().map(BookDto::new).collect(Collectors.toList());
    }
}

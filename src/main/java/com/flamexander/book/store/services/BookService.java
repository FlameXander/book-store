package com.flamexander.book.store.services;

import com.flamexander.book.store.entities.Book;
import com.flamexander.book.store.dto.BookDto;
import com.flamexander.book.store.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Page<Book> findAll(Specification<Book> spec, int page, int size) {
        return bookRepository.findAll(spec, PageRequest.of(page, size));
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveOrUpdate(Book book) {
        return bookRepository.save(book);
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

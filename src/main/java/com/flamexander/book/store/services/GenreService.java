package com.flamexander.book.store.services;

import com.flamexander.book.store.entities.Genre;
import com.flamexander.book.store.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Optional<Genre> findByTitle(String title) {
        return genreRepository.findByTitle(title);
    }
}

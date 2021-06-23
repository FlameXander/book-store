package com.flamexander.book.store.controllers;

import com.flamexander.book.store.entities.Genre;
import com.flamexander.book.store.services.GenreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
@Api("Set of endpoints for genres")
@RequiredArgsConstructor
@Slf4j
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    @ApiOperation("Returns list of all genres in the system.")
    public List<Genre> getAllGenres() {
        return genreService.findAll();
    }
}

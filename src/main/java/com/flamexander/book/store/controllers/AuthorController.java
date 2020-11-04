package com.flamexander.book.store.controllers;

import com.flamexander.book.store.services.AuthorService;
import com.flamexander.book.store.entities.Author;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@Api("Set of endpoints for authors")
@RequiredArgsConstructor
@Slf4j
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    @ApiOperation("Returns list of all authors in the system.")
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }
}

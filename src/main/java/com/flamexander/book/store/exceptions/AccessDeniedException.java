package com.flamexander.book.store.exceptions;

import lombok.Data;

import java.security.Principal;

@Data
public class AccessDeniedException extends RuntimeException {
    private String username;

    public AccessDeniedException(String message, Principal principal) {
        super(message);
        this.username = principal.getName();
    }
}

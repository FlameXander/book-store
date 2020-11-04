package com.flamexander.book.store.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class BookServiceError {
    private int status;
    private String message;
    private Date timestamp;

    public BookServiceError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}

package com.flamexander.book.store.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BookServiceError {
    private int status;
    private List<String> message;
    private Date timestamp;

    public BookServiceError(int status, String message) {
        this.status = status;
        this.message = List.of(message);
        this.timestamp = new Date();
    }

    public BookServiceError(int status, String... messages) {
        this.status = status;
        this.message = List.of(messages);
        this.timestamp = new Date();
    }

    public BookServiceError(int status, List<String> messages) {
        this.status = status;
        this.message = messages;
        this.timestamp = new Date();
    }
}

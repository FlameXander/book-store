package com.flamexander.book.store.dto;

import com.flamexander.book.store.entities.Role;

import javax.persistence.*;
import java.util.Collection;

public class RegUser {
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "passwordConfirmation")
    private String passwordConfirmation;

    @Column(name = "email")
    private String email;
}

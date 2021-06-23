package com.flamexander.book.store.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Class representing a book in the application.")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "Unique identifier of the book. No two books can have the same id.", example = "1", required = true, position = 0)
    private Long id;

    @Column(name = "title")
    @ApiModelProperty(notes = "Title of the book.", example = "Harry Potter", required = true, position = 1)
    @Size(min = 4, max = 255)
    private String title;

    @Column(name = "description")
    @ApiModelProperty(notes = "Description of the book.", example = "Good book", required = true, position = 2)
    private String description;

    @Column(name = "price")
    @ApiModelProperty(notes = "Price of the book (rub).", example = "450.00", required = true, position = 3)
    @Min(0)
    private BigDecimal price;

    @Column(name = "publish_year")
    @ApiModelProperty(notes = "Publish date of the book.", example = "2002", required = true, position = 5)
    @Min(1600)
    @Max(2100)
    private int publishYear;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @ApiModelProperty(notes = "Genre of the book.", example = "DETECTIVE", required = true, position = 4)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @ApiModelProperty(notes = "Author of the book.", example = "Rowling", required = true, position = 6)
    private Author author;

    @CreationTimestamp
    @Column(name = "created_at")
    @ApiModelProperty(notes = "Creation timestamp.", position = 7)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @ApiModelProperty(notes = "Last update timestamp.", position = 8)
    private LocalDateTime updatedAt;

    public Book(Long id, @Size(min = 4, max = 255) String title, String description, @Min(0) BigDecimal price, @Min(1900) @Max(2100) int publishYear, Genre genre, Author author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.publishYear = publishYear;
        this.genre = genre;
        this.author = author;
    }
}

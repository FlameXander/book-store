package com.flamexander.book.store.dto;

import com.flamexander.book.store.entities.Book;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@ApiModel(description = "Book dto in the application.")
@Data
@NoArgsConstructor
public class BookDto {
    @ApiModelProperty(notes = "Unique identifier of the book. No two books can have the same id.", example = "1", required = true, position = 0)
    private Long id;

    @ApiModelProperty(notes = "Title of the book.", example = "Harry Potter", required = true, position = 1)
    @Size(min = 4, max = 255)
    private String title;

    @ApiModelProperty(notes = "Genre of the book.", example = "FANTASY", required = true, position = 2)
    private String genre;

    @ApiModelProperty(notes = "Description of the book.", example = "Nice book, very nice", required = true, position = 3)
    private String description;

    @ApiModelProperty(notes = "Author of the book.", example = "Rowling", required = true, position = 4)
    private String authorName;

    @ApiModelProperty(notes = "Price of the book (RUB).", example = "450.00", required = true, position = 5)
    @Min(0)
    private BigDecimal price;

    @ApiModelProperty(notes = "Publish year.", example = "1998", required = true, position = 6)
    @Min(1600)
    @Max(2100)
    private int publishYear;

    public BookDto(Long id, @Size(min = 4, max = 255) String title, String genre, String description, String authorName, @Min(0) BigDecimal price, @Min(1900) @Max(2050) int publishYear) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.authorName = authorName;
        this.price = price;
        this.publishYear = publishYear;
    }

    public BookDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.authorName = book.getAuthor().getName();
        this.price = book.getPrice();
        this.publishYear = book.getPublishYear();
        this.genre = book.getGenre().getTitle();
    }
}

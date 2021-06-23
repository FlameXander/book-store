package com.flamexander.book.store.repositories;

import com.flamexander.book.store.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
//    @Query("select b.id as id, b.title as title, b.genre as genre, b.description as description, b.price as price, b.publishYear as publishYear, b.author.name as authorName from Book b")
//    @Query("select new com.geekbrains.book.store.dto.BookDto(b) from Book b")
//    List<BookDto> findAllBooksWithAuthorName();

    boolean existsByTitle(String title);
}

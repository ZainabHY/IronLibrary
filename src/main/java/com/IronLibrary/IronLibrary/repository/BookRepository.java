package com.IronLibrary.IronLibrary.repository;

import com.IronLibrary.IronLibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) = LOWER(:title)")
    List<Book> findBookByTitle(@Param("title") String title);
    @Query("SELECT b FROM Book b WHERE LOWER(b.category) = LOWER(:category)")
    List<Book> findBooksByCategory(@Param("category") String category);
}

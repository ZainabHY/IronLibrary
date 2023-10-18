package com.IronLibrary.HW3IronLibrary.Repository;


import com.IronLibrary.HW3IronLibrary.Entity.Author;
import com.IronLibrary.HW3IronLibrary.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Book> findByTitle(String title);
    List<Book> findByCategory(String category);

    List<Book> findByAuthor(Author author);
}

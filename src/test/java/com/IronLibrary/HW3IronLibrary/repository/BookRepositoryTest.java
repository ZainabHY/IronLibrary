package com.IronLibrary.HW3IronLibrary.repository;

import com.IronLibrary.HW3IronLibrary.Entity.Author;
import com.IronLibrary.HW3IronLibrary.Entity.Book;
import com.IronLibrary.HW3IronLibrary.Repository.AuthorRepository;
import com.IronLibrary.HW3IronLibrary.Repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    Book book1;
    Book book2;
    Author author1;
    Author author2;

    @Test
    void setUp() {
        book1 = new Book("123", "Intro to Java", "learning", 4);
        bookRepository.save(book1);
        book2 = new Book("456", "Into to Python", "learning", 3);
        bookRepository.save(book2);
        author1 = new Author("Waleed", "waleed@gmail.com");
        authorRepository.save(author1);
        author2 = new Author("Ali", "Ali@gmail.com");
        authorRepository.save(author2);
    }

    @Test
    void FindByTitleTest() {

        assertEquals("123", bookRepository.findByTitle("Intro to Java").get().getIsbn());
    }

    @Test
    void FindByCategoryTest() {
        assertEquals(bookRepository.findByCategory("learning"), bookRepository.findByCategory("learning"));
    }

}


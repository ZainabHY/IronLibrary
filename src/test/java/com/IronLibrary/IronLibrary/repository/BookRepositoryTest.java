package com.IronLibrary.IronLibrary.repository;

import com.IronLibrary.IronLibrary.entity.Author;
import com.IronLibrary.IronLibrary.entity.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    @BeforeEach
    public void setUp() {
        Book book = new Book("978-3-16-148410-0",  "The Notebook", "Romance", 4);
        bookRepository.save(book);
        Author author = new Author("Nicholas Sparks", "elnicky@gmail.com", book);
        authorRepository.save(author);
    }

    @AfterEach
    public void tearDown() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    public void findAll() {
        List<Book> bookList = bookRepository.findAll();
        System.out.println(bookList);
        assertEquals(1, bookList.size());
    }
}
package com.IronLibrary.HW3IronLibrary.repository;

import com.IronLibrary.HW3IronLibrary.entity.Author;
import com.IronLibrary.HW3IronLibrary.entity.Book;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorRepositoryTest {
    // create a new Author with valid name, email, and authorBook
    @Test
    public void test_create_author_with_valid_details() {
        Book mockBook = Mockito.mock(Book.class);
        Author author = new Author("Alla Said", "Alla@example.com", mockBook);

        assertEquals("Alla Said", author.getName());
        assertEquals("Alla@example.com", author.getEmail());
        assertEquals(mockBook, author.getAuthorBook());
    }

    // get the id of an Author
    @Test
    public void test_get_author_id() {
        Author author = new Author();
        author.setId(1);

        assertEquals(1L, author.getId().longValue());
    }

    // get the name of an Author
    @Test
    public void test_get_author_name() {
        Author author = new Author();
        author.setName("Alla Said");

        assertEquals("Alla Said", author.getName());
    }

    // create a new Author with null name
    @Test
    public void test_create_author_with_null_name() {
        Book mockBook = Mockito.mock(Book.class);
        Author author = new Author(null, "Alla@example.com", mockBook);

        assertNull(author.getName());
        assertEquals("Alla@example.com", author.getEmail());
        assertEquals(mockBook, author.getAuthorBook());
    }

}
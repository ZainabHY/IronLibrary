package com.IronLibrary.HW3IronLibrary.Entity;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorTest {
    //create an Author object with a name and email
    @Test
    public void test_create_author_with_name_and_email() {
        Author author = new Author("Alla Said", "Alla@example.com");
        assertEquals("Alla Said", author.getName());
        assertEquals("Alla@example.com", author.getEmail());
    }

    // get the id of an Author
    @Test
    public void test_get_author_id() {
        Author author = new Author();
        author.setId(1L);

        assertEquals(1L, author.getId().longValue());
    }

    // get the name of an Author
    @Test
    public void test_get_author_name() {
        Author author = new Author();
        author.setName("Alla Said");

        assertEquals("Alla Said", author.getName());
    }



}
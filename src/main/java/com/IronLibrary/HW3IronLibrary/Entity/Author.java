package com.IronLibrary.HW3IronLibrary.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tbl_authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "authorBook")
    @JoinColumn(name = "book_id")
    private Book authorBook;


    public Author(String name, String email, Book authorBook) {
        this.name = name;
        this.email = email;
        this.authorBook = authorBook;
    }

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Book getAuthorBook() {
        return authorBook;
    }

    public void setAuthorBook(Book authorBook) {
        this.authorBook = authorBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(name, author.name) && Objects.equals(email, author.email) && Objects.equals(authorBook, author.authorBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, authorBook);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", authorBook=" + authorBook +
                '}';
    }
}
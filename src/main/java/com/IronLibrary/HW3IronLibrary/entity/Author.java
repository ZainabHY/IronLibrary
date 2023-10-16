package com.IronLibrary.HW3IronLibrary.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tbl_author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;
    private String name;
    private String email;
    @OneToOne
    @JoinColumn(name = "book_Id")
    private Book authorBook;

    //Constructors
    public Author() {
    }

    public Author(String name, String email) {
        this.name = name;
        this.email = email;
    }

    //Getters & Setters
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(authorId, author.authorId) && Objects.equals(name, author.name) && Objects.equals(email, author.email) && Objects.equals(authorBook, author.authorBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, name, email, authorBook);
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", authorBook=" + authorBook +
                '}';
    }
}
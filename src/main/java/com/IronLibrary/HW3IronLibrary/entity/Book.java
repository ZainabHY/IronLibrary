package com.IronLibrary.HW3IronLibrary.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tbl_book")
public class Book {

    // isbn: International Standard Book Number = 13 digit unique number
    @Id
    private String isbn;
    private String title;
    private String category;
    private Integer quantity;

    @OneToOne(mappedBy = "authorBook")
    private Author author;

    public Book() {}

    public Book(String isbn, String title, String category, Integer quantity) {
        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title) && Objects.equals(category, book.category) && Objects.equals(quantity, book.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, category, quantity);
    }

    @Override
    public String toString() {
        return
                "/n isbn='" + isbn + '\'' +
                        ", title='" + title + '\'' +
                        ", category='" + category + '\'' +
                        ", quantity=" + quantity +
                        '}';
    }

}

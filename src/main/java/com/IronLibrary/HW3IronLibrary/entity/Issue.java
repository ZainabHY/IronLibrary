package com.IronLibrary.HW3IronLibrary.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Table(name = "tbl_issue")
public class Issue {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer issueId;

    private String issueDate;
    private String returnDate;

    @OneToOne()
    private Student issueStudent;

    @OneToOne()
    private Book issueBook;

    public Issue() {}

    public Issue(String issueDate, String returnDate, Student issueStudent, Book issueBook) {
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.issueStudent = issueStudent;
        this.issueBook = issueBook;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Student getIssueStudent() {
        return issueStudent;
    }

    public void setIssueStudent(Student issueStudent) {
        this.issueStudent = issueStudent;
    }

    public Book getIssueBook() {
        return issueBook;
    }

    public void setIssueBook(Book issueBook) {
        this.issueBook = issueBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Issue issue)) return false;
        return Objects.equals(getIssueId(), issue.getIssueId()) && Objects.equals(getIssueDate(), issue.getIssueDate()) && Objects.equals(getReturnDate(), issue.getReturnDate()) && Objects.equals(getIssueStudent(), issue.getIssueStudent()) && Objects.equals(getIssueBook(), issue.getIssueBook());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIssueId(), getIssueDate(), getReturnDate(), getIssueStudent(), getIssueBook());
    }

    @Override
    public String toString() {
        return "Issue{" +
                "issueId=" + issueId +
                ", issueDate='" + issueDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", issueStudent=" + issueStudent +
                ", issueBook=" + issueBook +
                '}';
    }
}

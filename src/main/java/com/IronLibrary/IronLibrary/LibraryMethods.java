package com.IronLibrary.IronLibrary;

import com.IronLibrary.IronLibrary.entity.Author;
import com.IronLibrary.IronLibrary.entity.Book;
import com.IronLibrary.IronLibrary.entity.Issue;
import com.IronLibrary.IronLibrary.entity.Student;
import com.IronLibrary.IronLibrary.repository.AuthorRepository;
import com.IronLibrary.IronLibrary.repository.BookRepository;
import com.IronLibrary.IronLibrary.repository.IssueRepository;
import com.IronLibrary.IronLibrary.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LibraryMethods {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    IssueRepository issueRepository;

    public void insertStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findById(student.getUsn());
        if (studentOptional.isPresent()) {
            return;
        }
        studentRepository.save(student);
    }

    public boolean insertBook(Book newBook) {
        Optional<Book> bookOptional = bookRepository.findById(newBook.getIsbn());
        if (bookOptional.isPresent()) {
            Book oldBook = bookOptional.get();
            Integer newQuantity = oldBook.getQuantity() + newBook.getQuantity();
            oldBook.setQuantity(newQuantity);
            bookRepository.save(oldBook);
            return true;
        }
        bookRepository.save(newBook);
        return false;
    }

    public void insertAuthor(Author author) {
        authorRepository.save(author);
    }

    public void insertIssue(Issue issue) {
        Optional<Book> bookOptional = bookRepository.findById(issue.getIssueBook().getIsbn());
        Book oldBook = bookOptional.get();
        Integer newQuantity = oldBook.getQuantity() - 1;
        oldBook.setQuantity(newQuantity);
        bookRepository.save(oldBook);
        issueRepository.save(issue);
    }

    public Issue getIssueById(Integer issueId) {
        Optional<Issue> optionalIssue = issueRepository.findById(issueId);
        if(optionalIssue.isPresent()) {
            return optionalIssue.get();
        }
        return null;
    }

    public List<Book> getBookByTitle(String title) {
        List<Book> bookList = bookRepository.findBookByTitle(title);
        if(bookList.size() > 0) {
            return bookList;
        }
        return null;
    }

    public List<Book> getBookByCategory(String category) {
        List<Book> bookList = bookRepository.findBooksByCategory(category);
        if(bookList.size() > 0) {
            return bookList;
        }
        return null;
    }

    public Book getBookByAuthor(String authorName) {
        Optional<Author> optionalAuthor = authorRepository.findAuthorByName(authorName);
        if (optionalAuthor.isPresent()) {
            return optionalAuthor.get().getPublishedBook();
        }
        return null;
    }

    public List<Author> getAllAuthors() {
        List<Author> authorsList = authorRepository.findAll();
        if (authorsList.size() > 0) {
            return authorsList;
        }
        return null;
    }

    public Book getBookById(String isbn) {
        Optional<Book> optionalBook = bookRepository.findById(isbn);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        }
        return null;
    }

    public List<Issue>findIssueByStudentNumber(String studentUsn){
        Optional<Student> optionalStudent = studentRepository.findById(studentUsn);
        if(optionalStudent.isPresent()){
            List<Issue> issueList = issueRepository.findAllByStudentUsn(studentUsn);
            if(issueList.size() > 0) {
                return issueList;
            }
            return null;

        }
        return null;
    }


    // if student return a book >> we delete it from issue table
    public void deleteIssue(Integer issueId) {
        Optional<Issue> issueOptional = issueRepository.findById(issueId);
        Issue oldIssue = issueOptional.get();
        Optional<Book> bookOptional = bookRepository.findById(oldIssue.getIssueBook().getIsbn());
        Book oldBook = bookOptional.get();
        Integer newQuantity = oldBook.getQuantity() + 1;
        oldBook.setQuantity(newQuantity);
        bookRepository.save(oldBook);
        issueRepository.delete(oldIssue);
    }

    public Boolean isAuthorExistent(String authorName){
        Optional<Author> optionalAuthor = authorRepository.findAuthorByName(authorName);
        if(optionalAuthor.isPresent()){
            return true;
        }
        return false;
    }

    @PersistenceContext
    private EntityManager entityManager;


    public boolean isISBNAlreadyIssued(String isbn) {
        Query query = entityManager.createQuery("SELECT COUNT(i) FROM Issue i WHERE i.issueBook.isbn = :isbn");
        query.setParameter("isbn", isbn);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }

}

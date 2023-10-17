package com.IronLibrary.HW3IronLibrary.Entity;

import com.IronLibrary.HW3IronLibrary.Repository.AuthorRepository;
import com.IronLibrary.HW3IronLibrary.Repository.BookRepository;
import com.IronLibrary.HW3IronLibrary.Repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LibraryMethods {
    List<Book> booksList = new ArrayList<>();


    // First method - add Book
    public static void addBook(AuthorRepository authorRepository, BookRepository bookRepository) {
        Scanner sc = new Scanner(System.in);

        // List of print added by user;
        System.out.print("Please introduce a isbn: ");
        String isbnBook = sc.nextLine();
        System.out.print("Please introduce a title: ");
        String titleBook = sc.nextLine();
        System.out.print("Please introduce a category: ");
        String categoryBook = sc.nextLine();
        System.out.print("Please introduce the name of the author: ");
        String nameAuthor = sc.nextLine();
        System.out.print("Please introduce the mail of the author: ");
        String mailAuthor = sc.nextLine();
        System.out.print("Please introduce the number of books: ");
        int numberCopiesBook = sc.nextInt();
        // ----------------------------------
        // create and save new book
        Book book = bookRepository.save(new Book(isbnBook, titleBook, categoryBook, numberCopiesBook));
        // -----------------------------------
        //create and save new author
        if (authorRepository.findByEmail(mailAuthor).isPresent()) {
            Author author = authorRepository.findByEmail(mailAuthor).get();
            book.setAuthor(author);
            bookRepository.save(book);
        } else {
            //------------------------------------
            //set author to book and save it to repository
            Author author = authorRepository.save(new Author(nameAuthor, mailAuthor));
            book.setAuthor(author);
            bookRepository.save(book);
        }
        sc.nextLine();
    }


    // Second method - search book by the title
    public void searchBookByTitle(BookRepository bookRepository) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce the title of the book: ");
        String titleBook = sc.nextLine();

        Book foundBook = null;

        for(int i = 0; i < booksList.size(); i++) {
            if (booksList.get(i).getTitle() == titleBook)
                foundBook = booksList.get(i);
            break;
        }
        if (foundBook != null) {
            System.out.println("ISBN: " + foundBook.getIsbn());
        } else
            System.err.println("Title not found");

//
//        System.out.println("Introduce the title of the book:");
//        titleBook = sc.nextLine();
//        if (bookRepository.findByTitle(titleBook).isPresent()) {
//            Book ZainabDB = bookRepository.findByTitle(titleBook).get();
//            System.out.print("Book ISBN\n" + "Book Title\n" + "Category\n" + "No of Books\n");
//            System.out.println(ZainabDB.toString());
//        } else
//            System.err.println("Title not found");

    }


    // Third method - search book by category
    public void searchBookByCategory(BookRepository bookRepository) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce the category of the book you want: ");
        String categoryBook = sc.nextLine();

        List<Book> booksListCategory = new ArrayList<>();
        Book foundBookCategory = null;

        for (int i = 0; i < booksList.size(); i++) {
            if (booksList.get(i).getCategory() == categoryBook)
                booksListCategory.add(booksListCategory.get(i));
        }

        // for loop in list of found books by category (foundBookCategoryList)
        // if it's found --> foundBookCategoryList.add(foundBookCategoryList.get(i)
        // 2nd for loop --> print all books info in foundBookCategoryList using toString
        if (booksListCategory != null) {
            System.out.println("List of books of category \"" + categoryBook + "\"");
            System.out.println("--------------------------");
            for(int i = 0; i < booksListCategory.size(); i++) {
                System.out.println("Book #" + (i+1));
                foundBookCategory.toString();
                System.out.println();
            }
        } else
            System.err.println("Title Category not found");
    }
    // method 4-search books by author
    public void searchBooksByAuthor(AuthorRepository authorRepository, BookRepository bookRepository) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the author: ");
        String authorName = sc.nextLine();

        // Find the author by name in the AuthorRepository
        Optional<Author> authorOptional = authorRepository.findByName(authorName);

        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();

            // Find books by the author in the BookRepository
            List<Book> booksByAuthor = bookRepository.findByAuthor(author);

            if (!booksByAuthor.isEmpty()) {
                System.out.println("Books by " + author.getName() + ":");
                for (Book book : booksByAuthor) {
                    System.out.println("ISBN: " + book.getIsbn() + ", Title: " + book.getTitle());
                }
            } else {
                System.out.println("No books found by " + author.getName());
            }
        } else {
            System.out.println("Author not found with the name: " + authorName);
        }
    }
    // 5- list all books with authors
    public void listBooksWithAuthors(BookRepository bookRepository) {
        List<Book> allBooks = bookRepository.findAll();

        if (!allBooks.isEmpty()) {
            System.out.println("List of Books with Authors:");
            for (Book book : allBooks) {
                System.out.println("ISBN: " + book.getIsbn());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor().getName());
                System.out.println();
            }
        } else {
            System.out.println("No books found in the library.");
        }
    }
    // method 7- issue book to student
    public void issueBookToStudent(BookRepository bookRepository, StudentRepository studentRepository) {
        Scanner sc = new Scanner(System.in);

        // Get student information
        System.out.print("Enter student ID: ");
        long studentId = sc.nextLong();
        sc.nextLine(); // Consume the newline character

        // Find the student by ID in the StudentRepository
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            // Display available books
            System.out.println("Available Books:");
            List<Book> availableBooks = bookRepository.findAll();
            for (Book book : availableBooks) {
                System.out.println("ISBN: " + book.getIsbn() + ", Title: " + book.getTitle());
            }

            // Get the ISBN of the book to be issued
            System.out.print("Enter the ISBN of the book to be issued: ");
            String isbn = sc.nextLine();

            // Find the book by ISBN in the BookRepository
            Optional<Book> bookOptional = bookRepository.findById(isbn);

            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();

                // Check if the book is available
                if (book.getQuantity() > 0) {
                    // Issue the book to the student
                    book.setQuantity(book.getQuantity() - 1);
                    book.setStudent(student);
                    bookRepository.save(book);
                    System.out.println("Book issued to " + student.getName());
                } else {
                    System.out.println("The book is not available.");
                }
            } else {
                System.out.println("Book with ISBN " + isbn + " not found.");
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

}




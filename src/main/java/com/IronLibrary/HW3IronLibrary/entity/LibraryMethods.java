package com.IronLibrary.HW3IronLibrary.entity;


import com.IronLibrary.HW3IronLibrary.repository.AuthorRepository;
import com.IronLibrary.HW3IronLibrary.repository.BookRepository;
import com.IronLibrary.HW3IronLibrary.repository.IssueRepository;
import com.IronLibrary.HW3IronLibrary.repository.StudentRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LibraryMethods {
    List<Book> booksList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);



    // 1. Add a book
    public static void addBook(AuthorRepository authorRepository, BookRepository bookRepository) {

        // List of print added by user;
        System.out.print("Please introduce a isbn: ");
        String isbnBook = sc.nextLine();
        System.out.print("Please introduce a title: ");
        String titleBook = sc.nextLine();
        System.out.print("Please introduce a category: ");
        String categoryBook = sc.nextLine();
        System.out.print("Please introduce the name of the author: ");
        String nameAuthor = sc.nextLine();
        System.out.print("Please introduce the email of the author: ");
        String mailAuthor = sc.nextLine();
        System.out.print("Please introduce the number of books: ");
        int numberCopiesBook = sc.nextInt();
        sc.nextLine();

        // ----------------------------------

        // Check if the author already exists
        Optional<Author> authorOptional = authorRepository.findById(Integer.valueOf(isbnBook));
        Author author;
        if (authorOptional.isPresent()) {
            author = authorOptional.get();
        } else {
            Book book = new Book(isbnBook, titleBook, categoryBook, numberCopiesBook);
            author = authorRepository.save(new Author(nameAuthor, mailAuthor, book));
            book.setAuthor(author);
            bookRepository.save(book);
        }


        System.out.println("Book added successfully.");
        sc.close();
        sc.nextLine();
    }


    // 2. Search book by title
    public void searchBookByTitle(BookRepository bookRepository) {
        System.out.println("Introduce the title of the book: ");
        String titleBook = sc.nextLine();

        Optional<Book> bookOptional = bookRepository.findByTitle(titleBook);


        if (bookOptional.isPresent())
        {
            Book foundBook = bookOptional.get();
            System.out.println("ISBN: " + foundBook.getIsbn());
            System.out.println("Title: " + foundBook.getTitle());
            System.out.println("Category: " + foundBook.getCategory());
            System.out.println("Quantity: " + foundBook.getQuantity());
        } else
            System.err.println("Title not found");
    }

    // 3. Search books by category
    public void searchBooksByCategory(BookRepository bookRepository) {
        System.out.println("Introduce the category of the book you want: ");
        String categoryBook = sc.nextLine();

        List<Book> booksListCategory = new ArrayList<>();
        Book foundBookCategory = null;

        for (int i = 0; i < booksList.size(); i++) {
            if (booksList.get(i).getCategory().equals(categoryBook))
                booksListCategory.add(booksListCategory.get(i));
        }

        // for loop in list of found books by category (foundBookCategoryList)
        // if it's found --> foundBookCategoryList.add(foundBookCategoryList.get(i)
        // 2nd for loop --> print all books info in foundBookCategoryList using toString

        // Check if it's not null
        if (!booksListCategory.isEmpty()) {
            System.out.println("\nList of books of category \"" + categoryBook + "\"");
            System.out.println("--------------------------");
            for(int i = 0; i < booksListCategory.size(); i++) {
                System.out.println("Book #" + (i+1));
                foundBookCategory.toString();
                System.out.println();
            }
        } else
            System.err.println("No books found in the category \"" + categoryBook + "\"");
    }

    // 4. Search book by Author
    public void searchBookByAuthor(BookRepository bookRepository) {
        System.out.print("Enter the name of the author: ");
        String authorName = sc.nextLine();

        // Find books by the author in the BookRepository
        List<Book> booksListAuthor = bookRepository.findByAuthorName(authorName);

        if (!booksListAuthor.isEmpty())
        {
            System.out.println("Books by " + authorName + ":");
            for (int i = 0; i < booksListAuthor.size(); i++)
            {
                System.out.println("Book #" + (i + 1));
                System.out.println(booksListAuthor.get(i).toString());
                System.out.println();
            }
        }
        else
        {
            System.out.println("No books found by \"" + authorName +"\"");
        }
    }

    // 5. List all books along with author
    public void listAllBooksWithAuthors(BookRepository bookRepository) {
        List<Book> allBooks = bookRepository.findAll();

        if (!allBooks.isEmpty()) {
            System.out.println("List of Books with Authors:");
            for (int i = 0; i < booksList.size(); i++) {
                System.out.println("Book #" + (i + 1));
                System.out.println(booksList.get(i).toString());
                System.out.println();
            }
        } else {
            System.out.println("No books found in the library.");
        }
    }

    // 6. Issue book to student
    public void issueBookToStudent(BookRepository bookRepository, StudentRepository studentRepository) {

        // Get student information
        System.out.print("Enter student USN: ");
        String studentUSN = sc.nextLine();

        // Find the student by ID in the StudentRepository
        Optional<Student> studentOptional = studentRepository.findById(studentUSN);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            // Display available books
            System.out.println("\nAvailable Books:");
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
                    System.out.println("Book issued successfully to " + student.getName() + " with USN: " +studentUSN);
                } else {
                    System.out.println("The book is not available");
                }
            } else {
                System.out.println("Book with ISBN " + isbn + " not found");
            }
        } else {
            System.out.println("Student with USN " + studentUSN + " not found");
        }
    }

    // 7. List books by USN
    public void listAllBooksByUsn(StudentRepository studentRepository)
    {
        System.out.print("Enter the student's USN: ");
        String studentUSN = sc.nextLine();

        Optional<Student> studentOptional = studentRepository.findById(studentUSN);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            List<Book> issuedBooks = student.getIssuedBooks();

            if (!issuedBooks.isEmpty()) {
                System.out.println("Books issued to student with USN: " + studentUSN);
                for (int i = 0; i < issuedBooks.size(); i++) {
                    System.out.println("Book #" + (i + 1));
                    System.out.println(issuedBooks.get(i).toString());
                    System.out.println();
                }
            } else {
                System.err.println("No books issued to student with USN: " + studentUSN);
            }
        } else {
            System.err.println("Student with USN: " + studentUSN + " not found");
        }
    }

    // 8. List books to be returned today
    public void listBooksReturnedToday(IssueRepository issueRepository, BookRepository bookRepository)
    {
        LocalDate currentDate = LocalDate.now();
        List<Issue> allIssues = issueRepository.findAll();

        List<Optional<Book>> booksReturnedToday = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Issue issue : allIssues) {
            if (issue.getReturnDate() != null) {
                LocalDate returnDate = LocalDate.parse(issue.getReturnDate(), formatter);
                if (returnDate.equals(currentDate)) {
                    Optional<Book> returnedBook = bookRepository.findById(issue.getIssueBook().getIsbn());
                    if (returnedBook != null) {
                        booksReturnedToday.add(returnedBook);
                    }
                }
            }
        }

        if (!booksReturnedToday.isEmpty()) {
            System.out.println("Books returned today (" + currentDate + "):");
            for (Optional<Book> book : booksReturnedToday) {
                System.out.println(book.toString());
                System.out.println();
            }
        } else {
            System.out.println("No books returned today.");
        }
    }


    // 9. Update book information
    public void updateBook(BookRepository bookRepository, AuthorRepository authorRepository) {
        System.out.println("\nTo remove a book, please provide me with its ISBN: ");
        String isbn = sc.nextLine();

        System.out.println("\nNow provide me with the new book details: ");
        System.out.println("-------------------------------------------");

        // Taking all book info to be updated
        System.out.print("ISBN: ");
        String isbnBook = sc.nextLine();
        System.out.print("Title: ");
        String titleBook = sc.nextLine();
        System.out.print("Category: ");
        String categoryBook = sc.nextLine();
        System.out.print("Name of the Author: ");
        String nameAuthor = sc.nextLine();
        System.out.print("Email of the Author: ");
        String mailAuthor = sc.nextLine();
        System.out.print("Number of Books: ");
        int numberCopiesBook = sc.nextInt();
        sc.nextLine();

        // Check if the author already exists
        Optional<Author> authorOptional = authorRepository.findById(Integer.valueOf(isbnBook));
        Author author;
        Book updatedBook = null;
        if (authorOptional.isPresent()) {
            author = authorOptional.get();
        } else {
            // Create and save the updated book
            updatedBook = new Book(isbnBook, titleBook, categoryBook, numberCopiesBook);
            updatedBook = bookRepository.save(updatedBook);

            // Create and save the author
            author = authorRepository.save(new Author(nameAuthor, mailAuthor, updatedBook));

            // Update the author reference in the updated book
            updatedBook.setAuthor(author);
        }

        // Search for the existing book by ISBN
        Optional<Book> existingBookOptional = bookRepository.findById(isbn);

        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();

            // Update the book properties with the new values
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setCategory(updatedBook.getCategory());
            existingBook.setQuantity(updatedBook.getQuantity());

            // Save the updated book
            bookRepository.save(existingBook);

            System.out.println("Book updated successfully");
        } else {
            System.out.println("Book not found");
        }
    }
    // 10. Remove a book
    public void removeBook(BookRepository bookRepository)
    {
        System.out.println("\nTo remove book, please provide me with its ISBN: ");
        String isbn = sc.nextLine();

        Optional<Book> bookToRemoveOptional = bookRepository.findById(isbn);
        if (bookToRemoveOptional.isPresent()) {
            Book bookToRemove = bookToRemoveOptional.get();

            bookRepository.delete(bookToRemove);
            System.out.println("\nBook with ISBN: " + isbn + " removed successfully");

        }
        System.out.println("Book with ISBN: " + isbn + "not found");
    }


}



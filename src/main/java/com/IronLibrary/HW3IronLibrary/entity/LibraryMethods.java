package com.IronLibrary.HW3IronLibrary.entity;

import com.IronLibrary.HW3IronLibrary.Repository.AuthorRepository;
import com.IronLibrary.HW3IronLibrary.Repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
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
}




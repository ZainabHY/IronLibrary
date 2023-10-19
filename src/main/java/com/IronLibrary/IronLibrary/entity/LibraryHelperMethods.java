package com.IronLibrary.IronLibrary.entity;

import com.IronLibrary.IronLibrary.LibraryMethods;
import com.IronLibrary.IronLibrary.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class LibraryHelperMethods {

    @Autowired
    LibraryMethods libraryMethods;

    @Autowired
    static IssueRepository issueRepository;

    private Boolean exit = false;
    private Boolean returnToMainMenu = false;
    Scanner scanner = new Scanner(System.in);

    private int command;


    public static final String RESET_COLOR = "\u001B[0m";
    public static final String COLOR_RED = "\u001B[31;2m";
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_YELLOW = "\u001B[33m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_PURPLE = "\u001B[35m";
    public static final String COLOR_CYAN = "\u001B[36m";

    public void userInteraction() {
//        greeting();
        while (exit == false) {
            displayMainMenu();
            executeCommandsMain(introduceCommand());
        }
    }

    // To reach managing books Menu
    public void userInteractionManage() {
        while (returnToMainMenu == false) {
            managingBooksMenu();
            executeCommandsManage(introduceCommand());
        }
    }

    // To reach List books Menu
    public void userInteractionList() {
        while (returnToMainMenu == false) {
            listBooksMenu();
            executeCommandsList(introduceCommand());
        }
    }

    // To reach searching books Menu
    public void userInteractionSearch() {
        while (returnToMainMenu == false) {
            searchBooksMenu();
            executeCommandsSearch(introduceCommand());
        }
    }

    public void greeting() {
        System.out.println();
        System.out.println();
        System.out.println(COLOR_BLUE + ">>>>           Welcome to Iron Library           <<<<" + RESET_COLOR);
        System.out.println("******************************************************\n\n");
    }

    private void displayMainMenu(){

        System.out.println(COLOR_BLUE + "╔════════════════════════════════════════════════════╗");
        System.out.println("║        " + "               MENU                " + "         ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ " + "1. Managing Books" + "                                  ║");
        System.out.println("║ " + "2. List Books    " + "                                  ║");
        System.out.println("║ " + "3. Search Book" + "                                     " + "║");
        System.out.println("║ " + "4. Exit" + "                     " + "                       ║");
        System.out.println("╚════════════════════════════════════════════════════╝" + RESET_COLOR);
    }

    private void managingBooksMenu(){

        System.out.println(COLOR_BLUE + "╔════════════════════════════════════════════════════╗");
        System.out.println("║        " + "        Managing Books Menu        " + "         ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ " + "1. Add a book" + "                                      ║");
        System.out.println("║ " + "2. Return Book    " + "                                 ║");
        System.out.println("║ " + "3. Issue book to student" + "                       " + "    ║");
        System.out.println("║ " + "4. Return to main menu" + "                             ║");
        System.out.println("╚════════════════════════════════════════════════════╝" + RESET_COLOR);
    }
    private void listBooksMenu(){

        System.out.println(COLOR_BLUE + "╔════════════════════════════════════════════════════╗");
        System.out.println("║        " + "         List Books Menu           " + "         ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ " + "1. List all books along with author" + "                ║");
        System.out.println("║ " + "2. List books to be returned today" + "                 ║");
        System.out.println("║ " + "3. List books by USN" + "                               " + "║");
        System.out.println("║ " + "4. Return to main menu" + "                             ║");
        System.out.println("╚════════════════════════════════════════════════════╝" + RESET_COLOR);
    }
    private void searchBooksMenu(){

        System.out.println(COLOR_BLUE + "╔════════════════════════════════════════════════════╗");
        System.out.println("║        " + "        Search Books Menu          " + "         ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ " + "1. Search book by title" + "                            ║");
        System.out.println("║ " + "2. Search book by category" + "                         ║");
        System.out.println("║ " + "3. Search book by Author" +"                           " + "║");
        System.out.println("║ " + "4. Return to main menu" + "                             ║");
        System.out.println("╚════════════════════════════════════════════════════╝" + RESET_COLOR);
    }

    public int introduceCommand() {
        System.out.print("\nEnter your choice: ");
        command = validateNumber();
        System.out.println();
        return command;
    }

    public void executeCommandsMain(int command){
        if (command == 1) {
            userInteractionManage();
        } else if (command == 2) {
            userInteractionList();
        } else if (command == 3) {
            userInteractionSearch();
        } else if (command == 4) {
            exit();
        } else {
            System.out.print("Please enter one of the possible available commands: from 1 to 4");
            executeCommandsMain(introduceCommand());
        }
    }
    public void executeCommandsManage(int command){
        if (command == 1) {
            addBook();
        } else if (command == 2) {
            returnBook();
        } else if (command == 3) {
            issueBook();
        } else if (command == 4) {
            returnToMainMenu();
        } else {
            System.out.print("Please enter one of the possible available commands: from 1 to 4");
            executeCommandsManage(introduceCommand());
        }
    }
    public void executeCommandsList(int command){
        if (command == 1) {
            listBooksWithAuthor();
        } else if (command == 2) {
//            getBooksToBeReturnedToday();
        } else if (command == 3) {
            listBooksByStudentNumber();
        } else if (command == 4) {
            returnToMainMenu();
        } else {
            System.out.print("Please enter one of the possible available commands: from 1 to 4");
            executeCommandsList(introduceCommand());
        }
    }
    public void executeCommandsSearch(int command){
        if (command == 1) {
            searchBookByTitle();
        } else if (command == 2) {
            searchBookByCategory();
        } else if (command == 3) {
            searchBookByAuthor();
        } else if (command == 4) {
            returnToMainMenu();
        } else {
            System.out.print("Please enter one of the possible available commands: from 1 to 4");
            executeCommandsSearch(introduceCommand());
        }
    }

    public void addBook() {
        String isbn;
        String title;
        String category;
        String authorName;
        String email;
        int numberOfBooks;
        System.out.print("\nEnter ISBN:");
        System.out.println(COLOR_CYAN + " (in Format: 978-9-51-045690-3)" + RESET_COLOR);
        isbn = validateIsbn();
        System.out.print("\nEnter title: ");
        title = validateString();
        System.out.print("\nEnter category: ");
        category = validateString();
        System.out.print("\nEnter Author name: ");
        authorName = validateName();
        while(libraryMethods.isAuthorExistent(authorName)){
            System.out.println(COLOR_YELLOW + "The Author "+ authorName + " already exists" + RESET_COLOR);
            System.out.print("\nPlease enter Author name: ");
            authorName = validateName();
        }
        System.out.print("\nEnter Author email: ");
        email = validateEmail();
        System.out.print("\nEnter number of books: ");
        numberOfBooks = validateNumber();
        Book book1 = new Book(isbn, authorName, title, category, numberOfBooks);
        if (libraryMethods.insertBook(book1)) {
            System.out.println(COLOR_YELLOW + "\nWe already have some copies of book " + book1 +"\n" + RESET_COLOR);
            System.out.println(COLOR_PURPLE + ">> " + numberOfBooks + " will be added to our database! " + RESET_COLOR);
            System.out.println("\n----------------------------------\n");
        } else {
            Author author1 = new Author(authorName, email, book1);
            libraryMethods.insertAuthor(author1);
            System.out.println(COLOR_PURPLE + "\n>> We have added your book " + title +" to our database! "+ RESET_COLOR);
            System.out.println("\n----------------------------------\n");
        }

    }

    public String validateName() {
        String name = null;
        while (name == null || name.isEmpty()) {
            System.out.println(COLOR_CYAN + "(minimum of 2 words)" + RESET_COLOR);
            name = scanner.nextLine();
            if (!isValidName(name)) {
                System.out.println(COLOR_RED + "Invalid name. Please enter a name with only letters and at least two words." + RESET_COLOR);
                name = "";
            }
        }
        return name;
    }

    private boolean isValidName(String name) {
        return name.matches("^[A-Za-z]+(\\s[A-Za-z]+)+$");
    }

    private String validateIsbn() {
        String isbn = null;
        while (isbn == null || isbn.isEmpty() || !isValidIsbn(isbn)) {

            isbn = scanner.nextLine();
            if (!isValidIsbn(isbn)) {
                System.out.println(COLOR_RED + "Please provide a valid ISBN" + RESET_COLOR);
            }
        }
        return isbn;
    }

    private boolean isValidIsbn(String isbn) {
        return isbn.matches("\\d\\d\\d-\\d-\\d\\d-\\d\\d\\d\\d\\d\\d-\\d");
    }

    private String validateBook() {
        String isbn = null;
        while (isbn == null || libraryMethods.getBookById(isbn) == null) {
            isbn = scanner.nextLine();
            if (libraryMethods.getBookById(isbn) == null) {
                System.out.println(COLOR_RED + "Please provide a valid ISBN" + RESET_COLOR);
            }
        }
        return isbn;
    }


    public String validateEmail() {
        String email = null;
        while (email == null || email.isEmpty() || !isValidEmail(email)) {

            email = scanner.nextLine();
            if (!isValidEmail(email)) {
                System.out.println(COLOR_RED + "Invalid email address. Please enter a valid email address." + RESET_COLOR);
            }
        }
        return email;
    }

    public String validateString() {
        String input = null;
        while (input == null || input.isEmpty()) {
            input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println(COLOR_RED + "Cannot be empty. Please enter it again." + RESET_COLOR);
            }
        }
        return input;
    }

    private boolean isValidEmail(String email) {
        // Regular expression pattern to validate email addresses
        String pattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(pattern);
    }

    public int validateNumber() {
        String input = scanner.nextLine();
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(COLOR_RED + "Invalid input. Please enter a valid number." + RESET_COLOR);
            number = validateNumber();
        }
        return number;
    }

    private Integer validateIssue() {
        String input = scanner.nextLine();
        int issueId = Integer.parseInt(input);
        while (libraryMethods.getIssueById(issueId) == null) {
            System.out.println(COLOR_RED + "Please provide a valid Issue ID" + RESET_COLOR);
        }
        return issueId;
    }

    public void listBooksWithAuthor() {
        List<Author> authorsList = libraryMethods.getAllAuthors();
        if (authorsList == null) {
            System.out.println(COLOR_YELLOW + "\nSorry, No books in this library yet" + RESET_COLOR);
            System.out.println("\n----------------------------------\n");
        } else {
            System.out.println(COLOR_PURPLE + ">> List of books: " + RESET_COLOR);
            System.out.printf("\n\n"+COLOR_GREEN + "%-17s   %-20s   %-10s   %-8s   %-20s   %-20s   %n", "Book ISBN", "Book Title", "Category", "Quantity", "Author name", "Author email" + RESET_COLOR);
            for (Author a : authorsList) {
                System.out.printf("%-17s   %-20s   %-10s   %-8d   %-20s   %-20s   %n", a.getPublishedBook().getIsbn(), a.getPublishedBook().getTitle(), a.getPublishedBook().getCategory(), a.getPublishedBook().getQuantity(), a.getName(), a.getEmail());
                System.out.println("\n----------------------------------\n");
            }
            System.out.println("\n\n");
        }
    }


//    public List<Book> getBooksToBeReturnedToday() {
//        List<Book> booksToBeReturned = new ArrayList<>();
//
//        //Get current date
//        LocalDate currentDate = LocalDate.now();
//        String currentDateString = String.valueOf(currentDate);
//
//        // Retrieve the list of issued books should be returned today
//        List<Issue> issuedBooks = issueRepository.findAllByReturnDate(currentDateString);
//
//        // Iterate ove the issued books list and add them to the booksToBeReturned list
//        for(Issue issue : issuedBooks)
//        {
//            booksToBeReturned.add(issue.getIssueBook());
//        }
//
//        return booksToBeReturned;
//    }

    public void searchBookByCategory() {
        String filter = "category";
        searchByString(filter);
    }

    public void searchBookByTitle() {
        String filter = "title";
        searchByString(filter);
    }

    public void searchBookByAuthor() {
        String filter = "author";
        searchByString(filter);
    }

    public void searchByString(String str) {
        String param;
        Book foundBook = null;
        List<Book> bookList = new ArrayList<Book>();
        if (str == "title") {
            System.out.print("Enter title: ");
            param = validateString();
            bookList = libraryMethods.getBookByTitle(param);

        } else if (str == "author") {
            System.out.print("Enter Author Name: ");
            param = validateString();
            foundBook = libraryMethods.getBookByAuthor(param);

        } else {
            System.out.print("Enter category: ");
            param = validateString();
            bookList = libraryMethods.getBookByCategory(param);
        }

    // Book is found
        if (bookList != null) {
            System.out.println(COLOR_PURPLE + "\n>> The book is found! " + "\n\n");
            System.out.printf(COLOR_GREEN + "%-17s  %-20s   %-10s   %-8s   %n", "Book ISBN", "Book Title", "Category", "Quantity" + RESET_COLOR);
            for(Book b : bookList) {
                System.out.printf("%-17s  %-20s   %-10s   %-8s   %n", b.getIsbn(), b.getTitle(), b.getCategory(), b.getQuantity());
            }
            System.out.println("\n");
        }


        else if (str == "category" && bookList != null) {
            System.out.println();
            System.out.println(COLOR_PURPLE + ">> List of books: " + RESET_COLOR);
            System.out.println("\n\n");
            System.out.printf(COLOR_GREEN + "%-17s  %-20s   %-10s   %-8s   %n", "Book ISBN", "Book Title", "Category", "Quantity" + RESET_COLOR);
            for (Book b : bookList) {
                System.out.printf("%-17s  %-20s   %-10s   %-8s   %n", b.getIsbn(), b.getTitle(), b.getCategory(), b.getQuantity());
            }
            System.out.println("\n");
        }

        // Book is NOT found
        else {
            System.out.println(COLOR_YELLOW + "\nSorry, We didn't found your book " + RESET_COLOR);
        }
        System.out.println("\n----------------------------------\n");
    }

    public void issueBook() {
        String usn;
        String studentName;
        String issueDate;
        String returnDate;
        String isbn;
        Book book;

        // Get current date
        LocalDate currentDate = LocalDate.now();
// Get date after 7 days
        LocalDate dateAfter7Days = currentDate.plusDays(8); // Change the plusDays value to 6 instead of 7

// Add a check to adjust the return date to the next year if necessary
        if (dateAfter7Days.getYear() != currentDate.getYear()) {
            dateAfter7Days = dateAfter7Days.withYear(currentDate.getYear());
        }

// Format the date as strings
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

// Save them in issueDate and returnDate as Strings
        returnDate = dateAfter7Days.format(formatter);

        System.out.print("\nEnter usn: ");
        usn = validateString();
        System.out.print("\nEnter student name: ");
        studentName = validateName();
        System.out.print("\nEnter issue date: ");
        issueDate = validateString();
        System.out.print("\nEnter ISBN: ");
        isbn = validateBook();


        // Check if the ISBN already exists in the issue table
        if (libraryMethods.isISBNAlreadyIssued(isbn)) {
            System.out.println(COLOR_YELLOW + "\nSorry, this book with ISBN " + isbn + " has already been issued to another student" + RESET_COLOR);
            System.out.println("Please enter another ISBN");
        }
        else {
            book = libraryMethods.getBookById(isbn);
            if (book.getQuantity() == 0) {
                System.out.println(COLOR_YELLOW + "\nSorry, There are no available copies of this book: " + book);
                System.out.println(" You may choose another book" + RESET_COLOR);
            }
            else {
                Student stu = new Student(usn, studentName);
                libraryMethods.insertStudent(stu);
                Issue issue = new Issue(issueDate, returnDate, stu, book);
                libraryMethods.insertIssue(issue);
                System.out.println(COLOR_PURPLE + "\n>> We have issued your book! " + book + RESET_COLOR);

                System.out.println(COLOR_PURPLE + ">> List of books: " + RESET_COLOR);
                System.out.println("\n\n");
                System.out.printf(COLOR_GREEN + "%-17s  %-20s   %-10s   %-8s   %n", "Book ISBN", "Book Title", "Category", "Quantity" + RESET_COLOR);
                    System.out.printf("%-17s  %-20s   %-10s   %-8s   %n", book.getIsbn(), book.getTitle(), book.getCategory(), book.getQuantity());
                System.out.println("\n");
                System.out.println(COLOR_PURPLE + "\n>> Return Date: " + returnDate + RESET_COLOR);
                System.out.println("\n----------------------------------\n");
            }
        }
    }

    public void listBooksByStudentNumber() {
        String usn;
        System.out.print("Enter usn: ");
        usn = validateString();
        List<Issue> issueList = libraryMethods.findIssueByStudentNumber(usn);
        if (issueList != null) {
            System.out.println();
            System.out.println(COLOR_PURPLE + ">> List of books: " + RESET_COLOR);

            for (Issue issue : issueList) {
                System.out.println("\n\n");
                System.out.printf(COLOR_GREEN + "%-20s   %-15s   %-10s   %-10s   %n", "Book Title", "Student Name", "Return Date", "Issue ID" + RESET_COLOR);
                System.out.printf("%-20s   %-15s   %-10s   %-10s   %n", issue.getIssueBook().getTitle(), issue.getIssueStudent().getName(), issue.getReturnDate(), issue.getIssueId());
            }
            System.out.println("\n");
        } else {
            System.out.println(COLOR_RED + "There is no student matching that usn. Please try again.." + RESET_COLOR);
            listBooksByStudentNumber();
        }
        System.out.println("\n----------------------------------\n");
    }

    public void returnBook() {
        Integer issueId;
        System.out.print("Enter issue ID:");
        issueId = validateIssue();
        libraryMethods.deleteIssue(issueId);
        System.out.println(COLOR_PURPLE + "\n >> Thank you for returning the book! " + RESET_COLOR);
    }

    public void exit(){
        exit=true;
        scanner.close();
        System.out.println("\n\n******************************************************");
        System.out.println(COLOR_BLUE +">>>>    Thank you for using the Iron Library!     <<<<\n\n" + RESET_COLOR);
    }

    public void returnToMainMenu(){
//        returnToMainMenu = true;
        userInteraction();
    }

}
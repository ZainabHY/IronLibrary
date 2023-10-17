package com.IronLibrary.HW3IronLibrary.entity;

import java.util.Scanner;

// This class holds the methods to arrange the code
// including sub-menus
public class LibraryHelperMethods {
    static Scanner scanner = new Scanner(System.in);
    LibraryMethods libraryMethods = new LibraryMethods();

    // 1. Managing Books Menu
    public void managingBooksMenu()
    {
        // Add a book
        // Remove a book
        // Update book information
        // Issue book to student

        boolean returnToMainMenu = false;
        int choice;

        while (!returnToMainMenu) {
            System.out.println("\n\u001B[35mManaging Books Menu\u001B[0m");

            System.out.println(">> Please choose one of the commands to execute:");
            System.out.println("------------------------------------------------");

            System.out.println("1. Add a book");
            System.out.println("2. Remove a book");
            System.out.println("3. Update book information");
            System.out.println("4. Issue book to student");
            System.out.println("5. Return to main menu");

            System.out.print("\nYour choice: ");

            // Taking user's choice
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 1 || choice > 5) {
                        throw new NumberFormatException();
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input. Please enter a number from 1 to 5: ");
                }
            } // End of taking user's choice

            switch (choice) {
                case 1:
                    libraryMethods.addBook();
                    break;

                case 2:
                    libraryMethods.removeBook();
                    break;

                case 3:
                    libraryMethods.updateBook();
                    break;

                case 4:
                    libraryMethods.issueBookToStudent();
                    break;

                case 5:
                    returnToMainMenu = true;
                    break;
            }
        }

        System.out.println("\n\u001B[33mReturning to the main menu...\n\u001B[0m");

    }

    // 2. List Books Menu
    public void listBooksMenu()
    {
      // Contains:
        // List all books along with author
        // List books to be returned today
        // List books by USN
        // Book recommendation

        boolean returnToMainMenu = false;
        int choice;

        while (!returnToMainMenu) {
            System.out.println("\n\u001B[35mSList Books Menu\u001B[0m");

            System.out.println(">> Please choose one of the commands to execute:");
            System.out.println("------------------------------------------------");

            System.out.println("1. List all books along with author");
            System.out.println("2. List books to be returned today");
            System.out.println("3. List books by USN");
            System.out.println("4. Book recommendation");
            System.out.println("5. Return to main menu");

            System.out.print("\nYour choice: ");

            // Taking user's choice
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 1 || choice > 5) {
                        throw new NumberFormatException();
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input. Please enter a number from 1 to 4: ");
                }
            } // End of taking user's choice

            switch (choice) {
                case 1:
                    libraryMethods.listAllBooksWithAuthors();
                    break;

                case 2:
                    libraryMethods.listBooksReturnedToday();
                    break;

                case 3:
                    libraryMethods.listAllBooksByUsn();
                    break;

                case 4:
                    libraryMethods.bookRecommendation();
                    break;

                case 5:
                    returnToMainMenu = true;
                    break;
            }
        }

        System.out.println("\n\u001B[33mReturning to the main menu...\n\u001B[0m");


    }

    // 3. Search Book Menu
    public void searchBookMenu()
    {
        // Contains:
        // Search book by title
        // Search book by category
        // Search book by Author

        boolean returnToMainMenu = false;
        int choice;

        while (!returnToMainMenu) {
            System.out.println("\n\u001B[35mSearch Book Menu\u001B[0m");

            System.out.println(">> Please choose one of the commands to execute:");
            System.out.println("------------------------------------------------");

            System.out.println("1. Search book by title");
            System.out.println("2. Search book by category");
            System.out.println("3. Search book by Author");
            System.out.println("4. Return to main menu");

            System.out.print("\nYour choice: ");

            // Taking user's choice
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 1 || choice > 4) {
                        throw new NumberFormatException();
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input. Please enter a number from 1 to 4: ");
                }
            } // End of taking user's choice

            switch (choice) {
                case 1:
                    libraryMethods.searchBookByTitle();
                    break;

                case 2:
                    libraryMethods.searchBookByCategory();
                    break;

                case 3:
                    libraryMethods.searchBookByAuthor();
                    break;

                case 4:
                    returnToMainMenu = true;
                    break;
            }
        }

        System.out.println("\n\u001B[33mReturning to the main menu...\n\u001B[0m");
    }


}


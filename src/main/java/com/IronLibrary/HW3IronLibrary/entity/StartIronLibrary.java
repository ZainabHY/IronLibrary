package com.IronLibrary.HW3IronLibrary.entity;

import java.util.Scanner;

public class StartIronLibrary {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        LibraryMethods libraryMethods = new LibraryMethods();
        LibraryHelperMethods helperMethods = new LibraryHelperMethods();

        System.out.println("\n\u001B[34m>>>> Welcome to Iron Library <<<<\u001B[0m");
        System.out.println("***************************************************");

        boolean exit = false;
        int choice;

        while (!exit) {
            System.out.println("\u001B[33m>> Please choose one of the lists to view its commands\u001B[0m");
            System.out.println("----------------------------------------------------");

            System.out.println("1. Search Book");
            System.out.println("2. List Books");
            System.out.println("3. Managing Books");
            System.out.println("4. Exit");

            System.out.print("\nYour choice: ");

            // Taking user's choice
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 1 || choice > 4)
                        throw new NumberFormatException();
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("You should enter from 1 to 4. Please try again: ");
                }
            } // End of taking user's choice

            switch (choice) {
                case 1:
                    helperMethods.searchBookMenu();
                    break;


                case 2:
                    helperMethods.listBooksMenu();
                    break;

                case 3:
                    helperMethods.managingBooksMenu();
                    break;

                case 4:
                    exit = true;
                    System.out.println("\n\u001B[35mThank you for using the Iron Library!\u001B[0m");
                    break;
            }
        }

    }
}
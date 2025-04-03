package com.flashcard;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n****************************");
        System.out.println(" Welcome to Flashcards CLI! ");
        System.out.println("****************************\n");

        Scanner sc = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("What do you want to do?");
            System.out.println("1. Study");
            System.out.println("2. Edit my collections");
            System.out.println("3. Quit");
            System.out.print("Your choice: ");

            int option = sc.nextInt();
            switch (option) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.err.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

}

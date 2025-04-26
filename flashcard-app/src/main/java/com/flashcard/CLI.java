package com.flashcard;

public class CLI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void printWelcome() {
        System.out.println(ANSI_YELLOW + "\n*******************************");
        System.out.println(" Welcome to Flashcards CLI! ^_^");
        System.out.println("*******************************\n" + ANSI_RESET);
    }

    public static void printMainMenu() {
        System.out.println(ANSI_BLUE + "What do you want to do?" + ANSI_RESET);
        System.out.println("1. Study");
        System.out.println("2. Edit");
        System.out.println("3. Quit");
        System.out.print(ANSI_BLUE + "Your choice: " + ANSI_RESET);
    }

    public static void printGoodbye() {
        System.out.println(ANSI_YELLOW + "Goodbye! :(" + ANSI_RESET);
    }

    public static void printInvalidChoice() {
        System.out.println(ANSI_RED + "Invalid choice! " + ANSI_RESET);
    }

    public static void printEditMenu() {
        System.out.println("1. Create a new deck");
        System.out.println("2. Edit deck");
        System.out.println("3. Delete deck");
        System.out.println("0. Back");
        System.out.print(ANSI_BLUE + "Your choice : " + ANSI_RESET);
    }

    public static void printEditDeckMenu() {
        System.out.println("\n1. Add a flashcard");
        System.out.println("2. Edit a flashcard");
        System.out.println("3. Delete a flashcard");
        System.out.println("0. Back");
        System.out.print(ANSI_BLUE + "Your choice : " + ANSI_RESET);
    }

}

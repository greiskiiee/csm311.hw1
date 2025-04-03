package com.flashcard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Deck> decks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n*******************************");
        System.out.println(" Welcome to Flashcards CLI! ^_^");
        System.out.println("*******************************\n");

        boolean running = true;
        while (running) {
            System.out.println("What do you want to do?");
            System.out.println("1. Study");
            System.out.println("2. Edit");
            System.out.println("3. Quit");
            System.out.print("Your choice : ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    study();
                    break;
                case 2:
                    edit();
                    break;
                case 3:
                    System.out.println("Goodbye! :(");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter from 1-3");
            }
        }
    }

    private static void edit() {
        System.out.print("Enter a new deck name:");
        scanner.nextLine();
        String deckName = scanner.nextLine();

        Deck newDeck = new Deck(deckName);
        decks.add(newDeck);
        System.out.println("New deck '" + deckName + "' created!\n");

        while (true) {
            System.out.println("Enter the question for the flashcard (or 'done' to stop):");
            String question = scanner.nextLine();
            if ("done".equals(question)) {
                break;
            }

            System.out.println("Enter the answer for the flashcard:");
            String answer = scanner.nextLine();

            Flashcard flashcard = new Flashcard(question, answer);
            newDeck.addFlashcard(flashcard);
            System.out.println("\tFlashcard added!");
        }
    }

    private static void study() {
        if (decks.isEmpty()) {
            System.out.println("\tYou have no decks available. Please create a new deck.");
            return;
        }

    }

}

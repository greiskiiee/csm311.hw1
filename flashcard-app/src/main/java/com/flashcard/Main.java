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

        //todo: ner hooson esehiig shalga
        //
        Deck newDeck = new Deck(deckName);
        decks.add(newDeck);
        System.out.println("New deck '" + deckName + "' created!\n");

        //todo: flashcard nemeh esehiig asuuh
        //
        while (true) {
            System.out.print("Enter the question for the flashcard (or 'done' to stop): ");
            String question = scanner.nextLine();
            if ("done".equals(question)) {
                break;
            }

            System.out.print("Enter the answer for the flashcard: ");
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

        System.out.println("Select the deck to study: ");
        for (int i = 0; i < decks.size(); i++) {
            System.out.println((i + 1) + ". " + decks.get(i).getDeckName());
        }
        int deckToStudy = scanner.nextInt();
        scanner.nextLine();

        if (deckToStudy >= 1 && deckToStudy <= decks.size()) {
            Deck selectedDeck = decks.get(deckToStudy - 1);

            if (selectedDeck.hasFlashcards()) {
                studyDeck(selectedDeck);
            } else {
                System.out.println("This deck has no flashcard");
            }
        } else {
            System.out.println("Invalid choice! Please enter from 1-" + decks.size());
        }

    }

    private static void studyDeck(Deck deck) {
        System.out.println("Selected '" + deck.getDeckName() + "'");

        int i = 1;

        for (Flashcard flashcard : deck.getFlashcards()) {
            System.out.println("Card " + i + "-> " + flashcard.getQuestion());
            System.out.print("Your answer: ");
            String myAnswer = scanner.nextLine();
            System.out.println("Press Enter to see the result.");
            scanner.nextLine();

            if (myAnswer.equals(flashcard.getAnswer())) {
                System.out.println("[^_-] Correct");
            } else {
                System.out.println("[X_X] Incorrect. Answer was '" + flashcard.getAnswer() + "'");
            }
            System.out.println("-----------");
            i++;
        }
    }

}

package com.flashcard;

import java.util.List;
import java.util.Scanner;

public class StudyDeck {

    private List<Deck> decks;
    private Scanner scanner = new Scanner(System.in);
    private CardOrganizer cardOrganizer;

    public StudyDeck(List<Deck> decks, Scanner scanner) {
        this.decks = decks;
        this.scanner = scanner;
    }

    public void study() {
        if (decks.isEmpty()) {
            System.out.println(CLI.ANSI_RED + "\tYou have no decks available. Please create a new deck." + CLI.ANSI_RESET);
            return;
        }

        System.out.println("Select the deck to study: ");

        for (int i = 0; i < decks.size(); i++) {
            System.out.println((i + 1) + ". " + decks.get(i).getDeckName());
        }

        System.out.print(CLI.ANSI_BLUE + "Your choice: " + CLI.ANSI_RESET);
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
            CLI.printInvalidChoice();
        }

    }

    private void studyDeck(Deck deck) {
        System.out.println(CLI.ANSI_BLUE + "Selected '" + deck.getDeckName() + "'" + CLI.ANSI_RESET);

        int repetition = 1;

        System.out.print("Repetition (1-3): ");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                break;
            }

            try {
                int value = Integer.parseInt(input);
                if (value >= 1 && value <= 3) {
                    repetition = value;
                    break;
                } else {
                    System.out.print(CLI.ANSI_RED + "Please enter a number from 1-3: " + CLI.ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.print(CLI.ANSI_RED + "Please enter a number from 1-3: " + CLI.ANSI_RESET);
            }
        }

        for (Flashcard flashcard : deck.getFlashcards()) {
            flashcard.initializeMistakes(repetition);
        }

        int i;
        for (i = 0; i < deck.getSize(); i++) {
            Flashcard flashcard = deck.getFlashcards().get(i);
            System.out.println("Card " + (i + 1) + "-> Q: " + flashcard.getQuestion());

            boolean answeredCorrectly = false;
            int r;
            for (r = 0; r < repetition; r++) {
                System.out.print("Your answer: ");
                String myAnswer = scanner.nextLine();

                if (myAnswer.equals(flashcard.getAnswer())) {
                    System.out.println(CLI.ANSI_GREEN + "[^_-] Correct" + CLI.ANSI_RESET);
                    flashcard.markMistake(r, false);
                    answeredCorrectly = true;
                    break;
                } else {
                    System.out.println(CLI.ANSI_RED + "[X_X] Incorrect. Would you like help (y/n) ? " + CLI.ANSI_RESET);
                    String help = scanner.nextLine().trim().toLowerCase();

                    if (help.equals("y")) {
                        System.out.println("Help --> " + flashcard.getHelp());
                    } else if (!help.equals("n")) {
                        System.out.print("Please enter 'y' or 'n': ");
                        System.out.print("\n");
                        r--;
                        continue;
                    }
                    flashcard.markMistake(r, true);;

                }
            }
            if (!answeredCorrectly) {
                System.out.println(CLI.ANSI_RED + "[X_X] You didn't get the answer right after " + repetition + " attempts." + CLI.ANSI_RESET);
                System.out.println("Correct answer: " + flashcard.getAnswer());
            }

            System.out.println("-----------");

        }
        cardOrganizer.sortingDeck(deck);

    }

}

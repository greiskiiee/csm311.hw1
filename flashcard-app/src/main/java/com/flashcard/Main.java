package com.flashcard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Deck> decks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static RecentMistakesFirstSorter sorting;

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

        while (true) {
            System.out.println("1. Create a new deck");
            System.out.println("2. Edit deck");
            System.out.println("0. Back");
            System.out.print("Your choice : ");
            int editChoice = scanner.nextInt();
            scanner.nextLine();
            Deck newDeck;
            switch (editChoice) {
                //1.Create a new deck
                case 1:
                    System.out.print("Enter a new deck name: ");
                    String deckName = scanner.nextLine();

                    if (deckName.trim().equals("")) {
                        System.out.println("Deck name cannot be null");
                    } else {
                        newDeck = new Deck(deckName);
                        decks.add(newDeck);
                        System.out.println("\tNew deck '" + deckName + "' created!\n");
                    }

                    break;

                //2. Edit deck
                case 2:
                    if (decks.isEmpty()) {
                        System.out.println("\tYou have no decks available. Please create a new deck.");
                        return;
                    }

                    System.out.println("Select the deck to edit: ");
                    for (int i = 0; i < decks.size(); i++) {
                        System.out.println((i + 1) + ". " + decks.get(i).getDeckName());
                    }
                    int deckToStudy = scanner.nextInt();
                    scanner.nextLine();

                    if (deckToStudy >= 1 && deckToStudy <= decks.size()) {
                        Deck selectedDeck = decks.get(deckToStudy - 1);
                        editDeck(selectedDeck);
                    } else {
                        System.out.println("Invalid choice! Please enter from 1-" + decks.size());
                    }
                    break;

                case 0:
                    return;
                default:
                    System.out.println("Invalid choice");

            }
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

        System.out.print("Your choice: ");
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
                    System.out.print("Please enter a number from 1-3: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a number from 1-3: ");
            }
        }

        int i;
        for (i = 0; i < deck.getSize(); i++) {
            Flashcard flashcard = deck.getFlashcards().get(i);
            System.out.println("Card " + (i + 1) + "-> " + flashcard.getQuestion());

            boolean answeredCorrectly = false;

            while (repetition > 0) {
                System.out.print("Your answer: ");
                String myAnswer = scanner.nextLine();

                if (myAnswer.equals(flashcard.getAnswer())) {
                    System.out.println("[^_-] Correct");
                    answeredCorrectly = true;
                    break;
                } else {
                    System.out.println("[X_X] Incorrect.");
                    System.out.print("Would you like help (y/n) ? ");
                    String help = scanner.nextLine().trim().toLowerCase();

                    if (help.equals("y")) {
                        System.out.println("Help --> " + flashcard.getHelp());
                    } else if (!help.equals("n")) {
                        System.out.print("Please enter 'y' or 'n': ");
                        continue;
                    }
                    flashcard.incrementMistakes();
                    repetition--;
                }
            }

            if (!answeredCorrectly) {
                System.out.println("Answer was '" + flashcard.getAnswer() + "'");
            }

            System.out.println("-----------");
            int mistakes = flashcard.getMistakes();

        }

        // System.out.println("Mistakes: " + mistakes);
        // System.out.println(sorting.organize(deck.getFlashcards()));
    }

    private static void editDeck(Deck deck) {
        System.out.println("Selected '" + deck.getDeckName() + "'");
        int i;

        int flaschcardCount = deck.getSize();
        while (true) {
            for (i = 0; i < deck.getSize(); i++) {
                Flashcard flashcard = deck.getFlashcards().get(i);
                System.out.println("Card " + (i + 1) + ":");
                System.out.println("\t Q: " + flashcard.getQuestion());
                System.out.println("\t A: " + flashcard.getAnswer());
                System.out.println("\t H: " + flashcard.getHelp());
            }

            System.out.println("\n1. Add a flashcard");
            System.out.println("2. Edit a flashcard");
            System.out.println("3. Delete a flashcard");
            System.out.println("0. Back");
            System.out.print("Your choice : ");
            int secondChoice = scanner.nextInt();
            scanner.nextLine();

            switch (secondChoice) {
                case 1:
                    System.out.print("Enter the question for the flashcard no" + (flaschcardCount + 1) + ": ");
                    String question = scanner.nextLine();

                    System.out.print("Enter the answer for the flashcard no" + (flaschcardCount + 1) + ": ");
                    String answer = scanner.nextLine();

                    System.out.print("Enter the help for the flashcard no" + (flaschcardCount + 1) + ": ");
                    String help = scanner.nextLine();

                    Flashcard flashcard = new Flashcard(question, answer, help);
                    deck.addFlashcard(flashcard);
                    System.out.println("\tFlashcard added to '" + deck.getDeckName() + "' !");
                    flaschcardCount++;
                    break;
                case 2:
                    System.out.print("Enter the flashcard number to edit: ");
                    int flashcardToEdit = scanner.nextInt();
                    scanner.nextLine();
                    if (flashcardToEdit >= 1 && flashcardToEdit <= deck.getSize()) {
                        System.out.print("Enter the new question for the flashcard no" + flashcardToEdit + ": ");
                        String newQuestion = scanner.nextLine();

                        System.out.print("Enter the new answer for the flashcard no" + flashcardToEdit + ": ");
                        String newAnswer = scanner.nextLine();

                        System.out.print("Enter the new help for the flashcard no" + flashcardToEdit + ": ");
                        String newHelp = scanner.nextLine();

                        deck.editFlashcard(flashcardToEdit - 1, newQuestion, newAnswer, newHelp);
                    } else {
                        System.out.println("There is no flashcard " + flashcardToEdit);
                    }
                    break;
                case 3:
                    System.out.print("Enter the flashcard number to delete: ");
                    int flashcardToDelete = scanner.nextInt();
                    scanner.nextLine();
                    if (flashcardToDelete >= 1 && flashcardToDelete <= deck.getSize()) {
                        deck.deleteFlashcard(flashcardToDelete - 1);
                    } else {
                        System.out.println("There is no flashcard " + flashcardToDelete);
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice!");

            }

        }

    }

}

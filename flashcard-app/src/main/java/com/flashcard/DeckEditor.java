package com.flashcard;

import java.util.List;
import java.util.Scanner;

public class DeckEditor {

    private static List<Deck> decks;
    private static Scanner scanner = new Scanner(System.in);

    public DeckEditor(List<Deck> decks, Scanner scanner) {
        this.decks = decks;
        this.scanner = scanner;
    }

    public static void edit() {
        CLI.printEditMenu();
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
                    System.out.println(CLI.ANSI_BLUE + "\tNew deck '" + deckName + "' created!\n" + CLI.ANSI_RESET);
                }

                break;

            //2. Edit deck
            case 2:
                if (decks.isEmpty()) {
                    System.out.println(CLI.ANSI_RED + "\tYou have no decks available. Please create a new deck." + CLI.ANSI_RESET);
                    return;
                }

                System.out.println(CLI.ANSI_BLUE + "Select the deck to edit: " + CLI.ANSI_RESET);
                for (int i = 0; i < decks.size(); i++) {
                    System.out.println((i + 1) + ". " + decks.get(i).getDeckName());
                }
                int deckToStudy = scanner.nextInt();
                scanner.nextLine();

                if (deckToStudy >= 1 && deckToStudy <= decks.size()) {
                    Deck selectedDeck = decks.get(deckToStudy - 1);
                    editDeck(selectedDeck);
                } else {
                    System.out.println(CLI.ANSI_RED + "Invalid choice! Please enter from 1-" + decks.size() + CLI.ANSI_RESET);
                }
                break;
            case 3:
                if (decks.isEmpty()) {
                    System.out.println(CLI.ANSI_RED + "\tYou have no decks available. Please create a new deck." + CLI.ANSI_RESET);
                    return;
                }
                for (int i = 0; i < decks.size(); i++) {
                    System.out.println((i + 1) + ". " + decks.get(i).getDeckName());
                }
                System.out.print("Enter the deck number to delete : ");
                int deleteDeck = scanner.nextInt();
                scanner.nextLine();

                if (deleteDeck >= 1 && deleteDeck <= decks.size()) {
                    decks.remove(deleteDeck - 1);
                    System.out.println(CLI.ANSI_BLUE + "Deck deleted" + CLI.ANSI_RESET);
                } else {
                    CLI.printInvalidChoice();
                }
                break;
            case 0:
                return;
            default:
                System.out.println(CLI.ANSI_RED + "Invalid choice" + CLI.ANSI_RESET);

        }
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
            CLI.printEditDeckMenu();

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
                    System.out.println(CLI.ANSI_BLUE + "\tFlashcard added to '" + deck.getDeckName() + "' !" + CLI.ANSI_RESET);
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
                    CLI.printInvalidChoice();

            }
        }
    }
}

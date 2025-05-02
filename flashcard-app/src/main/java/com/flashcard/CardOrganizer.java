package com.flashcard;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CardOrganizer {

    private static final Scanner sc = new Scanner(System.in);

    public static void sortingDeck(Deck deck) {
        System.out.println("\nStudy session finished!");
        System.out.println("Choose how to sort cards for next review:");

        while (true) {
            System.out.println("1. Random");
            System.out.println("2. Worst-first (most mistakes)");
            System.out.println("3. Recent mistakes first");
            System.out.println("4. Quit");

            System.out.print("Your choice: ");
            int sortChoice = getUserChoice();

            CardOrganizerStrategy strategy = null;

            switch (sortChoice) {
                case 1:
                    strategy = new RandomSortStrategy();
                    break;
                case 2:
                    strategy = new WorstFirstSortStrategy();
                    break;
                case 3:
                    strategy = new RecentMistakeSortStrategy();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. No sorting applied.");
                    continue;
            }

            strategy.sort(deck.getFlashcards());
            printFlashcards(deck.getFlashcards());
        }
    }

    private static int getUserChoice() {
        while (true) {
            try {
                int choice = Integer.parseInt(sc.nextLine());
                if (choice >= 1 && choice <= 4) {
                    return choice;
                } else {
                    System.out.print("Please enter 1, 2, 3, or 4: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private static void printFlashcards(List<Flashcard> flashcards) {
        System.out.println("\nSorted Flashcards:");
        for (Flashcard card : flashcards) {
            System.out.println("- " + card.getQuestion() + " (Mistakes: " + card.getTotalMistakes() + ")" + Arrays.toString(card.mistakes));
        }
    }
}

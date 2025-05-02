package com.flashcard;

import java.util.List;
import java.util.Scanner;

public class StudyDeck {

    // Картын багцуудыг хадгалах хувьсагч
    private List<Deck> decks;
    private Scanner scanner = new Scanner(System.in);
    private CardOrganizer cardOrganizer;

    // Картын багцууд болон Scanner-ийг хадгалах байгуулагч функц
    public StudyDeck(List<Deck> decks, Scanner scanner) {
        this.decks = decks;
        this.scanner = scanner;
    }

    // Картын багц тоглох функц
    public void study() {
        // Хэрвээ багц байхгүй бол мэдэгдэх
        if (decks.isEmpty()) {
            System.out.println(CLI.ANSI_RED + "\tYou have no decks available. Please create a new deck." + CLI.ANSI_RESET);
            return;
        }

        System.out.println("Select the deck to study: ");

        // Багцуудыг жагсааж харуулж байгаа хэсэг
        for (int i = 0; i < decks.size(); i++) {
            System.out.println((i + 1) + ". " + decks.get(i).getDeckName());
        }

        System.out.print(CLI.ANSI_BLUE + "Your choice: " + CLI.ANSI_RESET);
        int deckToStudy = scanner.nextInt();
        scanner.nextLine();

        // Сонгосон багцын шалгалтыг хийж, зөв бол эхлүүлэх
        if (deckToStudy >= 1 && deckToStudy <= decks.size()) {
            Deck selectedDeck = decks.get(deckToStudy - 1);

            // Багцад карт байхгүй бол мэдэгдэх
            if (selectedDeck.hasFlashcards()) {
                studyDeck(selectedDeck);
            } else {
                System.out.println("This deck has no flashcard");
            }
        } else {
            CLI.printInvalidChoice();
        }

    }

    // Багц тоглох функц
    private void studyDeck(Deck deck) {
        // Сонгосон багцын нэрийг харуулах
        System.out.println(CLI.ANSI_BLUE + "Selected '" + deck.getDeckName() + "'" + CLI.ANSI_RESET);

        int repetition = 1;

        // Давталт хийх удаагийн тоо сонгох хэсэг
        System.out.print("Repetition (1-3): ");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                break;
            }

            try {
                int value = Integer.parseInt(input);
                // Сонгосон утга 1-3 хооронд байвал зохино 		     	             
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

        // Картын багц доторх бүх картын алдаануудыг үүсгэх
        for (Flashcard flashcard : deck.getFlashcards()) {
            flashcard.initializeMistakes(repetition);
        }

        int i;
        // Картуудыг нэг нэгээр нь асуун хэрэглэгчийн хариулттай харьцуулах. -> давталтын тоогоор давтана, тусламж авч болно.

        for (i = 0; i < deck.getSize(); i++) {
            Flashcard flashcard = deck.getFlashcards().get(i);
            System.out.println("Card " + (i + 1) + "-> Q: " + flashcard.getQuestion());

            boolean answeredCorrectly = false;
            int r;
            // Хэрэглэгчийн хариултыг шалгах
            for (r = 0; r < repetition; r++) {
                System.out.print("Your answer: ");
                String myAnswer = scanner.nextLine();

                // Хэрвээ хариулт зөв бол тэмдэглэх
                if (myAnswer.equals(flashcard.getAnswer())) {
                    System.out.println(CLI.ANSI_GREEN + "[^_-] Correct" + CLI.ANSI_RESET);
                    flashcard.markMistake(r, false);
                    answeredCorrectly = true;
                    break;
                } else {
                    // Хэрвээ хариулт буруу бол тусламж авч болно
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
            // Хэрвээ хэрэглэгч асуултад нэг ч зөв хариулаагүй бол зөв хариултыг харуулах
            if (!answeredCorrectly) {
                System.out.println(CLI.ANSI_RED + "[X_X] You didn't get the answer right after " + repetition + " attempts." + CLI.ANSI_RESET);
                System.out.println("Correct answer: " + flashcard.getAnswer());
            }

            System.out.println("-----------");

        }
        // Багцын картуудыг эрэмбэлэх
        cardOrganizer.sortingDeck(deck);

    }
}

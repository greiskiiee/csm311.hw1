package com.flashcard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Deck> decks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DeckEditor deckEditor = new DeckEditor(decks, scanner);
    private static final StudyDeck studyDeck = new StudyDeck(decks, scanner);

    public static void main(String[] args) {
        CLI.printWelcome();

        boolean running = true;
        while (running) {
            CLI.printMainMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    studyDeck.study();
                    break;
                case 2:
                    deckEditor.edit();
                    break;
                case 3:
                    CLI.printGoodbye();
                    running = false;
                    break;
                default:
                    CLI.printInvalidChoice();
            }
        }
    }

}

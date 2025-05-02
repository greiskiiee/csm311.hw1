package com.flashcard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Картын багцуудыг хадгалах жагсаалт
    private static List<Deck> decks = new ArrayList<>();
    // Картын багцуудтай харьцах Scanner
    private static final Scanner scanner = new Scanner(System.in);
    // Багцыг засах объект
    private static final DeckEditor deckEditor = new DeckEditor(decks, scanner);
    // Картын багц судлах объект
    private static final StudyDeck studyDeck = new StudyDeck(decks, scanner);

    public static void main(String[] args) {
        // Тавтай морилно уу гэж харуулах
        CLI.printWelcome();

        boolean running = true;
        while (running) {
            // Гол цэсийг харуулах
            CLI.printMainMenu();
            // Хэрэглэгчийн сонголт
            int choice = scanner.nextInt();

            // Сонгосон үйлдлийн дагуу хийгдэх үйлдлүүд
            switch (choice) {
                case 1:
                    studyDeck.study(); // Карт тоглох
                    break;
                case 2:
                    deckEditor.edit(); // Багц засах
                    break;
                case 3:
                    CLI.printGoodbye(); // Баяртай гэж харуулах
                    running = false; // Программыг дуусгах
                    break;
                default:
                    CLI.printInvalidChoice(); // Буруу сонголт
            }
        }
    }

}

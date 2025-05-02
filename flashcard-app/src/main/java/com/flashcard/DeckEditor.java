package com.flashcard;

import java.util.List;
import java.util.Scanner;

public class DeckEditor {

    // Бүх багцуудыг хадгалах жагсаалт
    private static List<Deck> decks;

    // Хэрэглэгчийн оруулсан мэдээллийг унших сканнер
    private static Scanner scanner = new Scanner(System.in);

    // Багцууд болон сканнерийг оноох байгуулагч функц
    public DeckEditor(List<Deck> decks, Scanner scanner) {
        this.decks = decks;
        this.scanner = scanner;
    }

    // Засварлах үндсэн цэс (Main edit menu)
    public static void edit() {
        CLI.printEditMenu(); // Хэрэглэгчид цэсийг харуулна
        int editChoice = scanner.nextInt(); // Сонголтыг уншина
        scanner.nextLine(); // Enter товчийг арилгах
        Deck newDeck;

        switch (editChoice) {
            // 1. Шинэ багц үүсгэх
            case 1:
                System.out.print("Enter a new deck name: ");
                String deckName = scanner.nextLine();

                if (deckName.trim().equals("")) {
                    System.out.println("Deck name cannot be null");
                } else {
                    newDeck = new Deck(deckName);
                    decks.add(newDeck); // Жагсаалтад нэмнэ
                    System.out.println(CLI.ANSI_BLUE + "\tNew deck '" + deckName + "' created!\n" + CLI.ANSI_RESET);
                }
                break;

            // 2. Одоо байгаа багцыг засах
            case 2:
                if (decks.isEmpty()) {
                    System.out.println(CLI.ANSI_RED + "\tYou have no decks available. Please create a new deck." + CLI.ANSI_RESET);
                    return;
                }

                // Засах багцыг сонгоно
                System.out.println(CLI.ANSI_BLUE + "Select the deck to edit: " + CLI.ANSI_RESET);
                for (int i = 0; i < decks.size(); i++) {
                    System.out.println((i + 1) + ". " + decks.get(i).getDeckName());
                }

                int deckToStudy = scanner.nextInt();
                scanner.nextLine();

                if (deckToStudy >= 1 && deckToStudy <= decks.size()) {
                    Deck selectedDeck = decks.get(deckToStudy - 1);
                    editDeck(selectedDeck); // Сонгосон багцыг засна
                } else {
                    System.out.println(CLI.ANSI_RED + "Invalid choice! Please enter from 1-" + decks.size() + CLI.ANSI_RESET);
                }
                break;

            // 3. Багц устгах
            case 3:
                if (decks.isEmpty()) {
                    System.out.println(CLI.ANSI_RED + "\tYou have no decks available. Please create a new deck." + CLI.ANSI_RESET);
                    return;
                }

                // Бүх багцыг жагсаана
                for (int i = 0; i < decks.size(); i++) {
                    System.out.println((i + 1) + ". " + decks.get(i).getDeckName());
                }
                // Устгах багцны индекс оруулна
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

            // 0. Буцах
            case 0:
                return;

            // Бусад буруу сонголт
            default:
                System.out.println(CLI.ANSI_RED + "Invalid choice" + CLI.ANSI_RESET);
        }
    }

    // Сонгосон багц дотор флэш карт нэмэх, засах, устгах функцийг хийнэ
    private static void editDeck(Deck deck) {
        System.out.println(CLI.ANSI_BLUE + "Selected '" + deck.getDeckName() + "'" + CLI.ANSI_RESET);
        int i;

        int flaschcardCount = deck.getSize(); // Картны дугаарыг хянахад ашигласан
        while (true) {
            // Бүх картуудыг харуулна
            for (i = 0; i < deck.getSize(); i++) {
                Flashcard flashcard = deck.getFlashcards().get(i);
                System.out.println("Card " + (i + 1) + ":");
                System.out.println("\t Q: " + flashcard.getQuestion());
                System.out.println("\t A: " + flashcard.getAnswer());
                System.out.println("\t H: " + flashcard.getHelp());
            }

            CLI.printEditDeckMenu(); // Дэд цэсийг харуулна
            int secondChoice = scanner.nextInt();
            scanner.nextLine();

            switch (secondChoice) {
                // 1. Шинэ карт нэмэх
                case 1:
                    System.out.print("Enter the question for the flashcard no" + (flaschcardCount + 1) + ": ");
                    // Асуултыг гараас авна
                    String question = scanner.nextLine();

                    System.out.print("Enter the answer for the flashcard no" + (flaschcardCount + 1) + ": ");
                    // Хариултыг гараас авна
                    String answer = scanner.nextLine();

                    System.out.print("Enter the help for the flashcard no" + (flaschcardCount + 1) + ": ");
                    // Тусламжийг гараас авна
                    String help = scanner.nextLine();

                    Flashcard flashcard = new Flashcard(question, answer, help);
                    deck.addFlashcard(flashcard);
                    System.out.println(CLI.ANSI_BLUE + "\tFlashcard added to '" + deck.getDeckName() + "' !" + CLI.ANSI_RESET);
                    flaschcardCount++;
                    break;

                // 2. Одоо байгаа флэш картыг засах
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

                // 3. Карт устгах
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

                // 0. Буцах
                case 0:
                    return;

                // Буруу сонголт
                default:
                    CLI.printInvalidChoice();
            }
        }
    }
}

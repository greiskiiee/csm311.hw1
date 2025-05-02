package com.flashcard;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CardOrganizer {

    // Scanner объектын тусламжтайгаар хэрэглэгчийн оролтыг авах
    private static final Scanner sc = new Scanner(System.in);

    // Картуудаар тоглож дууссаны дараа картуудыг хэрхэн эрэмбэлэхийг хэрэглэгчээс асуух функц
    public static void sortingDeck(Deck deck) {
        System.out.println(CLI.ANSI_BLUE + "\nStudy session finished!" + CLI.ANSI_RESET); // Судалгаа дууссан тухай мэдэгдэл
        System.out.println("Choose how to sort cards for next review:"); // Картын дараагийн эрэмбийг сонгох

        // Хэрэглэгчээс эрэмбийг сонгох
        while (true) {
            System.out.println("1. Random");
            System.out.println("2. Worst-first (most mistakes)");
            System.out.println("3. Recent mistakes first");
            System.out.println("4. Quit");

            System.out.print(CLI.ANSI_BLUE + "Your choice: " + CLI.ANSI_RESET);
            int sortChoice = getUserChoice(); // Сонголт авах

            CardOrganizerStrategy strategy = null;

            // Хэрэглэгчийн сонголтын дагуу эрэмбийн стратеги сонгох
            switch (sortChoice) {
                case 1:
                    strategy = new RandomSortStrategy(); // Санамсаргүй эрэмбэ
                    break;
                case 2:
                    strategy = new WorstFirstSortStrategy(); // Хамгийн олон алдаатайгаар эрэмбэлэх
                    break;
                case 3:
                    strategy = new RecentMistakeSortStrategy(); // Сүүлд гаргасан алдаагаар эрэмбэлэх
                    break;
                case 4:
                    return; // Програм дуусгах
                default:
                    System.out.println(CLI.ANSI_RED + "Invalid choice. No sorting applied." + CLI.ANSI_RESET); // Буруу сонголт хийсэн бол
                    continue;
            }

            // Сонгосон стратегийг ашиглан картуудыг эрэмбэлэх
            strategy.sort(deck.getFlashcards());
            printFlashcards(deck.getFlashcards()); // Эрэмбэлсэн картуудыг хэвлэх
        }
    }

    // Хэрэглэгчийн сонголтыг зөвшөөрөгдсөн хүрээнд авах функц
    private static int getUserChoice() {
        while (true) {
            try {
                int choice = Integer.parseInt(sc.nextLine()); // Хэрэглэгчийн оролтыг тоогоор хөрвүүлэх
                if (choice >= 1 && choice <= 4) {
                    return choice; // Хуурамч биш зөвхөн зөв сонголт
                } else {
                    System.out.print("Please enter 1, 2, 3, or 4: "); // Зөв сонголт оруулахыг хүсэх
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: "); // Тоо биш тэмдэгт оруулсан бол
            }
        }
    }

    // Эрэмбэлсэн картуудыг хэвлэх функц
    private static void printFlashcards(List<Flashcard> flashcards) {
        System.out.println(CLI.ANSI_BLUE + "\nSorted Flashcards:" + CLI.ANSI_RESET); // Эрэмбэлсэн картууд
        for (Flashcard card : flashcards) {
            // Картын асуулт, алдаа тоо, алдаануудыг хэвлэх
            System.out.println("- " + card.getQuestion() + CLI.ANSI_RED + " (Mistakes: " + card.getTotalMistakes() + ")" + Arrays.toString(card.mistakes) + CLI.ANSI_RESET);
        }
        System.out.println("");
    }
}

package com.flashcard;

import java.util.List;

public class RecentMistakeSortStrategy implements CardOrganizerStrategy {

    // Картуудыг сүүлд гаргасан алдаагаар нь эрэмбэлэх стратеги
    @Override
    public void sort(List<Flashcard> flashcards) {
        // Алдаагаа сүүлд гаргасан картуудыг эхэнд нь байршуулж байна
        flashcards.sort((a, b) -> Boolean.compare(b.madeRecentMistake(), a.madeRecentMistake()));
        System.out.println("Flashcards sorted by recent mistakes!"); // Эрэмбэлсэн тухай мэдэгдэл
    }
}

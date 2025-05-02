package com.flashcard;

import java.util.List;

public class WorstFirstSortStrategy implements CardOrganizerStrategy {

    // Картуудыг хамгийн олон алдаатай картуудаар нь эрэмбэлэх стратеги
    @Override
    public void sort(List<Flashcard> flashcards) {
        // Хамгийн олон алдаатай картуудыг эхэнд нь байршуулж байна
        flashcards.sort((a, b) -> Integer.compare(b.getTotalMistakes(), a.getTotalMistakes()));
        System.out.println("Flashcards sorted by most mistakes first!"); // Эрэмбэлсэн тухай мэдэгдэл
    }
}

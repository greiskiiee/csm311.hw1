package com.flashcard;

import java.util.List;

public class WorstFirstSortStrategy implements CardOrganizerStrategy {

    @Override
    public void sort(List<Flashcard> flashcards) {
        flashcards.sort((a, b) -> Integer.compare(b.getTotalMistakes(), a.getTotalMistakes()));
        System.out.println("Flashcards sorted by most mistakes first!");
    }
}

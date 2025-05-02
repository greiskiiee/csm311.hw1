package com.flashcard;

import java.util.List;

public class RecentMistakeSortStrategy implements CardOrganizerStrategy {

    @Override
    public void sort(List<Flashcard> flashcards) {
        flashcards.sort((a, b) -> Boolean.compare(b.madeRecentMistake(), a.madeRecentMistake()));
        System.out.println("Flashcards sorted by recent mistakes!");
    }
}

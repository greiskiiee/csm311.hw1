package com.flashcard;

import java.util.Collections;
import java.util.List;

public class RandomSortStrategy implements CardOrganizerStrategy {

    @Override
    public void sort(List<Flashcard> flashcards) {
        Collections.shuffle(flashcards);
        System.out.println("Flashcards shuffled randomly!");
    }
}

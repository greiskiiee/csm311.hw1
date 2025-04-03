package com.flashcard;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private String deckName;
    private List<Flashcard> flashcards;

    public Deck(String deckName) {
        this.deckName = deckName;
        this.flashcards = new ArrayList<>();
    }

    public String getDeckName() {
        return deckName;
    }

    public void addFlashcard(Flashcard flashcard) {
        flashcards.add(flashcard);
    }

    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    public boolean hasFlashcards() {
        return !flashcards.isEmpty();
    }
}

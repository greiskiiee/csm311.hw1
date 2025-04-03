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

    public void deleteFlashcard(int idx) {
        flashcards.remove(idx);
    }

    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    public boolean hasFlashcards() {
        return !flashcards.isEmpty();
    }

    public int getSize() {
        return flashcards.size();
    }

    public void editFlashcard(int idx, String newQuestion, String newAnswer) {
        flashcards.get(idx).question = newQuestion;
        flashcards.get(idx).answer = newAnswer;
    }

}

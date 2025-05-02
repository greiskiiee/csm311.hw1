package com.flashcard;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    // Багцийн нэрийг хадгалах хувьсагч
    private String deckName;

    // Багц дахь бүх картуудыг хадгалах жагсаалт
    private List<Flashcard> flashcards;

    // Багцийн нэрийг оноож, хоосон картын багц үүсгэх байгуулагч функц
    public Deck(String deckName) {
        this.deckName = deckName;               // Багцын нэр онооно
        this.flashcards = new ArrayList<>();    // Флэш картуудыг хадгалах хоосон ArrayList үүсгэнэ
    }

    // Багцын нэрийг буцаах функц
    public String getDeckName() {
        return deckName;
    }

    // Шинэ карт нэмэх функц
    public void addFlashcard(Flashcard flashcard) {
        flashcards.add(flashcard); // Жагсаалтад шинэ карт нэмнэ
    }

    // Тодорхой индекс дээрх карт устгах функц
    public void deleteFlashcard(int idx) {
        flashcards.remove(idx); // Жагсаалтаас индексээр нь устгана
    }

    // Бүх картуудыг буцаах функц
    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    // Багц дотор карт байгаа эсэхийг шалгах функц
    public boolean hasFlashcards() {
        return !flashcards.isEmpty(); // Хоосон биш байвал true буцаана
    }

    // Багц дахь картын тоог буцаах функц
    public int getSize() {
        return flashcards.size(); // Жагсаалтын хэмжээ буюу нийт картын тоо
    }

    // Тухайн индекс дээрх картын агуулгыг өөрчлөх функц
    public void editFlashcard(int idx, String newQuestion, String newAnswer, String newHelp) {
        flashcards.get(idx).question = newQuestion; // Шинэ асуулт онооно
        flashcards.get(idx).answer = newAnswer;     // Шинэ хариулт онооно
        flashcards.get(idx).help = newHelp;         // Шинэ тусламж онооно
    }

}

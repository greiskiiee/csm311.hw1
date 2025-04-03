package com.flashcard;

public class Flashcard {

    String question;
    String answer;
    int mistakes;

    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.mistakes = 0;
    }
}

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

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void incrementMistakes() {
        this.mistakes++;
    }
}

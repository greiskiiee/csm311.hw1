package com.flashcard;

public class Flashcard {

    String question;
    String answer;
    String help;
    int mistakes;

    public Flashcard(String question, String answer, String help) {
        this.question = question;
        this.answer = answer;
        this.help = help;
        this.mistakes = 0;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getHelp() {
        return help;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void incrementMistakes() {
        this.mistakes++;
    }
}

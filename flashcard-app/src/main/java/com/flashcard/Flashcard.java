package com.flashcard;

public class Flashcard {

    String question;
    String answer;
    String help;
    boolean[] mistakes;

    public Flashcard(String question, String answer, String help) {
        this.question = question;
        this.answer = answer;
        this.help = help;
    }

    public void initializeMistakes(int repetition) {
        this.mistakes = new boolean[repetition];
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

    public int getTotalMistakes() {
        int count = 0;
        if (this.mistakes != null) {
            for (boolean m : this.mistakes) {
                if (m) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean[] getMistakes() {
        return this.mistakes;
    }

    public void markMistake(int repetitionIndex, boolean madeMistake) {
        if (this.mistakes != null && repetitionIndex >= 0 && repetitionIndex < mistakes.length) {
            this.mistakes[repetitionIndex] = madeMistake;
        }
    }

    public boolean madeRecentMistake() {
        if (this.mistakes != null && this.mistakes.length > 0) {
            return this.mistakes[mistakes.length - 1];
        }
        return false;
    }
}

package com.flashcard;

public class Flashcard {

    // Асуулт, хариулт, тусламж хадгалах хувьсагчууд
    String question;
    String answer;
    String help;

    // Давталтын үед гаргасан алдааг тэмдэглэх boolean массив
    boolean[] mistakes;

    // Асуулт, хариулт, тусламжийг хүлээн авч, карт үүсгэх байгуулагч (constructor) функц
    public Flashcard(String question, String answer, String help) {
        this.question = question; // Асуултыг хадгалах
        this.answer = answer;     // Зөв хариултыг хадгалах
        this.help = help;         // Тусламж хадгалах
    }

    // Давталтын тоогоор алдааны массивыг анхдагч утгаар үүсгэх функц
    public void initializeMistakes(int repetition) {
        this.mistakes = new boolean[repetition]; // давталтын тоогоор алдаа тэмдэглэх боломжтой массив үүсгэнэ
    }

    // Асуултыг буцаах getter функц
    public String getQuestion() {
        return question;
    }

    // Хариултыг буцаах getter функц
    public String getAnswer() {
        return answer;
    }

    // Тусламж буцаах getter функц
    public String getHelp() {
        return help;
    }

    // Нийт хэдэн удаа алдаа гаргасныг тооцоолж буцаах функц
    public int getTotalMistakes() {
        int count = 0;
        if (this.mistakes != null) { // Алдааны массив хоосон биш бол
            for (boolean m : this.mistakes) {
                if (m) {
                    count++; // Алдаа гарсан бол тоог нэмж тоолно
                }
            }
        }
        return count;
    }

    // Бүх алдааны мэдээллийг буцаах функц
    public boolean[] getMistakes() {
        return this.mistakes;
    }

    // Тухайн давталтын индекст алдаа гаргасан эсэхийг тэмдэглэх функц
    public void markMistake(int repetitionIndex, boolean madeMistake) {
        if (this.mistakes != null && repetitionIndex >= 0 && repetitionIndex < mistakes.length) {
            this.mistakes[repetitionIndex] = madeMistake;
        }
    }

    // Сүүлд хийсэн давталтад алдаа гаргасан эсэхийг шалгах функц
    public boolean madeRecentMistake() {
        if (this.mistakes != null && this.mistakes.length > 0) {
            return this.mistakes[mistakes.length - 1]; // Сүүлчийн алдааны утгыг буцаана
        }
        return false; // Алдааны мэдээлэл байхгүй бол false буцаана
    }
}

package com.flashcard;

import java.util.Collections;
import java.util.List;

// RandomSortStrategy нь CardOrganizerStrategy интерфэйсийг хэрэгжүүлнэ
public class RandomSortStrategy implements CardOrganizerStrategy {

    // санамсаргүй байдлаар эрэмбэлнэ
    @Override
    public void sort(List<Flashcard> flashcards) {
        // Java-гийн Collections классын shuffle() аргаар жагсаалтыг random байдлаар холих үйлдэл
        Collections.shuffle(flashcards);

        // Флашкартууд random хольсон тухай мэдээлэл харуулна
        System.out.println("Flashcards shuffled randomly!");
    }
}

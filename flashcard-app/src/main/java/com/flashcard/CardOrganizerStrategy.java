package com.flashcard;

import java.util.List;

// Картуудыг ангилах стратегийг тодорхойлдог интерфейс
public interface CardOrganizerStrategy {

    // Картуудын жагсаалтыг эрэмбэлэх функц
    void sort(List<Flashcard> flashcards);

}

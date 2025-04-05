package com.flashcard;

import java.util.List;

public class RecentMistakesFirstSorter implements CardOrganizer {

    @Override
    public List<Flashcard> organize(List<Flashcard> cards) {

        cards.sort((a, b) -> Integer.compare(b.mistakes, a.mistakes));
        return cards;
    }

}


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.flashcard.Deck;
import com.flashcard.DeckEditor;
import com.flashcard.Flashcard;
import com.flashcard.StudyDeck;

public class StudyDeckTest {

    private List<Deck> decks;
    private StudyDeck studyDeck;
    private DeckEditor deckEditor;
    private Deck testDeck;

    @Before
    public void setUp() {
        decks = new ArrayList<>();

        //creating deck decks.length = 1
        testDeck = new Deck("Test deck");
        decks.add(testDeck);

        //creating flashcards for testDeck
        Flashcard flashcard = new Flashcard("What is the Capital of Mongolia", "Ulaanbaatar", "Its easy");
        testDeck.addFlashcard(flashcard);

        studyDeck = new StudyDeck(decks, null);

    }

    @Test
    public void hasDeck() {
        assertFalse(decks.isEmpty());
    }

    @Test
    public void hasFlashcards() {
        assertTrue(testDeck.hasFlashcards());
    }

}

package com.korobko.elements;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Vova Korobko
 */

public class SentenceTest {
    public static final String TEST_SENTENCE = "asdasdfghfgh";
    private Sentence sentence;
    @Before
    public void setUp() {
        sentence = new Sentence(TEST_SENTENCE);
    }

    @After
    public void tearDown() {
        sentence = null;
    }

    @Test
    public void cutSubstring_bothLettersMatch_successfulCut() {
        assertEquals("ah", sentence.cutSubstring('s', 'g').toString());
    }

    @Test
    public void cutSubstring_firstCharDoNotMatch_notCuttedOff() {
        assertEquals(TEST_SENTENCE, sentence.cutSubstring('e', 'g').toString());
    }

    @Test
    public void cutSubstring_secondCharDoNotMatch_notCuttedOff() {
        assertEquals(TEST_SENTENCE, sentence.cutSubstring('s', 'e').toString());
    }

}
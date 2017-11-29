package com.korobko.elements;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vova Korobko
 */
class SentenceTest {
    Sentence sentence;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void cutSubstring_fromWordWithLetters_success() {
        sentence = new Sentence("asdasdfghfgh");
        assertTrue(sentence.cutSubstring('s', 'g').toString().equals("ah"));
    }

}
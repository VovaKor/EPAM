package com.korobko.elements;

import static com.korobko.Constants.EMPTY_STRING;
import static com.korobko.Constants.REGEX_ANY_SYMBOLS;

/**
 * @author Vova Korobko
 */
public class Sentence {

    private String sentence;

    public Sentence(String sentence) {
        this.sentence = sentence;
    }

    /**
     * Cuts  substring with maximum length from char {@param from) to char {@param to}
     */
    public Sentence cutSubstring(char from, char to) {
        this.sentence = this.sentence.replaceAll(from + REGEX_ANY_SYMBOLS + to, EMPTY_STRING);
        return this;
    }

    @Override
    public String toString() {
        return this.sentence;
    }
}

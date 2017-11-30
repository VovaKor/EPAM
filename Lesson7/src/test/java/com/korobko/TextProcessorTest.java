package com.korobko;

import com.korobko.elements.Sentence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;

/**
 * Unit test for simple TextProcessor.
 */
public class TextProcessorTest {
    static final String FILE_NAME = "C:\\Users\\Vova\\IdeaProjects\\EPAM/Lesson7/Test_text.txt";
    static final String[] STRING_ARRAY = {" Amanda John:", "What do you mean, Condiment.Decorator?", "That’s right!", "Any way we like...", "Ooooh, I see."};

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getSentences_fromArrayWithOneNotSentence_returnsOnlySentences() {
        List<Sentence> list = TextProcessor.getSentences(STRING_ARRAY);
        assertEquals(4, list.size());
    }

    @Test(expected = NullPointerException.class)
    public void getSentences_givenNullArray_throwsException() {
        TextProcessor.getSentences(null);
    }

    @Test
    public void getSentences_givenSentenceWithTabsAndSpaces_returnsSentenceWithSpaces() {
        List<Sentence> list = TextProcessor.getSentences(new String[] {"What\t     do         you   \tmean,   Condiment.Decorator?"});
        assertEquals("What do you mean, Condiment.Decorator?", list.get(0).toString());
    }

    @Test
    public void getSentences_givenSentenceWithTrailingTabsAndSpaces_returnsTrimmedSentence() {
        List<Sentence> list = TextProcessor.getSentences(new String[] {"\t\t    What do you mean, Condiment.Decorator?    "});
        assertEquals("What do you mean, Condiment.Decorator?", list.get(0).toString());
    }

    @Test
    public void getExpressionsFromFile_sixExpressions_returnsStringArray() throws IOException {
        String[] actualResult= TextProcessor.getExpressionsFromFile(FILE_NAME);
        for (int i = 0; i < STRING_ARRAY.length; i++) {
            assertEquals(STRING_ARRAY[i], actualResult[i]);
        }
    }

    @Test(expected = IOException.class)
    public void getExpressionsFromFile_fileNotExist_throwsException() throws IOException {
        TextProcessor.getExpressionsFromFile("test.txt");
    }


}

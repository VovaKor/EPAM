package com.korobko;

import com.korobko.elements.Sentence;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.korobko.Constants.*;

/**
 * Создать программу обработки текста учебника по программированию с использованием
 * классов: Символ, Слово, Предложение, Знак препинания и др. Во всех задачах с
 * формированием текста заменять табуляции и последовательности пробелов одним пробелом.
 * <p>
 * 11.	В каждом предложении текста исключить подстроку максимальной длины, начинающуюся
 * и заканчивающуюся заданными символами.
 *
 * @author Vova Korobko
 */
public class TextProcessor {

    public static void main(String[] args) {

        List<Sentence> sentenceList = null;
        try {
            String[] expressions = getExpressionsFromFile(FILE_NAME);
            sentenceList = getSentences(expressions);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Excluding substring with maximum length and printing results
        sentenceList.stream().forEach(sentence -> {
            System.out.println(sentence.cutSubstring('a', 'b'));
        });
    }

    /**
     * Gets list of sentences from given array of expressions
     */
    public static List<Sentence> getSentences(String[] expressions) {
        List<Sentence> sentenceList = new ArrayList<>();
        for (String string : expressions) {
            String expression = string.replaceAll(REGEX_SPACES, WHITESPACE).trim();
            if (!expression.endsWith(COLON)) {
                sentenceList.add(new Sentence(expression));
            }
        }
        return sentenceList;
    }

    /**
     * Splits text from given file into array of expressions
     */
    public static String[] getExpressionsFromFile(String path) throws IOException {
        return Files.lines(Paths.get(path))
                .map(line -> WHITESPACE + line)
                .collect(Collectors.joining())
                .split(REGEX_SPLIT_TEXT);
    }
}

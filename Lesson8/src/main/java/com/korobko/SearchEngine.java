package com.korobko;

import java.io.IOException;
import java.util.*;

import static com.korobko.Constants.REGEXP_NOT_LETTERS;

/**
 * 1. Написать регулярное выражение, определяющее является ли данная строчка
 * валидным URL адресом. В данной задаче правильным URL считаются адреса http и
 * https, явное указание протокола также может отсутствовать. Учитываются только
 * адреса, состоящие из символов, т.е. IP адреса в качестве URL не присутствуют при
 * проверке. Допускаются поддомены, указание порта доступа через двоеточие, GET
 * запросы с передачей параметров, доступ к подпапкам на домене, допускается
 * наличие якоря через решетку. Однобуквенные домены считаются запрещенными.
 * Запрещены спецсимволы, например «–» в начале и конце имени домена. Запрещен
 * символ «_» и пробел в имени домена. При составлении регулярного выражения
 * ориентируйтесь на список правильных и неправильных выражений заданных ниже.
 * Пример правильных выражений: http://www.zcontest.ru, http://zcontest.ru.
 * <p>
 * 2. Написать поисковый сервис. По произвольному URL перейти и спомощью регулярного
 * выражения найти 20 URL, каждому указываете ключевые слова с частотами,
 * по указаному слову найти все URL упорядоченые по возрастанию частоты слова.
 *
 * @author Vova Korobko
 */
public class SearchEngine {
    public static final String TEST_URL = "https://habrahabr.ru/hub/java/";
    public static void main(String[] args) {

        String url = TEST_URL;
        String wordToSearch = "java";
        System.out.println("Getting information from site: " + url);


        try {
            // Getting content for initial URL
            String contentAsString = URLService.getUrlContentAsString(url);

            // Finding 20 URLs in content by regular expression
            Set<String> urlSet = URLService.extractUrlsFromContent(contentAsString);

            // Printing URLs
            Iterator<String> iterator = urlSet.iterator();
            for (int i = 0; i < urlSet.size(); i++) {

                System.out.println(i + ") " + iterator.next());
            }

            System.out.println("Searching for word \"" + wordToSearch + "\"");
            Map<String, Map<String, Integer>> map = getUrlToWordsMap(urlSet);
            findURLsByWordAppearance(wordToSearch, map).forEach((i, s) -> {
                System.out.println("Word \"" + wordToSearch + "\" was found " + i + " times for " + s);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *  Creates map "frequency of occurrence" -> "url" for target word
     *  @return map sorted in ascending order by frequency of occurrence
     */
    public static Map<Integer, String> findURLsByWordAppearance(String word, Map<String, Map<String, Integer>> map) {
        Map<Integer, String> result = new TreeMap<>();
        map.forEach((s, stringIntegerMap) -> {
            if (stringIntegerMap.containsKey(word)) {
                result.put(stringIntegerMap.get(word), s);
            }
        });
        return result;
    }

    /**
     *  Creates map url -> word -> frequency of occurrence
     */
    public static Map<String, Map<String, Integer>> getUrlToWordsMap(Set<String> urls) throws IOException {
        Map<String, Map<String, Integer>> urlToWordsMap = new HashMap<>();
        for (String urlString : urls) {
            String[] words = URLService.getUrlContentAsString(urlString).split(REGEXP_NOT_LETTERS);

            Map<String, Integer> map = new HashMap<>();
            for (String word : words) {
                Integer value = map.get(word);
                if (value == null) value = 0;
                map.put(word, ++value);
            }
            urlToWordsMap.put(urlString, map);
        }
        return urlToWordsMap;
    }
}

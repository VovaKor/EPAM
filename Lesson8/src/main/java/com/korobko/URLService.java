package com.korobko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.korobko.Constants.CAPACITY;
import static com.korobko.Constants.REGEXP_URL;
import static com.korobko.Constants.URL_AMOUNT;

/**
 * @author Vova Korobko
 */
public class URLService {

    /**
     *  Getting html content from given URL
     */
    public static String getUrlContentAsString(String urlAsString) throws IOException {

        StringBuilder stringBuilder = new StringBuilder(CAPACITY);
        URL url = null;
        try {
            url = new URL(urlAsString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (MalformedURLException e) {
            System.out.println("URL.class couldn't handle this type of url " + urlAsString);
        }

        return stringBuilder.toString();
    }

    /**
     *  Using regular expression finds 20 URL
     */
    public static Set<String> extractUrlsFromContent(String content){

        Set<String> set = new HashSet<>();

        Pattern pattern = Pattern.compile(REGEXP_URL, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find() && set.size() != URL_AMOUNT) {
            set.add(matcher.group());
        }

        return set;
    }

}

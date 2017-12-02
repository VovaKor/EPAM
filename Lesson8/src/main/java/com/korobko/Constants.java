package com.korobko;

/**
 * @author Vova Korobko
 */
public class Constants {
    /**
     *  Regular expression to validate URL
     */
    public static final String REGEXP_URL =
            "((ht|f)tp(s?):\\/\\/|www\\.)"
            + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
            + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};]*)";
    /**
     *  StringBuilder initial capacity
     */
    public static final int CAPACITY = 1000;

    public static final String REGEXP_NOT_LETTERS = "[^a-zA-Zа-яА-Я+]+";
    public static final int URL_AMOUNT = 20;

}

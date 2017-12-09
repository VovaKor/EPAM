package com.korobko.utils;

/**
 * @author Vova Korobko
 */
public enum PaperEnum {
    PAPER_STORE("paper_store"),
    ID("id"),
    TITLE("title"),
    IS_MONTHLY("isMonthly"),
    IS_COLORED("isColored"),
    PAGE_AMOUNT("pageAmount"),
    IS_GLOSSY("isGlossy"),
    SUBSCRIPTION_INDEX("subscriptionIndex"),
    PAPER("paper"),
    TYPE("type"),
    CHARACTERISTICS("characteristics");

    private String value;
    PaperEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

}

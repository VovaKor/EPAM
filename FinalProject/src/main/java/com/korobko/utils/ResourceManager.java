package com.korobko.utils;

import java.util.ResourceBundle;

/**
 * @author Vova Korobko
 */
public enum ResourceManager {
    MESSAGES (ResourceBundle.getBundle("properties.messages")),
    CONFIGURATION (ResourceBundle.getBundle("properties.config")),
    QUERIES(ResourceBundle.getBundle("properties.db_queries"));

    private ResourceBundle resourceBundle;

    ResourceManager(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    /**
     * Gets a string for the given key from this resource bundle
     * @param key the key for the desired string
     * @return the string for the given key
     */
    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}

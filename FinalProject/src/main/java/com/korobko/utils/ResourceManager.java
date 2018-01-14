package com.korobko.utils;

import java.util.ResourceBundle;

/**
 * @author Vova Korobko
 */
public enum ResourceManager {
    MESSAGES {
        {
            this.resourceBundle = ResourceBundle.getBundle("resources.properties.messages");
        }
    },
    CONFIGURATION {
        {
            this.resourceBundle = ResourceBundle.getBundle("resources.properties.config");
        }
    },
    QUERIES{
        {
            this.resourceBundle = ResourceBundle.getBundle("resources.properties.db_queries");
        }
    };

    ResourceBundle resourceBundle;

    /**
     * Gets a string for the given key from this resource bundle
     * @param key the key for the desired string
     * @return the string for the given key
     */
    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}

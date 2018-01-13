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
     * todo
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}

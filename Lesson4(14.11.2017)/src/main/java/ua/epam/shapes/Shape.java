package ua.epam.shapes;

import java.util.HashMap;
import java.util.Map;

public abstract class Shape {
    private Map<String, Object> properties;

    public Shape() {
        this.properties = new HashMap<>();
    }

    public boolean containsProperty(String propertyName) {
        return properties.containsKey(propertyName);
    }

    public void setProperty(String propertyName, Object value) {
        properties.put(propertyName, value);
    }
}

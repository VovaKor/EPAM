package com.korobko;

import com.korobko.builders.XmlBuilder;

import java.util.Set;

/**
 * @author Vova Korobko
 */
public class XmlParser<T> {
    private String path;

    public XmlParser(String pathToXml) {
        this.path = pathToXml;
    }

    public Set<T> parseXml(XmlBuilder<T> builder) {
        return builder.buildObjectsFromXml(path);
    }
}

package com.korobko.builders;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Vova Korobko
 */
public abstract class XmlBuilder<T> {
    static {
        new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
    }
    static Logger logger = Logger.getLogger(XmlBuilder.class);

    Set<T> papers;

    public XmlBuilder() {
        this.papers = new HashSet<>();
    }

    public abstract Set<T> buildObjectsFromXml(String path);
}

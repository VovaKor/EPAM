package com.korobko.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author Vova Korobko
 */
public class SessionAttributeListener implements HttpSessionAttributeListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        logger.info("Attribute added: {}:{}:{}", event.getClass().getSimpleName(), event.getName(), event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        logger.info("Attribute removed: {}:{}:{}", event.getClass().getSimpleName(), event.getName(), event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        logger.info("Attribute replaced: {}:{}:{}", event.getClass().getSimpleName(), event.getName(), event.getValue());
    }
}

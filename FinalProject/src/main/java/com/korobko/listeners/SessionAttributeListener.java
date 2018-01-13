package com.korobko.listeners;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author Vova Korobko
 */
public class SessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        //todo
        // запись в log-файл или иные действия
        //System.out.println("add: " + ev.getClass().getSimpleName() + " : "+ ev.getName()
         //       + " : " + ev.getValue());

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
// запись в log-файл или иные действия
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
// запись в log-файл или иные действия
    }
}

package com.korobko;

import org.xml.sax.SAXException;

/**
 * @author Vova Korobko
 */
public class InvalidXMLException extends SAXException {

    public InvalidXMLException() {
    }

    public InvalidXMLException(String message, Exception cause) {
        super(message, cause);
    }

    public InvalidXMLException(String message) {
        super(message);
    }

    public InvalidXMLException(Exception cause) {
        super(cause);
    }
}



package com.korobko.utils;

import com.korobko.InvalidXMLException;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

import static com.korobko.utils.Constants.PATH_TO_LOG;

/**
 * @author Vova Korobko
 */
public class Validator {
    // creating logger for package com.korobko
    private static Logger logger = Logger.getLogger("com.korobko");

    public static boolean validateXMLwithXSD(String xmlFileName, String schemaFileName) {
        boolean result = false;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaFileName);
        try {
            // setting file and output format
            logger.addAppender(new FileAppender(new SimpleLayout(), PATH_TO_LOG));

            Schema schema = factory.newSchema(schemaLocation);
            javax.xml.validation.Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFileName);
            validator.setErrorHandler(new ErrorHandler());
            validator.validate(source);
            result = true;
        } catch (SAXException | IOException e) {
            if (!e.getClass().getSimpleName().equals("InvalidXMLException"))
                e.printStackTrace();
        }
        return result;
    }
    private static class ErrorHandler extends DefaultHandler {

        public void warning(SAXParseException e) {
            logger.warn(getLineAddress(e) + "-" + e.getMessage());
        }
        public void error(SAXParseException e) throws InvalidXMLException {
            logger.error(getLineAddress(e) + " - " + e.getMessage());
            throw new InvalidXMLException(e.getMessage(), e);
        }
        public void fatalError(SAXParseException e) {
            logger.fatal(getLineAddress(e) + " - " + e.getMessage());
        }
        private String getLineAddress(SAXParseException e) {

            // getting error row and column
            return e.getLineNumber() + " : " + e.getColumnNumber();
        }
    }

}


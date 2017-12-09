package com.korobko.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXParseException;

import static org.junit.Assert.*;

/**
 * @author Vova Korobko
 */
public class ValidatorTest {
    public static final String INVALID_XML = "./src/test/java/com/korobko/utils/invalid_paper_store.xml";
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void validateXMLwithXSD_validXmlAndValidXSD_returnsTrue() {
        boolean result = Validator.validateXMLwithXSD(Constants.PATH_TO_XML, Constants.PATH_TO_SCHEMA);
        assertTrue(result);
    }
    @Test
    public void validateXMLwithXSD_invalidXmlAndValidXSD_returnsFalse() {
        boolean result = Validator.validateXMLwithXSD(INVALID_XML, Constants.PATH_TO_SCHEMA);
        assertFalse(result);
    }
}
package com.korobko;

import com.korobko.builders.PaperDOMBuilder;
import com.korobko.builders.PaperSAXBuilder;
import com.korobko.builders.PaperStAXBuilder;
import com.korobko.models.Characteristics;
import com.korobko.models.Paper;
import com.korobko.models.PaperStore;
import com.korobko.models.PeriodicalType;
import com.korobko.utils.Constants;
import com.korobko.utils.ValidatorTest;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Vova Korobko
 */
public class XmlParserTest {
    private static PaperStore store;
    @BeforeClass
    public static void init() {
        Characteristics characteristics = new Characteristics(true, 500, true);
        characteristics.setSubscriptionIndex(456328);
        Paper paper = new Paper(1345454L, "New Magazine", PeriodicalType.MAGAZINE, true, characteristics);
        Characteristics characteristics2 = new Characteristics(true, 40, false);
        characteristics2.setSubscriptionIndex(324328);
        Paper paper2 = new Paper(2344543L, "New Paper", PeriodicalType.NEWSPAPER, true, characteristics2);
        Characteristics characteristics3 = new Characteristics(true, 500, true);
        Paper paper3 = new Paper(3345456L, "New Booklet", PeriodicalType.BOOKLET, true, characteristics3);
        store = new PaperStore();
        store.getPapers().add(paper);
        store.getPapers().add(paper2);
        store.getPapers().add(paper3);

    }
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void parseXml_paperDomBuilderValidXml_setsEqual() {
        XmlParser<Paper> parser = new XmlParser<>(Constants.PATH_TO_XML);
        Set<Paper> resultSet = parser.parseXml(new PaperDOMBuilder());
        assertEquals(store.getPapers(), resultSet);
    }
    @Test
    public void parseXml_paperDomBuilderInvalidXml_setsNotEqual() {
        XmlParser<Paper> parser = new XmlParser<>(ValidatorTest.INVALID_XML);
        Set<Paper> resultSet = parser.parseXml(new PaperDOMBuilder());
        assertNotEquals(store.getPapers(), resultSet);
    }
    @Test
    public void parseXml_paperSAXBuilderValidXml_setsEqual() {
        XmlParser<Paper> parser = new XmlParser<>(Constants.PATH_TO_XML);
        Set<Paper> resultSet = parser.parseXml(new PaperSAXBuilder());
        assertEquals(store.getPapers(), resultSet);
    }
    @Test(expected = IllegalArgumentException.class)
    public void parseXml_paperSAXBuilderInvalidXmlTag_throwsException() {
        XmlParser<Paper> parser = new XmlParser<>(ValidatorTest.INVALID_XML);
        parser.parseXml(new PaperSAXBuilder());

    }
    @Test
    public void parseXml_paperStAXBuilderValidXml_setsEqual() {
        XmlParser<Paper> parser = new XmlParser<>(Constants.PATH_TO_XML);
        Set<Paper> resultSet = parser.parseXml(new PaperStAXBuilder());
        assertEquals(store.getPapers(), resultSet);
    }
    @Test(expected = IllegalArgumentException.class)
    public void parseXml_paperStAXBuilderInvalidXmlTag_throwsException() {
        XmlParser<Paper> parser = new XmlParser<>(ValidatorTest.INVALID_XML);
        parser.parseXml(new PaperStAXBuilder());

    }
}
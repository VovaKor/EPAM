package com.korobko.builders;

import com.korobko.models.Characteristics;
import com.korobko.models.Paper;
import com.korobko.models.PeriodicalType;
import com.korobko.utils.PaperEnum;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vova Korobko
 */
public class PaperSAXBuilder extends XmlBuilder<Paper> {
    public static final int CHARACTERISTICS_ATTRIBUTES_MAX_LENGTH = 4;
    private PaperHandler sh;
    private XMLReader reader;

    @Override
    public Set<Paper> buildObjectsFromXml(String path) {
        sh = new PaperHandler();
        try {

            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(sh);
            reader.parse(path);

        } catch (SAXException e) {
            logger.error("Parsing failure: ", e);
        } catch (IOException e) {
            logger.error("File error or I/O error: ", e);
        }
        logger.info("SAX builder fetched all papers");
        papers = sh.getPapers();
        return papers;
    }

    private class PaperHandler extends DefaultHandler {

        private Set<Paper> papers;
        private Paper currentPaper;
        private PaperEnum currentEnum = null;
        public PaperHandler() {
            papers = new HashSet<>();

        }
        public Set<Paper> getPapers() {
            return papers;
        }
        public void startElement(String uri, String localName, String qName, Attributes attrs) {
            if (PaperEnum.PAPER.getValue().equals(localName)) {
                currentPaper = new Paper();
                currentPaper.setId(Long.parseLong(attrs.getValue(0)));
                currentPaper.setTitle(attrs.getValue(1));
                currentPaper.setMonthly(Boolean.parseBoolean(attrs.getValue(2)));
            } else if (PaperEnum.CHARACTERISTICS.getValue().equals(localName)) {
                Characteristics characteristics = new Characteristics();
                characteristics.setColored(Boolean.parseBoolean(attrs.getValue(0)));
                characteristics.setPageAmount(Integer.parseInt(attrs.getValue(1)));
                characteristics.setGlossy(Boolean.parseBoolean(attrs.getValue(2)));
                if (attrs.getLength() == CHARACTERISTICS_ATTRIBUTES_MAX_LENGTH) {
                    characteristics.setSubscriptionIndex(Integer.parseInt(attrs.getValue(3)));
                }
                currentPaper.setCharacteristics(characteristics);
            } else {
                currentEnum = PaperEnum.valueOf(localName.toUpperCase());
            }
        }
        public void endElement(String uri, String localName, String qName) {
            if (PaperEnum.PAPER.getValue().equals(localName)) {
                papers.add(currentPaper);
            }
        }
        public void characters(char[] ch, int start, int length) {

            String s = new String(ch, start, length).trim();
            if (currentEnum != null) {

                switch (currentEnum) {
                    case TYPE:
                        currentPaper.setType(PeriodicalType.valueOf(s));
                        break;
                    case PAPER_STORE:
                        break;
                    default:
                        throw new EnumConstantNotPresentException(
                                currentEnum.getDeclaringClass(), currentEnum.name());
                }
            }
            currentEnum = null;
        }
    }
}

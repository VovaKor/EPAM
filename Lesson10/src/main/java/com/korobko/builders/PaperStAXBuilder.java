package com.korobko.builders;

import com.korobko.models.Characteristics;
import com.korobko.models.Paper;
import com.korobko.models.PeriodicalType;
import com.korobko.utils.PaperEnum;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;


/**
 * @author Vova Korobko
 */
public class PaperStAXBuilder extends XmlBuilder<Paper> {
    private XMLInputFactory inputFactory;

    @Override
    public Set<Paper> buildObjectsFromXml(String path) {
        inputFactory = XMLInputFactory.newInstance();
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(path));
            reader = inputFactory.createXMLStreamReader(inputStream);
            // StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (PaperEnum.valueOf(name.toUpperCase()) == PaperEnum.PAPER) {
                        Paper paper = buildPaper(reader);
                        papers.add(paper);
                    }
                }
            }
            logger.info("StAX builder fetched all papers");
            return papers;
        } catch (XMLStreamException ex) {
            logger.error("StAX parsing error! ", ex);
        } catch (FileNotFoundException ex) {
            logger.error("File " + path + " not found! ", ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error("Impossible close file " + path + " : ", e);
            }
        }

        return null;
    }

    private Paper buildPaper(XMLStreamReader reader) throws XMLStreamException {
        Paper paper = new Paper();
        paper.setId(Long.parseLong(reader.getAttributeValue(null, PaperEnum.ID.getValue())));
        paper.setTitle(reader.getAttributeValue(null, PaperEnum.TITLE.getValue()));
        paper.setMonthly(Boolean.parseBoolean(reader.getAttributeValue(null, PaperEnum.IS_MONTHLY.getValue())));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PaperEnum.valueOf(name.toUpperCase())) {
                        case TYPE:
                            paper.setType(PeriodicalType.valueOf(getXMLText(reader)));
                            break;
                        case CHARACTERISTICS:
                            paper.setCharacteristics(getXMLCharacteristics(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PaperEnum.valueOf(name.toUpperCase()) == PaperEnum.PAPER) {
                        return paper;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag <paper>");
    }

    private Characteristics getXMLCharacteristics(XMLStreamReader reader) throws XMLStreamException {
        Characteristics characteristics = new Characteristics();
        characteristics.setColored(Boolean.parseBoolean(reader.getAttributeValue(null, PaperEnum.IS_COLORED.getValue())));
        characteristics.setPageAmount(Integer.parseInt(reader.getAttributeValue(null, PaperEnum.PAGE_AMOUNT.getValue())));
        characteristics.setGlossy(Boolean.parseBoolean(reader.getAttributeValue(null, PaperEnum.IS_GLOSSY.getValue())));
        String attr = reader.getAttributeValue(null, PaperEnum.SUBSCRIPTION_INDEX.getValue());
        if (attr != null) {
            characteristics.setSubscriptionIndex(Integer.parseInt(attr));
        }
        return characteristics;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}

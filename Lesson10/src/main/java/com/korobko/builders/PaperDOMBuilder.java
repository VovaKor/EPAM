package com.korobko.builders;

import com.korobko.models.Characteristics;
import com.korobko.models.Paper;
import com.korobko.models.PeriodicalType;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Set;

/**
 * @author Vova Korobko
 */
public class PaperDOMBuilder extends XmlBuilder<Paper> {

    private DocumentBuilder documentBuilder;

    @Override
    public Set<Paper> buildObjectsFromXml(String path) {

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            buildSetPapers(path);
        } catch (ParserConfigurationException e) {
        }
        return papers;
    }

    private void buildSetPapers(String path) {
        Document doc = null;
        try {

            doc = documentBuilder.parse(path);
            Element root = doc.getDocumentElement();

            NodeList papersList = root.getElementsByTagName("paper");
            for (int i = 0; i < papersList.getLength(); i++) {
                Element paperElement = (Element) papersList.item(i);
                Paper paper = buildPaper(paperElement);
                papers.add(paper);
            }
            logger.info("DOM builder fetched all papers");
        } catch (IOException e) {

            logger.error("File error or I/O error: ", e);

        } catch (SAXException e) {
            logger.error("Parsing failure: ", e);

        }

    }

    private Paper buildPaper(Element paperElement) {
        Paper paper = new Paper();
        paper.setId(Long.parseLong(paperElement.getAttribute("id")));
        paper.setTitle(paperElement.getAttribute("title"));
        paper.setMonthly(Boolean.parseBoolean(paperElement.getAttribute("isMonthly")));
        paper.setType(PeriodicalType.valueOf(getElementTextContent(paperElement, "type")));
        Characteristics characteristics = getCharacteristics(paperElement, "characteristics");
        paper.setCharacteristics(characteristics);

        return paper;

    }

    private Characteristics getCharacteristics(Element paperElement, String elementName) {
        Characteristics characteristics = new Characteristics();
        Element charElement = (Element) paperElement.getElementsByTagName(elementName).item(0);
        characteristics.setColored(Boolean.parseBoolean(charElement.getAttribute("isColored")));
        characteristics.setPageAmount(Integer.parseInt(charElement.getAttribute("pageAmount")));
        characteristics.setGlossy(Boolean.parseBoolean(charElement.getAttribute("isGlossy")));
        if (!charElement.getAttribute("subscriptionIndex").isEmpty()) {
            characteristics.setSubscriptionIndex(Integer.parseInt(charElement.getAttribute("subscriptionIndex")));
        }
        return characteristics;
    }

    private String getElementTextContent(Element paperElement, String elementName) {
        NodeList nList = paperElement.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;

    }
}

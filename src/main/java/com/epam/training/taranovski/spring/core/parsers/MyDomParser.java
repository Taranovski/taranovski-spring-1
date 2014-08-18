/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core.parsers;

import java.util.List;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.DOMException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Admin
 */
public class MyDomParser {

    List<Gem> list = new ArrayList();

    /**
     * get list of gems
     *
     * @return list of gems
     */
    public List<Gem> getList() {
        return list;
    }

    /**
     * parse function
     *
     * @param fileName file to parse
     */
    public void parse(String fileName) {
        try {
            //create new document builder factory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //creating new document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            //creating new document
            Document document = db.parse(fileName);
            //defining root element
            Element root = document.getDocumentElement();
            //next function for parsing objects from list
            parseGems(root.getChildNodes());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * parse a list of gems
     *
     * @param gems nodelist of gems
     */
    private void parseGems(NodeList gems) {

        for (int i = 0; i < gems.getLength(); i++) {
            Node gemItem = gems.item(i);
            if (gemItem.getNodeName().equals(GEM.toString())) {
                parseGem(gemItem);
            }
        }
    }

    /**
     * parse one gem
     *
     * @param gemItem gem to parse
     * @throws NumberFormatException
     * @throws DOMException
     */
    public void parseGem(Node gemItem) throws NumberFormatException, DOMException {
        Node gemElement = gemItem;
        Gem gemEntity = new Gem();
        VisualParameters vis;

        parseAttributes(gemEntity, gemElement);

        NodeList nodeList = gemElement.getChildNodes();
        parseTags(nodeList, gemEntity);

        list.add(gemEntity);
    }

    /**
     * parse attributes
     *
     * @param gemEntity gem to fill
     * @param gemElement gem node to parse
     * @throws DOMException
     */
    public void parseAttributes(Gem gemEntity, Node gemElement) throws DOMException {
        gemEntity.setName(gemElement.getAttributes().getNamedItem(NAME.toString()).getNodeValue());
        gemEntity.setPreciousness(Boolean.parseBoolean(gemElement.getAttributes().getNamedItem(PREC.toString()).getNodeValue()));
    }

    /**
     * parse tags from gem
     *
     * @param nodeList list of tags
     * @param gemEntity gem to fill
     * @throws NumberFormatException
     * @throws DOMException
     */
    public void parseTags(NodeList nodeList, Gem gemEntity) throws NumberFormatException, DOMException {
        VisualParameters vis;
        for (int j = 0; j < nodeList.getLength(); j++) {
            Node inGemItem = nodeList.item(j);

            if (inGemItem.getNodeName().equals(ORIGIN.toString())) {
                gemEntity.setOrigin(inGemItem.getTextContent());
            }
            if (inGemItem.getNodeName().equals(VALUE.toString())) {
                gemEntity.setValue(Double.parseDouble(inGemItem.getTextContent()));
            }

            if (inGemItem.getNodeName().equals(VIS.toString())) {
                NodeList visualParameters = inGemItem.getChildNodes();
                vis = new VisualParameters();

                parseVisualParameters(visualParameters, vis);

                gemEntity.setVisualParameters(vis);
            }
        }
    }

    /**
     * parse visual parameters
     *
     * @param visualParameters - nodelist of visual parameters
     * @param vis object of visual parameters
     * @throws DOMException
     * @throws NumberFormatException
     */
    public void parseVisualParameters(NodeList visualParameters, VisualParameters vis) throws DOMException, NumberFormatException {
        for (int k = 0; k < visualParameters.getLength(); k++) {
            Node inVisualParameters = visualParameters.item(k);
            if (inVisualParameters.getNodeName().equals(COLOR.toString())) {
                vis.setColor(inVisualParameters.getTextContent());
            }
            if (inVisualParameters.getNodeName().equals(TRANSP.toString())) {
                vis.setTransparency(Double.parseDouble(inVisualParameters.getTextContent()));
            }
            if (inVisualParameters.getNodeName().equals(SIDES.toString())) {
                vis.setSides(Integer.parseInt(inVisualParameters.getTextContent()));
            }
        }
    }
}

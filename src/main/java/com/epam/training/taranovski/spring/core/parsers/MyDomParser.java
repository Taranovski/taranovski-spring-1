/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core.parsers;

import com.epam.training.taranovski.spring.core.Bean;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Admin
 */
public class MyDomParser implements MyParser {

    List<Bean> list = new LinkedList();

    /**
     * get list of gems
     *
     * @return list of gems
     */
    public List<Bean> getList() {
        return list;
    }

    /**
     * parse function
     *
     * @param fileName file to parse
     */
    @Override
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
            parseBeans(root.getChildNodes());

        } catch (IOException | ParserConfigurationException | SAXException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * parse a list of beans
     *
     * @param beans node list of beans
     */
    private void parseBeans(NodeList beans) {

        for (int i = 0; i < beans.getLength(); i++) {
            Node gemItem = beans.item(i);
            if ("bean".equals(gemItem.getNodeName())) {
                parseBean(gemItem);
            }
        }
    }

    /**
     * parse one bean
     *
     * @param gemItem bean to parse
     * @throws NumberFormatException
     * @throws DOMException
     */
    public void parseBean(Node gemItem) throws NumberFormatException, DOMException {
        Node beanElement = gemItem;
        Bean beanEntity = new Bean();

        beanEntity.setBeadId(beanElement.getAttributes().getNamedItem("id").getNodeValue());
        beanEntity.setClassName(beanElement.getAttributes().getNamedItem("class").getNodeValue());

        NodeList diff = beanElement.getChildNodes();

        for (int i = 0; i < diff.getLength(); i++) {
            if ("constructor-arg".equals(diff.item(i).getNodeName())) {
                beanEntity.addConstructorArgs(parseParameter(diff.item(i)));
            } else if ("property".equals(diff.item(i).getNodeName())) {
                beanEntity.addParameter(parseParameterName(diff.item(i)), parseParameter(diff.item(i)));
            } else {
                throw new RuntimeException("unknown entity");
            }
        }

        list.add(beanEntity);
    }

    /**
     *
     * @param node
     * @return
     */
    private Object parseParameter(Node node) {
        return null;
    }

    /**
     *
     * @param node
     * @return
     */
    private String parseParameterName(Node node) {
        return node.getAttributes().getNamedItem("name").getNodeValue();
    }

}

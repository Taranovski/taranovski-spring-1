/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core.parsers;

import com.epam.training.taranovski.spring.core.Bean;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Admin
 */
public class MyDomParser implements MyParser {

    private boolean validating;
    private String fileName;

    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Document document;

    /**
     *
     * @param fileName
     */
    public MyDomParser(String fileName) {
        this.fileName = fileName;
        //create new document builder factory
        dbf = DocumentBuilderFactory.newInstance();
        
        try {
            //creating new document builder
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MyDomParser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * parse function
     *
     * @return
     */
    @Override
    public List<Bean> parse() {
        try {
            //creating new document
            document = db.parse(fileName);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(MyDomParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return parseBeans(document.getDocumentElement().getChildNodes());

    }

    /**
     * parse a list of beans
     *
     * @param beans node list of beans
     */
    private List<Bean> parseBeans(NodeList beans) {
        List<Bean> list1 = new LinkedList<>();
        for (int i = 0; i < beans.getLength(); i++) {
            Node beanItem = beans.item(i);
            if ("bean".equals(beanItem.getNodeName())) {
                list1.add(parseBean(beanItem));
            }
        }
        return list1;
    }

    /**
     * parse one bean
     *
     * @param beanItem
     * @return
     * @throws NumberFormatException
     * @throws DOMException
     */
    private Bean parseBean(Node beanItem) throws NumberFormatException, DOMException {
        Node beanElement = beanItem;
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

        return beanEntity;
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

    /**
     * @return the validating
     */
    public boolean isValidating() {
        return validating;
    }

    /**
     * @param validating the validating to set
     */
    public void setValidating(boolean validating) {
        this.validating = validating;
    }

}

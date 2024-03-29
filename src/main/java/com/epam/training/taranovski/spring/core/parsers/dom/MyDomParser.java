/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core.parsers.dom;

import com.epam.training.taranovski.spring.core.Bean;
import com.epam.training.taranovski.spring.core.BeanFactoryImpl;
import com.epam.training.taranovski.spring.core.parsers.MyBeansParser;
import java.io.IOException;
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
public class MyDomParser implements MyBeansParser {

    private static MyDomParser instance;

    private static String fileName;

    private static DocumentBuilderFactory dbf;
    private static DocumentBuilder db;
    private static Document document;

    public static MyDomParser getInstance(String fileName) {
        if (instance == null) {
            instance = new MyDomParser(fileName);

        }
        return instance;
    }

    /**
     * parse function
     *
     */
    @Override
    public void parse() {
        parseBeans(document.getDocumentElement().getChildNodes());
    }

    /**
     *
     * @param fileName
     * @param aThis
     */
    private MyDomParser(String fileName) {
        MyDomParser.fileName = fileName;
        //create new document builder factory
        dbf = DocumentBuilderFactory.newInstance();
        try {
            //creating new document builder
            db = dbf.newDocumentBuilder();
            document = db.parse(MyDomParser.fileName);
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(MyDomParser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * parse a list of beans
     *
     * @param beans node list of beans
     */
    private void parseBeans(NodeList beans) {
        for (int i = 0; i < beans.getLength(); i++) {
            Node beanItem = beans.item(i);
            if ("bean".equals(beanItem.getNodeName())) {
                BeanFactoryImpl.getInstance().addBean(parseBean(beanItem));
            }
        }
    }

    /**
     * parse one bean
     *
     * @param beanItem
     * @return
     * @throws NumberFormatException
     * @throws DOMException
     */
    private Bean parseBean(Node beanItem) {
        Node beanElement = beanItem;
        Bean beanEntity = new Bean();

        beanEntity.setBeadId(beanElement.getAttributes().getNamedItem("id").getNodeValue());
        beanEntity.setClassName(beanElement.getAttributes().getNamedItem("class").getNodeValue());

        NodeList diff = beanElement.getChildNodes();

        for (int i = 0; i < diff.getLength(); i++) {
            if ("constructor-arg".equals(diff.item(i).getNodeName())) {
                beanEntity.addConstructorArgs(parseParameter(diff.item(i)));
            }
            if ("property".equals(diff.item(i).getNodeName())) {
                beanEntity.addParameter(parseParameterName(diff.item(i)), parseParameter(diff.item(i)));
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

        Node valueNode = node.getAttributes().getNamedItem("value");
        Node refNode = node.getAttributes().getNamedItem("ref");

        if (valueNode != null & refNode != null) {
            throw new RuntimeException("error in xml bean definition");
        }

        if (valueNode != null) {
            String value = valueNode.getNodeValue();
            return value;
        }

        if (refNode != null) {
            String beanName = refNode.getNodeValue();
            return BeanFactoryImpl.getInstance().getBean(beanName);
        }
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

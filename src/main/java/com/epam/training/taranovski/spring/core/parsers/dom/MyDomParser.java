/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core.parsers.dom;

import com.epam.training.taranovski.spring.core.Bean;
import com.epam.training.taranovski.spring.core.XmlBeanDefinitionReader;
import com.epam.training.taranovski.spring.core.parsers.MyBeansParser;
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
public class MyDomParser implements MyBeansParser {

    private String fileName;
    XmlBeanDefinitionReader backReference;

    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Document document;

    /**
     *
     * @param fileName
     * @param aThis
     */
    public MyDomParser(String fileName, XmlBeanDefinitionReader aThis) {
        this.fileName = fileName;
        backReference = aThis;
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
        String type = node.getAttributes().getNamedItem("itemType").getNodeValue();
        switch (type) {
            case "byte": {
                return Byte.parseByte(node.getAttributes().getNamedItem("value").getNodeValue());
            }
            case "short": {
                return Short.parseShort(node.getAttributes().getNamedItem("value").getNodeValue());
            }
            case "integer": {
                return Integer.parseInt(node.getAttributes().getNamedItem("value").getNodeValue());
            }
            case "long": {
                return Long.parseLong(node.getAttributes().getNamedItem("value").getNodeValue());
            }
            case "float": {
                return Float.parseFloat(node.getAttributes().getNamedItem("value").getNodeValue());
            }
            case "double": {
                return Double.parseDouble(node.getAttributes().getNamedItem("value").getNodeValue());
            }
            case "char": {
                String value = node.getAttributes().getNamedItem("value").getNodeValue();
                return value.charAt(0);
            }
            case "string": {
                return node.getAttributes().getNamedItem("value").getNodeValue();
            }
            case "custom": {
                String beanName = node.getAttributes().getNamedItem("reference").getNodeValue();
                return backReference.getBeanFactory().getBean(beanName);
            }
            default: {
                return null;
            }
        }
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

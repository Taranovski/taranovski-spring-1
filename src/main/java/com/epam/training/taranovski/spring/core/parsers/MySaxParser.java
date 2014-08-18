/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core.parsers;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Alex
 */
public class MySaxParser {
/**
 * parse function
 * @param fileName file to parse
 * @throws ParserConfigurationException
 * @throws SAXException
 * @throws IOException 
 */
    public void parse(String fileName) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        MySaxHandler saxHandler = new MySaxHandler();

        parser.parse(new File(fileName), saxHandler);
        
        for (Gem gem : saxHandler.getGems()) {
            System.out.println(gem);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core.parsers;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author Alex
 */
public class MyStaxParser {

    List<Gem> list = new ArrayList();
    Gem currentGem;
    VisualParameters vis;
    String tempString;
    /**
     * parse function
     * @param fileName file to parse
     * @throws XMLStreamException
     * @throws FileNotFoundException 
     */
    public void parse(String fileName) throws XMLStreamException, FileNotFoundException {
        System.out.println("start stax parse xml...");
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(new File(fileName)));

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT: {
                    if (reader.getLocalName().equals(GEM.toString())) {
                        currentGem = new Gem();
                        currentGem.setName(reader.getAttributeValue(1));
                        currentGem.setPreciousness(Boolean.parseBoolean(reader.getAttributeValue(0)));
                    }
                    if (reader.getLocalName().equals(VIS.toString())) {
                        vis = new Gem.VisualParameters();
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    if (reader.getLocalName().equals(VALUE.toString())) {
                        currentGem.setValue(Double.parseDouble(tempString));
                    }
                    if (reader.getLocalName().equals(TRANSP.toString())) {
                        vis.setTransparency(Double.parseDouble(tempString));
                    }
                    if (reader.getLocalName().equals(SIDES.toString())) {
                        vis.setSides(Integer.parseInt(tempString));
                    }
                    if (reader.getLocalName().equals(ORIGIN.toString())) {
                        currentGem.setOrigin(tempString);
                    }
                    if (reader.getLocalName().equals(COLOR.toString())) {
                        vis.setColor(tempString);
                    }
                    if (reader.getLocalName().equals(VIS.toString())) {
                        currentGem.setVisualParameters(vis);
                    }
                    if (reader.getLocalName().equals(GEM.toString())) {
                        list.add(currentGem);
                    }
                    break;
                }
                case XMLStreamConstants.CHARACTERS: {
                    tempString = reader.getText().trim();
                    break;
                }
            }
        }
        System.out.println("stop stax parse xml...");
        for (Gem gem : list) {
            System.out.println(gem);
        }
    }
}

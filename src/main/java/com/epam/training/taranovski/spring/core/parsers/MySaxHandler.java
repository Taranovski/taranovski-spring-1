/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core.parsers;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Alex
 */
public class MySaxHandler extends DefaultHandler {

    private List<Gems.Gem> gems;
    Gem currentGem = new Gem();
    VisualParameters vis = new VisualParameters();
    String temp;

    /**
     * @return the gems
     */
    public List<Gems.Gem> getGems() {
        return gems;
    }

    /**
     * override start document
     *
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start SAX parse XML...");
        gems = new ArrayList();
    }

    /**
     * override end document
     */
    @Override
    public void endDocument() {
        System.out.println("Stop SAX parse XML...");
    }

    /**
     * override start element
     *
     * @param uri
     * @param localName
     * @param qName
     * @param atts
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (qName.equals(GEM.toString())) {
            currentGem = new Gem();
            currentGem.setName(atts.getValue(NAME.toString()));
            currentGem.setPreciousness(Boolean.parseBoolean(atts.getValue(PREC.toString())));
            
        }
        if (qName.equals(VIS.toString())) {
            vis = new VisualParameters();
        }

    }

    /**
     * override end element
     *
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(VALUE.toString())) {
            currentGem.setValue(Double.parseDouble(temp));
        }
        if (qName.equals(TRANSP.toString())) {
            vis.setTransparency(Double.parseDouble(temp));
        }
        if (qName.equals(SIDES.toString())) {
            vis.setSides(Integer.parseInt(temp));
        }
        if (qName.equals(ORIGIN.toString())) {
            currentGem.setOrigin(temp);
        }

        if (qName.equals(COLOR.toString())) {
            vis.setColor(temp);
        }

        if (qName.equals(VIS.toString())) {
            currentGem.setVisualParameters(vis);
        }
        if (qName.equals(GEM.toString())) {
            gems.add(currentGem);

        }

    }

    /**
     * override charachters
     *
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        temp = new String(ch, start, length);
    }

}

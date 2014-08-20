/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core;

import com.epam.training.taranovski.spring.core.parsers.MyParser;
import com.epam.training.taranovski.spring.core.parsers.ParserTypes;

/**
 *
 * @author Alyx
 */
public class GenericXmlApplicationContext {

    private XmlBeanDefinitionReader reader;
    private MyParser parser;
    private String mySpringXMLConfigFile;
    private ParserTypes parserType;

    /**
     *
     * @param validating
     */
    public void setValidating(boolean validating) {

    }

    /**
     *
     * @param parserType
     */
    public void setParserType(ParserTypes parserType) {
        this.parserType = parserType;
    }

    /**
     *
     * @param XMLFileLocation
     */
    public void load(String XMLFileLocation) {
        mySpringXMLConfigFile = XMLFileLocation;
    }

    /**
     *
     * @return
     */
    public BeanFactory getBeanFactory() {

        reader = new XmlBeanDefinitionReader();
        return reader.getBeanFactory();
    }
//- создает и возвращает экземпляр BeanFactory, в котором хранится ссылка на объектное представление конфигурационного xml-файла

}

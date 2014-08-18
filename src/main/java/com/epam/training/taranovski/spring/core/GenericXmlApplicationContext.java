/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core;

import com.epam.training.taranovski.spring.core.parsers.MyDomParser;
import com.epam.training.taranovski.spring.core.parsers.MyParser;
import com.epam.training.taranovski.spring.core.parsers.MySaxParser;
import com.epam.training.taranovski.spring.core.parsers.MyStaxParser;
import com.epam.training.taranovski.spring.core.parsers.ParserTypes;

/**
 *
 * @author Alyx
 */
public class GenericXmlApplicationContext {

    private final XmlBeanDefinitionReader reader;
    private MyParser parser;
    private String mySpringXMLConfigFile;

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
        switch (parserType) {
            case DOM: {
                parser = new MyDomParser();
                break;
            }
            case SAX: {
                parser = new MySaxParser();
                break;
            }
            case StAX: {
                parser = new MyStaxParser();
                break;
            }
        }
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
        return null;
    }
//- создает и возвращает экземпляр BeanFactory, в котором хранится ссылка на объектное представление конфигурационного xml-файла
}

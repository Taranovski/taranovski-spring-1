/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core;

import com.epam.training.taranovski.spring.core.parsers.MyBeansParser;
import com.epam.training.taranovski.spring.core.parsers.ParserTypes;
import com.epam.training.taranovski.spring.core.parsers.dom.MyDomParser;

/**
 *
 * @author Alyx
 */
public class XmlBeanDefinitionReader {

    private static XmlBeanDefinitionReader instance;

    private static ParserTypes parserType;
    private static String mySpringXMLConfigFile;
    private static BeanFactory beanFactory;
    private static MyBeansParser parser;
    
    /**
     *
     * @param mySpringXMLConfigFile
     * @param parserType
     * @param content
     * @return
     */
    public static XmlBeanDefinitionReader getInstance(String mySpringXMLConfigFile, ParserTypes parserType, GenericXmlApplicationContext content) {
        if (instance == null) {
            instance = new XmlBeanDefinitionReader(mySpringXMLConfigFile, parserType, content);
        }
        return instance;
    }

    /**
     *
     * @param mySpringXMLConfigFile
     * @param parserType
     * @param content
     */
    private XmlBeanDefinitionReader(String mySpringXMLConfigFile, ParserTypes parserType, GenericXmlApplicationContext content) {
        XmlBeanDefinitionReader.parserType = parserType;
        XmlBeanDefinitionReader.mySpringXMLConfigFile = mySpringXMLConfigFile;
        getParser().parse();

    }

    /**
     *
     * @param parserType
     * @param mySpringXMLConfigFile
     * @return
     */
    private MyBeansParser getParser() {
        switch (parserType) {
            case DOM: {
                if (parser == null) {
                    parser = MyDomParser.getInstance(mySpringXMLConfigFile, this);
                }
                return parser;
            }
            case SAX: {
                return MyDomParser.getInstance(mySpringXMLConfigFile, this);
//                parser = new MySaxParser(mySpringXMLConfigFile);
            }
            case StAX: {
                return MyDomParser.getInstance(mySpringXMLConfigFile, this);
//                parser = new MyStaxParser(mySpringXMLConfigFile);
            }
            default: {
                return null;
            }
        }
    }

    /**
     *
     * @return
     */
    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    
}

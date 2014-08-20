/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core;

import com.epam.training.taranovski.spring.core.parsers.MyDomParser;
import com.epam.training.taranovski.spring.core.parsers.MyParser;
import com.epam.training.taranovski.spring.core.parsers.ParserTypes;

/**
 *
 * @author Alyx
 */
public class XmlBeanDefinitionReader {

    private ParserTypes parserType;
    private String mySpringXMLConfigFile;
    private BeanFactory beanFactory;

    /**
     *
     * @param parserType
     * @param mySpringXMLConfigFile
     * @return
     */
    private MyParser getParser() {
        switch (parserType) {
            case DOM: {
                return new MyDomParser(mySpringXMLConfigFile);
            }
            case SAX: {
                return new MyDomParser(mySpringXMLConfigFile);
//                parser = new MySaxParser(mySpringXMLConfigFile);
            }
            case StAX: {
                return new MyDomParser(mySpringXMLConfigFile);
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
        return new BeanFactoryImpl();
    }
}

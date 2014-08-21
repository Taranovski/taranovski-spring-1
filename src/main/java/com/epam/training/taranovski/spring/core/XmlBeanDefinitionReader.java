/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core;

import com.epam.training.taranovski.spring.core.parsers.dom.MyDomParser;
import com.epam.training.taranovski.spring.core.parsers.MyBeansParser;
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
     * @param mySpringXMLConfigFile
     * @param parserType
     */
    public XmlBeanDefinitionReader(String mySpringXMLConfigFile, ParserTypes parserType) {
        this.parserType = parserType;
        this.mySpringXMLConfigFile = mySpringXMLConfigFile;
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
                return new MyDomParser(mySpringXMLConfigFile, this);
            }
            case SAX: {
                return new MyDomParser(mySpringXMLConfigFile, this);
//                parser = new MySaxParser(mySpringXMLConfigFile);
            }
            case StAX: {
                return new MyDomParser(mySpringXMLConfigFile, this);
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
        if (beanFactory == null) {
            beanFactory = new BeanFactoryImpl(getParser().parse());
        }
        return beanFactory;
    }
}

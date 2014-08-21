/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core;

import com.epam.training.taranovski.spring.core.parsers.MyXMLValidator;
import com.epam.training.taranovski.spring.core.parsers.ParserTypes;

/**
 *
 * @author Alyx
 */
public class GenericXmlApplicationContext {

    private static XmlBeanDefinitionReader reader;
    private static String mySpringXMLConfigFile;
    private static String mySpringSchemaFile;
    private static ParserTypes parserType = ParserTypes.DOM;
    private static boolean validating = false;

    private static GenericXmlApplicationContext instance;

    /**
     *
     * @return
     */
    public static GenericXmlApplicationContext getContext() {
        if (instance == null) {
            instance = new GenericXmlApplicationContext();
            reader = XmlBeanDefinitionReader.getInstance(mySpringXMLConfigFile, parserType, instance);
        }
        return instance;
    }

    

    /**
     *
     * @param mySpringXMLConfigFile
     * @param mySpringSchemaFile
     * @return
     */
    public static GenericXmlApplicationContext getContext(String mySpringXMLConfigFile, String mySpringSchemaFile) {
        if (instance == null) {
            instance = new GenericXmlApplicationContext(mySpringXMLConfigFile, mySpringSchemaFile);
            reader = XmlBeanDefinitionReader.getInstance(mySpringXMLConfigFile, parserType, instance);
        }
        return instance;
    }

    

    /**
     *
     * @param mySpringXMLConfigFile
     * @param mySpringSchemaFile
     * @param parserType
     * @return
     */
    public static GenericXmlApplicationContext getContext(String mySpringXMLConfigFile,
            String mySpringSchemaFile, ParserTypes parserType) {
        if (instance == null) {
            instance = new GenericXmlApplicationContext(mySpringXMLConfigFile, mySpringSchemaFile, parserType);
            reader = XmlBeanDefinitionReader.getInstance(mySpringXMLConfigFile, parserType, instance);
        }
        return instance;
    }

    

    /**
     * @param mySpringXMLConfigFile
     * @return
     */
    public static GenericXmlApplicationContext getContext(String mySpringXMLConfigFile) {
        if (instance == null) {
            instance = new GenericXmlApplicationContext(mySpringXMLConfigFile);
            reader = XmlBeanDefinitionReader.getInstance(mySpringXMLConfigFile, parserType, instance);
        }
        return instance;
    }

    

    /**
     *
     * @param validating
     */
    public void setValidating(boolean validating) {
        GenericXmlApplicationContext.validating = validating;

    }

    /**
     *
     * @param parserType
     */
    public void setParserType(ParserTypes parserType) {
        GenericXmlApplicationContext.parserType = parserType;
    }

    /**
     *
     * @param XMLFileLocation
     */
    public void load(String XMLFileLocation) {
        mySpringXMLConfigFile = XMLFileLocation;
        validate();
    }

    /**
     *
     */
    private void validate() {
        if (validating) {
            if (!MyXMLValidator.validate(mySpringXMLConfigFile, mySpringSchemaFile)) {
                throw new RuntimeException("validation failed");
            }
        }
    }

    /**
     *
     * @return
     */
    public BeanFactory getBeanFactory() {
        if (mySpringXMLConfigFile == null) {
            throw new RuntimeException("no config file specified");
        }
        return BeanFactoryImpl.getInstance();
    }
//- создает и возвращает экземпляр BeanFactory, в котором хранится ссылка на объектное представление конфигурационного xml-файла

    /**
     *
     */
    private GenericXmlApplicationContext() {
    }
    
    /**
     *
     * @param mySpringXMLConfigFile
     */
    private GenericXmlApplicationContext(String mySpringXMLConfigFile) {
        GenericXmlApplicationContext.mySpringXMLConfigFile = mySpringXMLConfigFile;
    }
    
    /**
     *
     * @param mySpringXMLConfigFile
     * @param mySpringSchemaFile
     */
    private GenericXmlApplicationContext(String mySpringXMLConfigFile, String mySpringSchemaFile) {
        GenericXmlApplicationContext.mySpringXMLConfigFile = mySpringXMLConfigFile;
        GenericXmlApplicationContext.mySpringSchemaFile = mySpringSchemaFile;
        validating = true;
        validate();
    }
    
    /**
     *
     * @param mySpringXMLConfigFile
     * @param mySpringSchemaFile
     * @param parserType
     */
    private GenericXmlApplicationContext(String mySpringXMLConfigFile,
            String mySpringSchemaFile, ParserTypes parserType) {
        GenericXmlApplicationContext.mySpringXMLConfigFile = mySpringXMLConfigFile;
        GenericXmlApplicationContext.mySpringSchemaFile = mySpringSchemaFile;
        validating = true;
        validate();
        GenericXmlApplicationContext.parserType = parserType;
    }
}

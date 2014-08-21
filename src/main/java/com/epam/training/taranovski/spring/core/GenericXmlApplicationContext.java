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

    private XmlBeanDefinitionReader reader;
    private String mySpringXMLConfigFile;
    private String mySpringSchemaFile;
    private ParserTypes parserType = ParserTypes.DOM;
    private boolean validating = false;
    
    public GenericXmlApplicationContext() {
    }

    /**
     *
     * @param mySpringXMLConfigFile
     * @param mySpringSchemaFile
     */
    public GenericXmlApplicationContext(String mySpringXMLConfigFile, String mySpringSchemaFile) {
        this.mySpringXMLConfigFile = mySpringXMLConfigFile;
        this.mySpringSchemaFile = mySpringSchemaFile;
        validating = true;
        validate();
    }

    /**
     *
     * @param mySpringXMLConfigFile
     * @param mySpringSchemaFile
     * @param parserType
     */
    public GenericXmlApplicationContext(String mySpringXMLConfigFile, String mySpringSchemaFile, ParserTypes parserType) {
        this.mySpringXMLConfigFile = mySpringXMLConfigFile;
        this.mySpringSchemaFile = mySpringSchemaFile;
        validating = true;
        validate();
        this.parserType = parserType;
    }

    /**
     *
     * @param mySpringXMLConfigFile
     */
    public GenericXmlApplicationContext(String mySpringXMLConfigFile) {
        this.mySpringXMLConfigFile = mySpringXMLConfigFile;
    }

    /**
     *
     * @param validating
     */
    public void setValidating(boolean validating) {
        this.validating = validating;

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
        if (reader == null) {
            reader = new XmlBeanDefinitionReader(mySpringXMLConfigFile, parserType);
        }
        return reader.getBeanFactory();

    }
//- создает и возвращает экземпляр BeanFactory, в котором хранится ссылка на объектное представление конфигурационного xml-файла

}

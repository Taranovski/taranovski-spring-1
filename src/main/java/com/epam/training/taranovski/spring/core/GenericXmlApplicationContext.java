/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core;

import com.epam.training.taranovski.spring.core.parsers.ParserTypes;

/**
 *
 * @author Alyx
 */
public class GenericXmlApplicationContext {

    private final XmlBeanDefinitionReader reader;

    public void setValidating(boolean validating) {
    }

    public void setParserType(ParserTypes parserType) {
    }

    public void load(String XMLFileLocation) {
    }

    public BeanFactory getBeanFactory() {
        return null;
    }
//- создает и возвращает экземпляр BeanFactory, в котором хранится ссылка на объектное представление конфигурационного xml-файла
}

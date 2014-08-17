/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core;

import com.epam.training.taranovski.spring.interfaces.GreetingService;

/**
 *
 * @author Alyx
 */
public class MySpringRunner {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("SpringXMLConfig.xml");
        context.setValidating(true);
        context.setParserType(ParserTypes.StAX);

        BeanFactory factory = context.getBeanFactory();
        GreetingService greetingService = factory.getBean("greetingService", GreetingService.class);
        
    }
}

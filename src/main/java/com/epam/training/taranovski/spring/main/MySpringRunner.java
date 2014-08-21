/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.main;

import com.epam.training.taranovski.spring.core.BeanFactory;
import com.epam.training.taranovski.spring.core.GenericXmlApplicationContext;
import com.epam.training.taranovski.spring.core.parsers.ParserTypes;
import com.epam.training.taranovski.spring.interfaces.GreetingService;
import com.epam.training.taranovski.spring.interfaces.SomeBean;
import com.epam.training.taranovski.spring.interfaces.SomeBean1;
import com.epam.training.taranovski.spring.interfaces.SomeBean2;

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
        context.setValidating(false);
        context.setParserType(ParserTypes.DOM);
        context.load(".\\src\\main\\java\\mySpringConfig.xml");

        BeanFactory factory = context.getBeanFactory();

        GreetingService greetingService = factory.getBean("GreetingService", GreetingService.class);
        greetingService.sayHello();
        greetingService.showReferenceItem();
        System.out.println(greetingService);

        GreetingService greetingService1 = factory.getBean("GreetingService", GreetingService.class);
        greetingService1.sayHello();
        greetingService1.showReferenceItem();
        System.out.println(greetingService1);

        greetingService1.setSomeConstructorString("i am a singleton!");

        greetingService.sayHello();
        greetingService.showReferenceItem();
        System.out.println(greetingService);

        greetingService1.sayHello();
        greetingService1.showReferenceItem();
        System.out.println(greetingService1);

        SomeBean someBean = factory.getBean("SomeBean", SomeBean.class);
        someBean.showMyContents();
        System.out.println(someBean);

        SomeBean someBean111 = factory.getBean("SomeBean", SomeBean.class);
        someBean111.showMyContents();
        System.out.println(someBean111);

        someBean111.changeSomeProperty1("i am not a singleton");
        someBean.showMyContents();
        System.out.println(someBean);

        someBean111.showMyContents();
        System.out.println(someBean111);

        SomeBean1 someBean1 = factory.getBean("SomeBean1", SomeBean1.class);
        someBean1.showMySelf();
        System.out.println(someBean1);
        
        SomeBean1 someBean122 = factory.getBean("SomeBean1", SomeBean1.class);
        someBean122.showMySelf();
        System.out.println(someBean122);
        someBean122.setSomeProp3("i'm not a singleton");
        
        someBean1.showMySelf();
        System.out.println(someBean1);
        
        someBean122.showMySelf();
        System.out.println(someBean122);

        SomeBean2 someBean2 = factory.getBean("SomeBean2", SomeBean2.class);
        someBean2.showYourSelf();

    }
}

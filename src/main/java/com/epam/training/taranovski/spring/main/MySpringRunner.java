/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.main;

import com.epam.training.taranovski.spring.core.BeanFactory;
import com.epam.training.taranovski.spring.core.GenericXmlApplicationContext;
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
        GenericXmlApplicationContext context = GenericXmlApplicationContext.getContext(".\\src\\main\\java\\mySpringConfig.xml");
        
        BeanFactory factory = context.getBeanFactory();

        GreetingService greetingService = factory.getBean("GreetingService", GreetingService.class);
        greetingService.sayHello();
        greetingService.showReferenceItem();

        GreetingService greetingService1 = factory.getBean("GreetingService", GreetingService.class);
        greetingService1.sayHello();
        greetingService1.showReferenceItem();

        greetingService1.setSomeConstructorString("i am a singleton!");
        greetingService1.sayHello();
        greetingService.sayHello();
        SomeBean someBean = factory.getBean("SomeBean", SomeBean.class);
        someBean.showMyContents();

        SomeBean someBean111 = factory.getBean("SomeBean", SomeBean.class);
        someBean111.showMyContents();

        someBean111.changeSomeProperty1("i am not a singleton");
        someBean.showMyContents();
        someBean111.showMyContents();

        SomeBean1 someBean1 = factory.getBean("SomeBean1", SomeBean1.class);
        someBean1.showMySelf();

        SomeBean1 someBean122 = factory.getBean("SomeBean1", SomeBean1.class);
        someBean122.showMySelf();

        someBean122.setSomeProp3("i'm not a singleton");
        someBean1.showMySelf();
        someBean122.showMySelf();

        SomeBean2 someBean2 = factory.getBean("SomeBean2", SomeBean2.class);
        someBean2.showYourSelf();

    }
}

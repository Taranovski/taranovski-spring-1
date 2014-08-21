/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.classes;

import com.epam.training.taranovski.spring.interfaces.GreetingService;
import com.epam.training.taranovski.spring.interfaces.SomeBean;

/**
 *
 * @author Alyx
 * @param <T>
 */
public class GreetingServiceImpl<T extends SomeBean> implements GreetingService {

    private String someConstructorString;
    private T someConstructorReference;

    /**
     *
     * @param someConstructorString
     * @param someConstructorReference
     */
    public GreetingServiceImpl(String someConstructorString, T someConstructorReference) {
        this.someConstructorString = someConstructorString;
        this.someConstructorReference = someConstructorReference;
    }

    /**
     *
     * @param someConstructorString
     */
    @Override
    public void setSomeConstructorString(String someConstructorString) {
        this.someConstructorString = someConstructorString;
    }

    /**
     *
     */
    @Override
    public void sayHello() {
        System.out.println(someConstructorString);
    }

    /**
     *
     */
    @Override
    public void showReferenceItem() {
        System.out.println(someConstructorReference);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "i am a GreetingServiceImpl with someConstructorString = "
                + someConstructorString
                + " and someConstructorReference = "
                + someConstructorReference;
    }
}

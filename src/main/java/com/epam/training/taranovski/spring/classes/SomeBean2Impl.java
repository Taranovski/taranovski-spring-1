/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.classes;

import com.epam.training.taranovski.spring.interfaces.SomeBean2;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public class SomeBean2Impl implements SomeBean2 {

    private String someProperty4;

    /**
     *
     * @return 
     */
    @Override
    public String showYourSelf() {
        return this.toString();
    }

    /**
     *
     * @param prop
     */
    @Override
    public void changePror(String prop) {
        someProperty4 = prop;
    }

    /**
     * @return the someProperty4
     */
    public String getSomeProperty4() {
        return someProperty4;
    }

    /**
     * @param someProperty4 the someProperty4 to set
     */
    public void setSomeProperty4(String someProperty4) {
        this.someProperty4 = someProperty4;
    }

    @Override
    public String toString() {
        return "i am SomeBean2Impl, my someProperty4 = " + someProperty4;
    }
}

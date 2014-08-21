/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.classes;

import com.epam.training.taranovski.spring.interfaces.SomeBean1;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public class SomeBean1Impl implements SomeBean1 {

    private String someProperty3;

    /**
     *
     */
    @Override
    public void showMySelf() {
        System.out.println(this);
    }

    /**
     * @return the someProperty3
     */
    public String getSomeProperty3() {
        return someProperty3;
    }

    /**
     * @param someProperty3 the someProperty3 to set
     */
    public void setSomeProperty3(String someProperty3) {
        this.someProperty3 = someProperty3;
    }

    /**
     *
     * @param newProp
     */
    @Override
    public void setSomeProp3(String newProp) {
        someProperty3 = newProp;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "i am SomeBean1Impl, my someProperty3 = " + someProperty3;
    }
}

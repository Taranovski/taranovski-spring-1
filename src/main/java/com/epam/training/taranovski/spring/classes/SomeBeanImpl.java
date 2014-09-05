/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.classes;

import com.epam.training.taranovski.spring.interfaces.SomeBean;
import com.epam.training.taranovski.spring.interfaces.SomeBean1;
import com.epam.training.taranovski.spring.interfaces.SomeBean2;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public class SomeBeanImpl implements SomeBean {

    private SomeBean2 constructorArgBean;
    private String someProperty1;
    private SomeBean1 someProperty2;

    /**
     *
     * @param constructorArgBean
     */
    public SomeBeanImpl(SomeBean2 constructorArgBean) {
        this.constructorArgBean = constructorArgBean;
    }

    /**
     *
     * @return 
     */
    @Override
    public String showMyContents() {
        return this.toString();
    }

    /**
     * @return the someProperty1
     */
    public String getSomeProperty1() {
        return someProperty1;
    }

    /**
     * @param someProperty1 the someProperty1 to set
     */
    public void setSomeProperty1(String someProperty1) {
        this.someProperty1 = someProperty1;
    }

    /**
     * @return the someProperty2
     */
    public SomeBean1 getSomeProperty2() {
        return someProperty2;
    }

    /**
     * @param someProperty2 the someProperty2 to set
     */
    public void setSomeProperty2(SomeBean1 someProperty2) {
        this.someProperty2 = someProperty2;
    }

    /**
     *
     * @param prop
     */
    @Override
    public void changeSomeProperty1(String prop) {
        someProperty1 = prop;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "i am SomeBeanImpl, my contents are: "
                + " constructorArgBean = " + constructorArgBean
                + " someProperty1 = " + someProperty1
                + " someProperty2 = " + someProperty2;
    }
}

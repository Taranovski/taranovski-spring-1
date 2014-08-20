/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public class Bean {

    private String beadId;
    private String className;
    private Class beanClass;

    private boolean singleton = false;

    private List<Object> constructorArgs;
    private Map<String, Object> parameters;

    public Bean() {
        constructorArgs = new LinkedList<>();
        parameters = new LinkedHashMap<>();
    }

    /**
     * @return the beadId
     */
    public String getBeadId() {
        return beadId;
    }

    /**
     * @param beadId the beadId to set
     */
    public void setBeadId(String beadId) {
        this.beadId = beadId;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
        setBeanClass();
    }

    /**
     * @return the beanClass
     */
    public Class getBeanClass() {
        return beanClass;
    }

    /**
     * @param beanClass the beanClass to set
     */
    private void setBeanClass() {
        try {
            this.beanClass = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the constructorArgs
     */
    public List<Object> getConstructorArgs() {
        return constructorArgs;
    }

    /**
     * @param constructorArgs the constructorArgs to set
     */
    public void addConstructorArgs(Object... constructorArgs) {
        for (Object ob : constructorArgs) {
            this.getConstructorArgs().add(ob);
        }
    }

    /**
     * @return the parameters
     */
    public Map<String, Object> getParameters() {
        return parameters;
    }

    /**
     * @param name
     * @param value
     */
    public void addParameter(String name, Object value) {
        this.getParameters().put(name, value);
    }

    /**
     * @return the singleton
     */
    public boolean isSingleton() {
        return singleton;
    }

}

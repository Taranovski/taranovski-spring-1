/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
    private volatile Object single;

    private List<Object> constructorArgs;
    private Map<String, Object> parameters;

    public Bean() {
        constructorArgs = new LinkedList<>();
        parameters = new TreeMap<>();
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
            this.constructorArgs.add(ob);
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
        this.parameters.put(name, value);
    }

    public Object getObject() {

        if (singleton) {
            if (single != null) {
                return single;
            }
        }

        Class[] constructorParameterTypes = new Class[constructorArgs.size()];

        for (int i = 0; i < constructorArgs.size(); i++) {
            constructorParameterTypes[i] = constructorArgs.get(i).getClass();
        }

        Constructor constructor = null;
        String parameterName = null;
        Object parameterValue = null;
        Class parameterClass = null;
        Method setterMethod = null;

        Object ob = null;
        try {
            constructor = beanClass.getConstructor(constructorParameterTypes);
            ob = constructor.newInstance(constructorArgs.toArray());

            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                parameterName = entry.getKey();
                parameterValue = entry.getValue();
                parameterClass = entry.getValue().getClass();

                try {
                    setterMethod = beanClass.getDeclaredMethod("set"
                            + parameterName.substring(0, 1).toUpperCase()
                            + parameterName.substring(1),
                            parameterClass);
                    setterMethod.invoke(ob, parameterValue);
                } catch (NoSuchMethodException |
                        SecurityException |
                        IllegalAccessException |
                        IllegalArgumentException |
                        InvocationTargetException ex) {
                    Logger.getLogger(Bean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (singleton) {
                single = ob;
            }
        } catch (NoSuchMethodException |
                SecurityException |
                InstantiationException |
                IllegalAccessException |
                IllegalArgumentException |
                InvocationTargetException ex) {
            Logger.getLogger(Bean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ob;
    }

}

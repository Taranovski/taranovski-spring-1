/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public class BeanFactoryImpl implements BeanFactory {

    private Map<String, Bean> beanMap = new HashMap<>();
    private Map<String, Object> singletoneMap = new HashMap<>();

    public BeanFactoryImpl(List<Bean> list) {
        for (Bean bean : list) {
            beanMap.put(bean.getBeadId(), bean);
        }
    }

    /**
     *
     * @param string
     * @return
     */
    @Override
    public Object getBean(String string) {
        Bean bean = beanMap.get(string);
        Class beanClass = bean.getBeanClass();

        if (bean.isSingleton()) {
            return singletoneMap.get(string);
        }

        Constructor constructor = null;
        String parameterName = null;
        Object parameterValue = null;
        Class parameterClass = null;
        Method setterMethod = null;

        Object ob = null;

        try {
            if (bean.getConstructorArgs().isEmpty()) {
                ob = bean.getBeanClass().newInstance();
            } else {
                Class[] constructorParameterTypes = new Class[bean.getConstructorArgs().size()];

                for (int i = 0; i < bean.getConstructorArgs().size(); i++) {
                    constructorParameterTypes[i] = bean.getConstructorArgs().get(i).getClass();
                }
                constructor = bean.getBeanClass().getConstructor(constructorParameterTypes);
                ob = constructor.newInstance(bean.getConstructorArgs().toArray());
            }

            for (Map.Entry<String, Object> entry : bean.getParameters().entrySet()) {
                parameterName = entry.getKey();
                parameterValue = entry.getValue();
                parameterClass = entry.getValue().getClass();

                setterMethod = bean.getBeanClass().getDeclaredMethod("set"
                        + parameterName.substring(0, 1).toUpperCase()
                        + parameterName.substring(1),
                        parameterClass);
                setterMethod.invoke(ob, parameterValue);
            }
        } catch (InstantiationException | 
                NoSuchMethodException |
                SecurityException |
                IllegalAccessException |
                IllegalArgumentException |
                InvocationTargetException ex) {
            Logger.getLogger(Bean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (bean.isSingleton()) {
            singletoneMap.put(string, beanClass.cast(ob));
        }

        return beanClass.cast(ob);
    }

    /**
     *
     * @param <T>
     * @param string
     * @param type
     * @return
     */
    @Override
    public <T> T
            getBean(String string, Class<T> type
            ) {
        return (T) getBean(string);
    }

}

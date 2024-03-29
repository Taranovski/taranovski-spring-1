/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core;

/**
 *
 * @author Alyx
 */
public interface BeanFactory {

    /**
     *
     * @param string
     * @return
     */
    public Object getBean(String string);
//- создает экземпляр класса типа Object прописанного в конфигурационном xml-файле по его id

    /**
     *
     * @param <T>
     * @param string
     * @param type
     * @return
     */
    public <T extends Object> T getBean(String string, Class<T> type);
//- создает экземпляр класса типа T прописанного в конфигурационном xml-файле по его id

    public void addBean(Bean parseBean);
}

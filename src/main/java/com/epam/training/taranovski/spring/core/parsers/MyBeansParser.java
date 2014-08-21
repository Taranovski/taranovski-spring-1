/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.training.taranovski.spring.core.parsers;

import com.epam.training.taranovski.spring.core.Bean;
import java.util.List;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public interface MyBeansParser {

    /**
     *
     * @return
     */
    List<Bean> parse();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.spring.core.parsers;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public class MyXMLValidator {

    private MyXMLValidator() {
    }

    /**
     *
     * @param xml
     * @param xsd
     * @return
     */
    public static boolean validate(String xml, String xsd) {
        try {
            //creating schema factory (XMLConstants.W3C_XML_SCHEMA_NS_URI - syntax rules for this schema)
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            //creating new schema (from file)
            Schema schema = schemaFactory.newSchema(new File(xsd));
            //creating new validator from schema
            Validator validator = schema.newValidator();
            //validating file for schema compliance
            validator.validate(new StreamSource(new File(xml)));
        } catch (SAXException | IOException ex) {
            return false;
        }
        return true;
    }
}

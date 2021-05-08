package com.epam.secondtask.validator;

import com.epam.secondtask.builder.sax.MedicineErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class DataXmlValidator {

    public boolean validateXmlFile(String xmlPath, String xsdPath) {

        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(xsdPath);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlPath);
            validator.setErrorHandler(new MedicineErrorHandler());
            validator.validate(source);
            return true;
        } catch (SAXException | IOException e) {
            System.err.println(xmlPath + " is not correct or valid");
        }
        return false;
    }
}
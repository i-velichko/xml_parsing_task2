package com.epam.secondtask.validator;

import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import com.epam.secondtask.builder.sax.MedicineErrorHandler;
import com.epam.secondtask.exception.MedicineXmlException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

public class DataXmlValidator {
private final static Logger LOGGER = LogManager.getLogger();

    public boolean validateXmlFile(String xmlPath, String xsdPath) throws MedicineXmlException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(xsdPath);
        boolean validationResult = false;
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlPath);
            validator.validate(source);
            validationResult = true;
        } catch (SAXException e) {
            LOGGER.error("xml is not valid " + e.getMessage());
        } catch (IOException e) {
            throw new MedicineXmlException("file path error " + e.getMessage());
        }
        return validationResult;
    }
}
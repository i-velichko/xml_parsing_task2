package com.epam.secondtask.builder.sax;

import com.epam.secondtask.builder.AbstractMedicinesBuilder;
import com.epam.secondtask.exception.MedicineXmlException;
import com.epam.secondtask.model.Medicine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class MedicinesSaxBuilder extends AbstractMedicinesBuilder {
    private final static Logger LOGGER = LogManager.getLogger();
    private final MedicineHandler handler = new MedicineHandler();
    private List<Medicine> medicines;
    private XMLReader reader;

    public MedicinesSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.error(e.getMessage());
        }
        reader.setErrorHandler(new MedicineErrorHandler());
        reader.setContentHandler(handler);
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    @Override
    public void buildListMedicines(String pathToFile) throws MedicineXmlException {
        try {
            reader.parse(pathToFile);
        } catch (IOException | SAXException e) {
            throw new MedicineXmlException("Can not parse file with SAX parser" + e.getMessage());
        }
        medicines = handler.getMedicines();
        LOGGER.info("The list of medicine was created by SAX parser.");
    }
}


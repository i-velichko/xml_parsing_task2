package com.epam.secondtask.builder.sax;

import com.epam.secondtask.builder.AbstractMedicinesBuilder;
import com.epam.secondtask.model.Medicine;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;


public class MedicinesSaxBuilder extends AbstractMedicinesBuilder {
    private final static String pathToFile = "src/main/resources/data/medicines.xml";
    private List<Medicine> medicines;
    private MedicineHandler handler = new MedicineHandler();
    private XMLReader reader;

    public MedicinesSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace(); // log
        }
        reader.setErrorHandler(new MedicineErrorHandler());
        reader.setContentHandler(handler);
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    @Override
    public void buildListMedicines(String pathToFile) {
        try {
            reader.parse(pathToFile);
        } catch (IOException | SAXException e) {
            e.printStackTrace(); // log
        }
        medicines = handler.getMedicines();
    }
}


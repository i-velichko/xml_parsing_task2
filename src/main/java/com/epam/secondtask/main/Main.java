package com.epam.secondtask.main;

import com.epam.secondtask.builder.AbstractMedicinesBuilder;
import com.epam.secondtask.builder.MedicineBuilderFactory;
import com.epam.secondtask.exception.MedicineXmlException;
import com.epam.secondtask.validator.DataXmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String pathToFile = "src/main/resources/data/medicines.xml";
    private final static String schemaName = "src/main/resources/data/medicine.xsd";
    public static void main(String[] args) throws MedicineXmlException {

        DataXmlValidator dataXmlValidator = new DataXmlValidator();
        try {
            System.out.println(dataXmlValidator.validateXmlFile(pathToFile,schemaName ));
        } catch (MedicineXmlException e) {
            e.printStackTrace();
        }

        String type = "stax";
        MedicineBuilderFactory builderFactory = new MedicineBuilderFactory();
        AbstractMedicinesBuilder builder = builderFactory.createMedicineBuilder(type);
        builder.buildListMedicines(pathToFile);
        builder.getMedicines().forEach(System.out::println);

    }
}


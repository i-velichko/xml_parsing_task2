package com.epam.secondtask.main;

import com.epam.secondtask.builder.AbstractMedicinesBuilder;
import com.epam.secondtask.builder.MedicineBuilderFactory;
import com.epam.secondtask.exception.MedicineXmlException;
import com.epam.secondtask.validator.DataXmlValidator;

public class Main {
    private final static String pathToFile = "src/main/resources/data/medicines.xml";
    private final static String schemaName = "src/main/resources/data/medicine.xsd";
    public static void main(String[] args) throws MedicineXmlException {

//        MedicinesDomBuilder domBuilder = new MedicinesDomBuilder();
//        domBuilder.buildSetMedicines();
//
//
//        MedicinesStaxBuilder staxBuilder = new MedicinesStaxBuilder();
//        staxBuilder.buildListMedicines(pathToFile);
//        staxBuilder.getMedicines().forEach(System.out::println);
//
//        MedicinesSaxBuilder saxBuilder = new MedicinesSaxBuilder();
//        saxBuilder.buildListMedicines(pathToFile);
//        saxBuilder.getMedicines().forEach(System.out::println);
//
//        DataXmlValidator dataXmlValidator = new DataXmlValidator();
//        try {
//            System.out.println(dataXmlValidator.validateXmlFile(pathToFile,schemaName ));
//        } catch (MedicineXmlException e) {
//            e.printStackTrace();
//        }

        String type = "sax";
        MedicineBuilderFactory builderFactory = new MedicineBuilderFactory();
        AbstractMedicinesBuilder builder = builderFactory.createMedicineBuilder(type);
        builder.buildListMedicines(pathToFile);
        builder.getMedicines().forEach(System.out::println);

    }
}

//todo дописать equals и hashCode везде
//todo логи
//todo тесты
//todo свой эксепшн
//todo причесать
//todo тайпы в модел
//todo мейби везде сунуть энамы вместо стрингов

package com.epam.secondtask.main;

import com.epam.secondtask.builder.common.AbstractMedicinesBuilder;
import com.epam.secondtask.builder.common.MedicineBuilderFactory;
import com.epam.secondtask.validator.DataXmlValidator;

public class Main {
    private final static String pathToFile = "src/main/resources/data/medicines.xml";
    private final static String schemaName = "src/main/resources/data/medicine.xsd";
    public static void main(String[] args) {

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
        DataXmlValidator dataXmlValidator = new DataXmlValidator();
        System.out.println(dataXmlValidator.validateXmlFile(pathToFile,schemaName ));

//        String type = "sax";
//        AbstractMedicinesBuilder builder = MedicineBuilderFactory.createMedicineBuilder(type);
//        builder.buildListMedicines(pathToFile);
//        builder.getMedicines().forEach(System.out::println);

    }
}

//todo дописать equals и hashCode везде
//todo логи
//todo тесты
//todo свой эксепшн
//todo причесать

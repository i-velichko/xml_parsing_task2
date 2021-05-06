package com.epam.secondtask;

import com.epam.secondtask.builder.MedicinesSaxBuilder;
import com.epam.secondtask.builder.MedicinesStaxBuilder;

public class Main {
    private final static String pathToFile = "src/main/resources/data/medicines.xml";
    public static void main(String[] args) {
        MedicinesStaxBuilder staxBuilder = new MedicinesStaxBuilder();
        staxBuilder.buildListMedicines(pathToFile);
        staxBuilder.getMedicines().forEach(System.out::println);
    }
}

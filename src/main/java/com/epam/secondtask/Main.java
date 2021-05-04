package com.epam.secondtask;

import com.epam.secondtask.builder.MedicinesSaxBuilder;

public class Main {
    private final static String pathToFile = "src/main/resources/data/medicines.xml";
    public static void main(String[] args) {

        MedicinesSaxBuilder saxBuilder = new MedicinesSaxBuilder();
        saxBuilder.buildSetMedicines(pathToFile);
        System.out.println(saxBuilder.getMedicines());
    }
}

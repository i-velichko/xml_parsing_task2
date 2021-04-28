package com.epam.secondtask.parser.impl;

import com.epam.secondtask.model.Medicine;
import com.epam.secondtask.parser.AbstractMedicineBuilder;

import java.util.List;

public class DomMedicineBuilderImpl implements AbstractMedicineBuilder {
    private final static String DATA_FILE_PATH = "src/main/resources/data/medicines.xml";

    @Override
    public List<Medicine> buildMedicinesList(String filePath) {
        return null;
    }
}

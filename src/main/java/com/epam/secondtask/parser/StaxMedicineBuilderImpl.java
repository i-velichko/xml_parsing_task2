package com.epam.secondtask.parser;

import com.epam.secondtask.model.Medicine;

import java.util.List;

public class StaxMedicineBuilderImpl implements AbstractMedicineBuilder {
    private final static String DATA_FILE_PATH = "src/main/resources/data/medicines.xml";
    @Override
    public List<Medicine> buildMedicinesList(String filePath) {
        return null;
    }
}

package com.epam.secondtask.builder;

import com.epam.secondtask.model.Medicine;
import org.xml.sax.XMLReader;

import java.util.Set;

public class MedicinesSaxBuilder extends AbstractMedicinesBuilder {
    private MedicineHandler handler;
    private XMLReader reader;
    public MedicinesSaxBuilder() { // more code
    }
    public MedicinesSaxBuilder(Set<Medicine> medicines) {
        super(medicines);
// more code
    }
    @Override
    public void buildSetMedicines(String fileName) {
// more code
    }
    // private methods
}

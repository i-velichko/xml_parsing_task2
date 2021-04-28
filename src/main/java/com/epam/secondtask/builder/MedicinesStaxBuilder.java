package com.epam.secondtask.builder;

import com.epam.secondtask.model.Medicine;

import javax.xml.stream.XMLInputFactory;
import java.util.Set;

public class MedicinesStaxBuilder extends AbstractMedicinesBuilder {
    private XMLInputFactory inputFactory;
    public MedicinesStaxBuilder() {
// more code
    }
    public MedicinesStaxBuilder(Set<Medicine> medicines) {
        super(medicines);
// more code
    }
    @Override
    public void buildSetMedicines(String fileName) {
// more code
    }
// private methods
}

package com.epam.secondtask.builder.common;

import com.epam.secondtask.model.Medicine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractMedicinesBuilder {
    protected List<Medicine> medicines;

    public AbstractMedicinesBuilder() {
        medicines = new ArrayList<Medicine>();
    }

    public AbstractMedicinesBuilder(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public abstract void buildListMedicines(String pathToFile);

}

package com.epam.secondtask.builder;

import com.epam.secondtask.model.Medicine;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractMedicinesBuilder {
    protected Set<Medicine> medicines;

    public AbstractMedicinesBuilder() {
        medicines = new HashSet<Medicine>();
    }

    public AbstractMedicinesBuilder(Set<Medicine> medicines) {
        this.medicines = medicines;
    }

    public Set<Medicine> getMedicines() {
        return medicines;
    }

    public abstract void buildSetMedicines(String filename);

}

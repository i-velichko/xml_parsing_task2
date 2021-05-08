package com.epam.secondtask.model;

import com.epam.secondtask.builder.type.MedicineGroupType;

import java.util.List;

public class Vaccine extends Medicine {
    private String bacteria;

    public Vaccine() {
    }

    public Vaccine(String medicineId, String prescription, String medicineName, MedicineGroupType medicineGroup, List<String> analogs, List<Version> medicineVersions) {
        super(medicineId, prescription, medicineName, medicineGroup, analogs, medicineVersions);
    }

    public String getBacteria() {
        return bacteria;
    }

    public void setBacteria(String bacteria) {
        this.bacteria = bacteria;
    }

    @Override
    public String toString() {
        return "Vaccine" +
                " - " + super.toString() +
        "bacteria='" + bacteria + '\'';
    }


}

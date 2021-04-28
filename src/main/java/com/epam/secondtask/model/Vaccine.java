package com.epam.secondtask.model;

import com.epam.secondtask.model.enumeration.MedicineGroupType;

import java.util.List;

public class Vaccine extends Medicine {
    private String bacteria;

    public Vaccine(String medicineId, boolean prescription, String medicineName, MedicineGroupType medicineGroup, List<String> analogs, List<Version> medicineVersions) {
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
        return "Vaccine{" +
                "bacteria='" + bacteria + '\'' +
                '}';
    }
}

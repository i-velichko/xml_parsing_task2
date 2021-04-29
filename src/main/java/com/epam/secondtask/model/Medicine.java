package com.epam.secondtask.model;

import com.epam.secondtask.model.enumeration.MedicineGroupType;

import java.util.Collections;
import java.util.List;

public class Medicine {
    private String medicineId;
    private boolean prescription;
    private String medicineName;
    private MedicineGroupType medicineGroup;
    private List<String> analogs;
    private List<Version> medicineVersions;

    public Medicine() {
    }

    public Medicine(String medicineId, boolean prescription, String medicineName, MedicineGroupType medicineGroup, List<String> analogs, List<Version> medicineVersions) {
        this.medicineId = medicineId;
        this.prescription = prescription;
        this.medicineName = medicineName;
        this.medicineGroup = medicineGroup;
        this.analogs = analogs;
        this.medicineVersions = medicineVersions;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public boolean isPrescription() {
        return prescription;
    }

    public void setPrescription(boolean prescription) {
        this.prescription = prescription;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public MedicineGroupType getMedicineGroup() {
        return medicineGroup;
    }

    public void setMedicineGroup(MedicineGroupType medicineGroup) {
        this.medicineGroup = medicineGroup;
    }

    public List<String> getAnalogs() {
        return analogs;
    }

    public void setAnalogs(List<String> analogs) {
        this.analogs = analogs;
    }

    public List<Version> getMedicineVersions() {
        return medicineVersions;
    }

    public void setMedicineVersions(List<Version> medicineVersions) {
        this.medicineVersions = medicineVersions;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "medicineId='" + medicineId + '\'' +
                ", prescription=" + prescription +
                ", medicineName='" + medicineName + '\'' +
                ", medicineGroup=" + medicineGroup +
                ", analogs=" + analogs +
                ", medicineVersions=" + medicineVersions +
                '}';
    }
}

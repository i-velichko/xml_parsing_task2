package com.epam.secondtask.model;

import com.epam.secondtask.model.type.MedicineGroupType;

import java.util.List;

public class Medicine {
    private String medicineId;
    private String prescription;
    private String medicineName;
    private MedicineGroupType medicineGroup;
    private List<String> analogs;
    private List<Version> medicineVersions;

    public Medicine() {
    }

    public Medicine(String medicineId, String medicineName) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
    }

    public Medicine(String medicineId, String prescription, String medicineName, MedicineGroupType medicineGroup, List<String> analogs, List<Version> medicineVersions) {
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

    public String isPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medicine medicine = (Medicine) o;

        if (getMedicineId() != null ? !getMedicineId().equals(medicine.getMedicineId()) : medicine.getMedicineId() != null)
            return false;
        if (prescription != null ? !medicine.prescription.equals(prescription) : medicine.prescription != null)
            return false;
        if (getMedicineName() != null ? !getMedicineName().equals(medicine.getMedicineName()) : medicine.getMedicineName() != null)
            return false;
        if (getMedicineGroup() != medicine.getMedicineGroup()) return false;
        if (getAnalogs() != null ? !getAnalogs().equals(medicine.getAnalogs()) : medicine.getAnalogs() != null)
            return false;
        return getMedicineVersions() != null ? getMedicineVersions().equals(medicine.getMedicineVersions()) : medicine.getMedicineVersions() == null;
    }

    @Override
    public int hashCode() {
        int result = getMedicineId() != null ? getMedicineId().hashCode() : 0;
        result = 31 * result + (prescription != null ? prescription.hashCode() : 0);
        result = 31 * result + (getMedicineName() != null ? getMedicineName().hashCode() : 0);
        result = 31 * result + (getMedicineGroup() != null ? getMedicineGroup().hashCode() : 0);
        result = 31 * result + (getAnalogs() != null ? getAnalogs().hashCode() : 0);
        result = 31 * result + (getMedicineVersions() != null ? getMedicineVersions().hashCode() : 0);
        return result;
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

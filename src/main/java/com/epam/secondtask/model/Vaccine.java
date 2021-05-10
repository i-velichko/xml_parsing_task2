package com.epam.secondtask.model;

import com.epam.secondtask.model.type.MedicineGroupType;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Vaccine vaccine = (Vaccine) o;

        return getBacteria() != null ? getBacteria().equals(vaccine.getBacteria()) : vaccine.getBacteria() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getBacteria() != null ? getBacteria().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vaccine - ");
        sb.append(super.toString());
        sb.append("bacteria='").append(bacteria).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

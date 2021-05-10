package com.epam.secondtask.model;

import com.epam.secondtask.model.type.MedicineGroupType;

import java.util.List;

public class Homeopathy extends Medicine {
    private String activeSubstance;

    public Homeopathy() {
    }

    public Homeopathy(String medicineId, String prescription, String medicineName, MedicineGroupType medicineGroup, List<String> analogs, List<Version> medicineVersions) {
        super(medicineId, prescription, medicineName, medicineGroup, analogs, medicineVersions);
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public void setActiveSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
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

        Homeopathy that = (Homeopathy) o;

        return getActiveSubstance() != null ? getActiveSubstance().equals(that.getActiveSubstance()) : that.getActiveSubstance() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getActiveSubstance() != null ? getActiveSubstance().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Homeopathy - ");

        sb.append(super.toString());
        sb.append("activeSubstance='").append(activeSubstance).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

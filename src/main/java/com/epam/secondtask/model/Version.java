package com.epam.secondtask.model;

import com.epam.secondtask.model.type.MedicinePackageType;

import java.time.YearMonth;

public class Version {
    private String pharmCompany;
    private String medicineCertificate;
    private MedicinePackageType medicinePackage;
    private String medicineDosage;
    private YearMonth expirationDate;

    public Version() {
    }

    public Version(String pharmCompany, String medicineCertificate, MedicinePackageType medicinePackage, String medicineDosage, YearMonth expirationDate) {
        this.pharmCompany = pharmCompany;
        this.medicineCertificate = medicineCertificate;
        this.medicinePackage = medicinePackage;
        this.medicineDosage = medicineDosage;
        this.expirationDate = expirationDate;
    }

    public String getPharmCompany() {
        return pharmCompany;
    }

    public void setPharmCompany(String pharmCompany) {
        this.pharmCompany = pharmCompany;
    }

    public String getMedicineCertificate() {
        return medicineCertificate;
    }

    public void setMedicineCertificate(String medicineCertificate) {
        this.medicineCertificate = medicineCertificate;
    }

    public MedicinePackageType getMedicinePackage() {
        return medicinePackage;
    }

    public void setMedicinePackage(MedicinePackageType medicinePackage) {
        this.medicinePackage = medicinePackage;
    }

    public String getMedicineDosage() {
        return medicineDosage;
    }

    public void setMedicineDosage(String medicineDosage) {
        this.medicineDosage = medicineDosage;
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(YearMonth expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Version version = (Version) o;

        if (getPharmCompany() != null ? !getPharmCompany().equals(version.getPharmCompany()) : version.getPharmCompany() != null)
            return false;
        if (getMedicineCertificate() != null ? !getMedicineCertificate().equals(version.getMedicineCertificate()) : version.getMedicineCertificate() != null)
            return false;
        if (getMedicinePackage() != version.getMedicinePackage()) return false;
        if (getMedicineDosage() != null ? !getMedicineDosage().equals(version.getMedicineDosage()) : version.getMedicineDosage() != null)
            return false;
        return getExpirationDate() != null ? getExpirationDate().equals(version.getExpirationDate()) : version.getExpirationDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getPharmCompany() != null ? getPharmCompany().hashCode() : 0;
        result = 31 * result + (getMedicineCertificate() != null ? getMedicineCertificate().hashCode() : 0);
        result = 31 * result + (getMedicinePackage() != null ? getMedicinePackage().hashCode() : 0);
        result = 31 * result + (getMedicineDosage() != null ? getMedicineDosage().hashCode() : 0);
        result = 31 * result + (getExpirationDate() != null ? getExpirationDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Version{" +
                "pharmCompany='" + pharmCompany + '\'' +
                ", medicineCertificate='" + medicineCertificate + '\'' +
                ", medicinePackage=" + medicinePackage +
                ", medicineDosage='" + medicineDosage + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}

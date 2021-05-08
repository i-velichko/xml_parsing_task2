package com.epam.secondtask.model;

import com.epam.secondtask.builder.type.MedicinePackageType;

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

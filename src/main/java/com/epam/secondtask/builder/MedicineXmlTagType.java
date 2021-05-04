package com.epam.secondtask.builder;

public enum MedicineXmlTagType {
    MEDICINES,
    MEDICINE,
    ID,
    PRESCRIPTION,
    NAME,
    GROUP,
    ANALOGS,
    ANALOG,
    VERSIONS,
    VERSION,
    PHARM,
    CERTIFICATE,
    PACKAGE,
    DOSAGE,
    EXPIRATION_DATE;

    @Override
    public String toString() {
        return name()
                .toLowerCase()
                .replace("_", "-");
    }
}

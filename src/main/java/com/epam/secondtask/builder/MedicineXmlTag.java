package com.epam.secondtask.builder;

public enum MedicineXmlTag {
    MEDICINES,
    MEDICINE,
    HOMEOPATHY,
    VACCINE,
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
    EXPIRATION_DATE,
    ACTIVE_SUBSTANCE,
    BACTERIA,
    BACTERIUM;

    @Override
    public String toString() {
        return name()
                .toLowerCase()
                .replace("_", "-");
    }
}

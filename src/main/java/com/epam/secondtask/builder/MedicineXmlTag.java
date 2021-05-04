package com.epam.secondtask.builder;

public enum MedicineXmlTag {
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
    EXPIRATION_DATE,
    HOMEOPATHY,
    VACCINE,
    ACTIVE_SUBSTANCE,
    BACTERIA,
    BACTERIUM;

//    private String value;
//    MedicineXmlTag(String value) {
//        this.value = value;
//    }
//    public String getValue() {
//        return value;
//    }

    @Override
    public String toString() {
        return name()
                .toLowerCase()
                .replace("_", "-");
    }
}

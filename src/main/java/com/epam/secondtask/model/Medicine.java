package com.epam.secondtask.model;

import java.util.Collections;
import java.util.List;

public class Medicine {
    private String medicineId;
    private boolean prescription;
    private String medicineName;
    private String medicineGroup; //энам
    private List<String> analogs;
    private List<Version> medicineVersions;
}

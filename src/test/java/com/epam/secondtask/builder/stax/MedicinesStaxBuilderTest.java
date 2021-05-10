package com.epam.secondtask.builder.stax;

import com.epam.secondtask.exception.MedicineXmlException;
import com.epam.secondtask.model.Medicine;
import com.epam.secondtask.model.Version;
import com.epam.secondtask.model.type.MedicineGroupType;
import com.epam.secondtask.model.type.MedicinePackageType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class MedicinesStaxBuilderTest {

    private final static String PATH_TO_XML_FILE = "src/test/recourses/medicines.xml";
    private final static String WRONG_PATH_TO_XML_FILE = "src/test/recoudewdewdqdrses/medicines.xml";
    int expectedListMedicinesSize = 17;
    private MedicinesStaxBuilder medicinesStaxBuilder;
    private Medicine expectedMedicineInstance;

    @BeforeMethod
    public void setUp() {
        medicinesStaxBuilder = new MedicinesStaxBuilder();
        List<String> analogs = new ArrayList<>();
        analogs.add("Magnelek");
        analogs.add("Magvit");
        analogs.add("Magnetab");
        List<Version> versions = new ArrayList<>();
        Version version1 = new Version("Sanofi", "№: 10476/16 от 27.04.2016 - Acting",
                MedicinePackageType.PILLS, "2 tablets, 3 times a day", YearMonth.parse("2023-04"));
        Version version2 = new Version("Sanofi", "№: 9120/09/14/17 от 02.12.2014 - Acting",
                MedicinePackageType.SOLUTE, "3-4 ampoules per day, divided into 2-3 doses with meals.", YearMonth.parse("2023-04"));
        versions.add(version1);
        versions.add(version2);
        expectedMedicineInstance = new Medicine("M1", "false", "Magne B6", MedicineGroupType.VITAMINS, analogs, versions);

    }

    @Test
    public void testIsNotEmptyListMedicines() throws MedicineXmlException {
        medicinesStaxBuilder.buildListMedicines(PATH_TO_XML_FILE);
        int actualListSize = medicinesStaxBuilder.getMedicines().size();
        Assert.assertEquals(actualListSize, expectedListMedicinesSize);
    }

    @Test
    public void testBuildListMedicines() throws MedicineXmlException {
        medicinesStaxBuilder.buildListMedicines(PATH_TO_XML_FILE);
        Medicine actualMedicineInstance = medicinesStaxBuilder.getMedicines().get(0);
        Assert.assertEquals(actualMedicineInstance, expectedMedicineInstance);
    }

    @Test(expectedExceptions = MedicineXmlException.class)
    public void testIsUncorrectedPathToFile() throws MedicineXmlException {
        medicinesStaxBuilder.buildListMedicines(WRONG_PATH_TO_XML_FILE);
    }
}
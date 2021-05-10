package com.epam.secondtask.builder.sax;

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

import static org.testng.Assert.*;

public class MedicinesSaxBuilderTest {
    private MedicinesSaxBuilder medicinesSaxBuilder;
    private Medicine medicine;
    private final static String PATH_TO_XML_FILE = "src/test/recourses/medicines.xml";
    int expected = 17;

    @BeforeMethod
    public void setUp() {
        medicinesSaxBuilder = new MedicinesSaxBuilder();
        List<String> analogs = new ArrayList<>();
        analogs.add("Magnelek");
        analogs.add("Magvit");
        analogs.add("Magnetab");
        List<Version> versions = new ArrayList<>();
        Version version1 = new Version("Sanofi","№: 10476/16 от 27.04.2016 - Acting",
                MedicinePackageType.PILLS, "2 tablets, 3 times a day", YearMonth.parse("2023-04"));
        Version version2 = new Version("Sanofi","№: 9120/09/14/17 от 02.12.2014 - Acting",
                MedicinePackageType.SOLUTE, "3-4 ampoules per day, divided into 2-3 doses with meals.", YearMonth.parse("2023-04"));
        versions.add(version1);
        versions.add(version2);
        medicine = new Medicine("M1","false", "Magne B6", MedicineGroupType.VITAMINS, analogs, versions );

    }

    @Test
    public void testGetMedicines() {
    }

    @Test
    public void testIsNotEmptyListMedicines() {
        medicinesSaxBuilder.buildListMedicines(PATH_TO_XML_FILE);
        int actual = medicinesSaxBuilder.getMedicines().size();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testBuildListMedicines() {
        medicinesSaxBuilder.buildListMedicines(PATH_TO_XML_FILE);
        Medicine actualMedicineInstance = medicinesSaxBuilder.getMedicines().get(0);
        Assert.assertEquals(actualMedicineInstance, medicine );
    }
}
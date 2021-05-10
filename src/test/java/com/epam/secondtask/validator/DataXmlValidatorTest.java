package com.epam.secondtask.validator;

import com.epam.secondtask.exception.MedicineXmlException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import static org.testng.Assert.*;

public class DataXmlValidatorTest {
    private DataXmlValidator dataXmlValidator;
    private final static String PATH_TO_XML_FILE = "src/test/recourses/medicines.xml";
    private final static String WRONG_PATH_TO_XML_FILE = "src/test/recourses/medicinessss.xml";
    private final static String PATH_TO_XML_FILE_WITH_UNCORRECTED_DATA = "src/test/recourses/uncorrectedDataMedicines.xml";
    private final static String PATH_TO_SCHEMA = "src/test/recourses/medicine.xsd";

    @BeforeMethod
    public void setUp() {
        dataXmlValidator = new DataXmlValidator();
    }

    @Test
    public void testValidateXmlFile() throws MedicineXmlException {
        boolean actualResult = dataXmlValidator.validateXmlFile(PATH_TO_XML_FILE, PATH_TO_SCHEMA);
        Assert.assertTrue(actualResult);
    }

    @Test(expectedExceptions = MedicineXmlException.class)
    public void testValidateXmlFileWithWrongPathToFile() throws MedicineXmlException {
        dataXmlValidator.validateXmlFile(WRONG_PATH_TO_XML_FILE, PATH_TO_SCHEMA);
    }

    @Test()
    public void testValidateXmlFileWithWrongXmlData() throws MedicineXmlException {
        boolean actualResult = dataXmlValidator.validateXmlFile(PATH_TO_XML_FILE_WITH_UNCORRECTED_DATA, PATH_TO_SCHEMA);
        Assert.assertFalse(actualResult);
    }

}
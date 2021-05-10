package com.epam.secondtask.builder;

import com.epam.secondtask.builder.dom.MedicinesDomBuilder;
import com.epam.secondtask.builder.sax.MedicinesSaxBuilder;
import com.epam.secondtask.builder.stax.MedicinesStaxBuilder;
import com.epam.secondtask.exception.MedicineXmlException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MedicineBuilderFactoryTest {
    private MedicineBuilderFactory builderFactory;

    @BeforeMethod
    public void setUp() {
        builderFactory = new MedicineBuilderFactory();
    }

    @Test
    public void testCreateDomBuilder() throws MedicineXmlException {
        AbstractMedicinesBuilder domBuilder = builderFactory.createMedicineBuilder("DOM");
        Assert.assertEquals(domBuilder.getClass(), MedicinesDomBuilder.class);
    }

    @Test
    public void testCreateSaxBuilder() throws MedicineXmlException {
        AbstractMedicinesBuilder saxBuilder = builderFactory.createMedicineBuilder("SAX");
        Assert.assertEquals(saxBuilder.getClass(), MedicinesSaxBuilder.class);
    }

    @Test
    public void testCreateStaxBuilder() throws MedicineXmlException {
        AbstractMedicinesBuilder staxBuilder = builderFactory.createMedicineBuilder("STAX");
        Assert.assertEquals(staxBuilder.getClass(), MedicinesStaxBuilder.class);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNonExistentBuilder() throws MedicineXmlException {
        builderFactory.createMedicineBuilder("JAXB");
    }
}
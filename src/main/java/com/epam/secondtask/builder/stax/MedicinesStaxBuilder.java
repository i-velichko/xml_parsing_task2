package com.epam.secondtask.builder.stax;

import com.epam.secondtask.builder.AbstractMedicinesBuilder;
import com.epam.secondtask.builder.type.MedicineXmlTag;
import com.epam.secondtask.exception.MedicineXmlException;
import com.epam.secondtask.model.Homeopathy;
import com.epam.secondtask.model.Medicine;
import com.epam.secondtask.model.Vaccine;
import com.epam.secondtask.model.Version;
import com.epam.secondtask.model.type.MedicineGroupType;
import com.epam.secondtask.model.type.MedicinePackageType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class MedicinesStaxBuilder extends AbstractMedicinesBuilder {
    private final static Logger LOGGER = LogManager.getLogger();
    private final List<Medicine> medicines;
    private final XMLInputFactory inputFactory;
    private final List<MedicineXmlTag> anyMedicineTagList = List.of(MedicineXmlTag.MEDICINE, MedicineXmlTag.VACCINE, MedicineXmlTag.HOMEOPATHY);

    public MedicinesStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        medicines = new ArrayList<Medicine>();
    }

    private static String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    @Override
    public void buildListMedicines(String filename) throws MedicineXmlException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(filename)) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    switch (MedicineXmlTag.valueOf(name.toUpperCase())) {
                        case MEDICINE -> medicines.add(buildAnyMedicine(reader, new Medicine()));
                        case VACCINE -> medicines.add(buildAnyMedicine(reader, new Vaccine()));
                        case HOMEOPATHY -> medicines.add(buildAnyMedicine(reader, new Homeopathy()));
                    }
                }
            }
            LOGGER.info("The list of medicine was created by STAX parser.");
        } catch (XMLStreamException | InstantiationException | IllegalAccessException | IOException e) {
            throw new MedicineXmlException("Can not parse file with STAX parser " + e.getMessage());
        }
    }

    private Medicine buildAnyMedicine(XMLStreamReader reader, Medicine medicine) throws XMLStreamException, InstantiationException, IllegalAccessException {
        medicine.setMedicineId(reader.getAttributeValue(null, MedicineXmlTag.ID.toString()));
        String prescription = reader.getAttributeValue(null, MedicineXmlTag.PRESCRIPTION.toString());
        medicine.setPrescription(prescription == null ? "true" : prescription);
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    switch (MedicineXmlTag.valueOf(name.toUpperCase())) {
                        case NAME -> medicine.setMedicineName(getXMLText(reader));
                        case ACTIVE_SUBSTANCE -> ((Homeopathy) medicine).setActiveSubstance(getXMLText(reader));
                        case BACTERIUM -> ((Vaccine) medicine).setBacteria(getXMLText(reader));
                        case GROUP -> medicine.setMedicineGroup(MedicineGroupType.valueOf(getXMLText(reader).toUpperCase()));
                        case ANALOGS -> medicine.setAnalogs(getListAnalogs(reader));
                        case VERSIONS -> medicine.setMedicineVersions(getListVersions(reader));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (anyMedicineTagList.contains(MedicineXmlTag.valueOf(name.toUpperCase()))) {
                        LOGGER.debug("The medicine instance was created and filled with content by STAX parser.");
                        return medicine;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <medicine>");
    }

    private List<String> getListAnalogs(XMLStreamReader reader) throws XMLStreamException {
        List<String> analogs = new ArrayList<>();
        String name;

        while (reader.hasNext()) {
            String analog;
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    if (name.equals(MedicineXmlTag.ANALOG.toString())) {
                        analog = getXMLText(reader);
                        analogs.add(analog);

                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (MedicineXmlTag.valueOf(name.toUpperCase()) == MedicineXmlTag.ANALOGS) {
                        return analogs;
                    }
                }
            }
        }
        return analogs;
    }

    private List<Version> getListVersions(XMLStreamReader reader) throws XMLStreamException {
        List<Version> versions = new ArrayList<>();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName();
                if (name.equals(MedicineXmlTag.VERSION.toString())) {

                    Version version = buildVersion(reader);
                    versions.add(version);
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName();
                if (MedicineXmlTag.valueOf(name.toUpperCase()) == MedicineXmlTag.VERSIONS) {
                    return versions;
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <versions>");
    }

    private Version buildVersion(XMLStreamReader reader) throws XMLStreamException {
        Version version = new Version();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    switch (MedicineXmlTag.valueOf(name.toUpperCase())) {
                        case PHARM -> version.setPharmCompany(getXMLText(reader));
                        case CERTIFICATE -> version.setMedicineCertificate(getXMLText(reader));
                        case PACKAGE -> version.setMedicinePackage(MedicinePackageType.valueOf(getXMLText(reader).toUpperCase()));
                        case DOSAGE -> version.setMedicineDosage(getXMLText(reader));
                        case EXPIRATION_DATE -> version.setExpirationDate(YearMonth.parse(getXMLText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (MedicineXmlTag.valueOf(name.toUpperCase()) == MedicineXmlTag.VERSION) {
                        return version;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <version>");
    }
}

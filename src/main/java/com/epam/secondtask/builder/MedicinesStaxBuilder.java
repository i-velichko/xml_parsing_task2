package com.epam.secondtask.builder;

import com.epam.secondtask.model.Homeopathy;
import com.epam.secondtask.model.Medicine;
import com.epam.secondtask.model.Vaccine;
import com.epam.secondtask.model.Version;
import com.epam.secondtask.model.enumeration.MedicineGroupType;
import com.epam.secondtask.model.enumeration.MedicinePackageType;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class MedicinesStaxBuilder {
    private List<Medicine> medicines;
    private XMLInputFactory inputFactory;
    private List<MedicineXmlTag> anyMedicineTagList = List.of(MedicineXmlTag.MEDICINE, MedicineXmlTag.VACCINE, MedicineXmlTag.HOMEOPATHY);

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

    public void buildListMedicines(String filename) {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(filename))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
// StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    switch (MedicineXmlTag.valueOf(name.toUpperCase())) {
                        case MEDICINE -> medicines.add(buildAnyMedicine(reader, Medicine.class));
                        case VACCINE -> medicines.add(buildAnyMedicine(reader, Vaccine.class));
                        case HOMEOPATHY -> medicines.add(buildAnyMedicine(reader, Homeopathy.class));
                    }
                }
            }
        } catch (XMLStreamException | InstantiationException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }

    private Medicine buildAnyMedicine(XMLStreamReader reader, Class<?> medicineClass) throws XMLStreamException, InstantiationException, IllegalAccessException {
        Medicine medicine = (Medicine) medicineClass.newInstance();
        medicine.setMedicineId(reader.getAttributeValue(null, MedicineXmlTag.ID.toString()));
        // null check
        medicine.setPrescription(reader.getAttributeValue(null,
                MedicineXmlTag.PRESCRIPTION.toString()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (MedicineXmlTag.valueOf(name.toUpperCase())) {
                        case NAME -> medicine.setMedicineName(getXMLText(reader));
                        case ACTIVE_SUBSTANCE -> ((Homeopathy) medicine).setActiveSubstance(getXMLText(reader));
                        case BACTERIUM -> ((Vaccine) medicine).setBacteria(getXMLText(reader));
                        case GROUP -> medicine.setMedicineGroup(MedicineGroupType.valueOf(getXMLText(reader).toUpperCase()));
                        case ANALOGS -> medicine.setAnalogs(getListAnalogs(reader));
                        case VERSIONS -> medicine.setMedicineVersions(getListVersions(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (anyMedicineTagList.contains(MedicineXmlTag.valueOf(name.toUpperCase()))) {
                        return medicine;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <medicine>");
    }

    private List<String> getListAnalogs(XMLStreamReader reader) throws XMLStreamException {
        List<String> analogs = new ArrayList<>();
        String name;

        while (reader.hasNext()) {
            String analog = null;
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
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (MedicineXmlTag.valueOf(name.toUpperCase())) {
                        case PHARM -> version.setPharmCompany(getXMLText(reader));
                        case CERTIFICATE -> version.setMedicineCertificate(getXMLText(reader));
                        case PACKAGE -> version.setMedicinePackage(MedicinePackageType.valueOf(getXMLText(reader).toUpperCase()));
                        case DOSAGE -> version.setMedicineDosage(getXMLText(reader));
                        case EXPIRATION_DATE -> version.setExpirationDate(YearMonth.parse(getXMLText(reader)));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (MedicineXmlTag.valueOf(name.toUpperCase()) == MedicineXmlTag.VERSION) {
                        return version;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <version>");
    }
}

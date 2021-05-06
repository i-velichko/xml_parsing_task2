package com.epam.secondtask.builder;

import com.epam.secondtask.model.Medicine;
import com.epam.secondtask.model.Version;
import com.epam.secondtask.model.enumeration.MedicineGroupType;
import com.epam.secondtask.model.enumeration.MedicinePackageType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.*;
import java.util.function.Consumer;

public class MedicineHandler extends DefaultHandler { //класс, который будет вызывать парсер
    private static final String ELEMENT_MEDICINE = "medicine";
    private static final String ELEMENT_ANALOG = "analog";
    private static final String ELEMENT_ANALOGS = "analogs";
    private static final String ELEMENT_VERSIONS = "versions";
    private static final String ELEMENT_VERSION = "version";
    private Set<Medicine> medicines;
    private Medicine currentMedicine;
    private Version currentVersion;
    private MedicineXmlTag currentXmlTag;
    private EnumSet<MedicineXmlTag> withText;
    private List<String> analogs;
    private List<Version> versions;
    private String analog;

    public MedicineHandler() {
        medicines = new HashSet<Medicine>();
        withText = EnumSet.range(MedicineXmlTag.NAME, MedicineXmlTag.EXPIRATION_DATE);

    }

    public Set<Medicine> getMedicines() {
        return medicines;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (ELEMENT_MEDICINE.equals(qName)) {
            currentMedicine = new Medicine();
            currentMedicine.setMedicineId(attrs.getValue(0));
            if (attrs.getLength() == 2) {
                currentMedicine.setPrescription(attrs.getValue(1));
            }
        } else {
            MedicineXmlTag temp = MedicineXmlTag.valueOf(qName.toUpperCase());
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
            if (ELEMENT_ANALOGS.equals(qName)) {
                analogs = new ArrayList<>();
            }

            if (ELEMENT_VERSIONS.equals(qName)) {
                versions = new ArrayList<>();
            }

            if (ELEMENT_VERSION.equals(qName)) {
                currentVersion = new Version();
            }
        }


    }

    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case ELEMENT_MEDICINE -> medicines.add(currentMedicine);
            case ELEMENT_ANALOGS -> currentMedicine.setAnalogs(analogs);
            case ELEMENT_ANALOG -> analogs.add(analog);
            case ELEMENT_VERSIONS -> currentMedicine.setMedicineVersions(versions);
            case ELEMENT_VERSION -> versions.add(currentVersion);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case NAME -> currentMedicine.setMedicineName(data);
                case GROUP -> currentMedicine.setMedicineGroup(MedicineGroupType.valueOf(data.toUpperCase()));
                case ANALOGS, VERSIONS -> System.out.println(" ");
                case ANALOG -> analog = data;
                case VERSION -> System.out.print(" ");
                case PHARM -> currentVersion.setPharmCompany(data);
                case CERTIFICATE -> currentVersion.setMedicineCertificate(data);
                case PACKAGE -> currentVersion.setMedicinePackage(MedicinePackageType.valueOf(data.toUpperCase()));
                case DOSAGE -> currentVersion.setMedicineDosage(data);
                case EXPIRATION_DATE -> currentVersion.setExpirationDate(YearMonth.parse(data));
                default -> throw new EnumConstantNotPresentException(
                        currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }
}


//    private Set<Medicine> medicineSet;
//    private Set<Version> versionSet;
//    private Set<String> analogs;
//    Medicine medicine = new Medicine();
//    private String currentTagName;
//    private boolean isMedicine = false;
//    private String name;
//    private String id;
//
//
//    public Medicine getMedicine() {
//        return medicine;
//    }
//
//    @Override
//    public void startDocument() throws SAXException {
//        System.out.println("Start document");
//    }
//
//    @Override
//    public void endDocument() throws SAXException {
//        System.out.println("End document");
//    }
//
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException { //стартЕлемент это тег, мы ъодим по тегам
////        System.out.println("Start element " + qName);
//        currentTagName = qName;
//        if (currentTagName.equals(MedicineXmlTagType.MEDICINE)) {
//            isMedicine = true;
//
//        }
//    }
//
//    @Override
//    public void endElement(String uri, String localName, String qName) throws SAXException {
////        System.out.println("End element " + qName);
//        if (currentTagName != null) {
//            if (currentTagName.equals(MedicineXmlTagType.MEDICINE)) {
//                isMedicine = false;
//                Medicine medicine = new Medicine(id,name);
//                medicineSet.add(medicine);
//            }
//        }
//        currentTagName = null;
//    }
//
//    @Override
//    public void characters(char[] ch, int start, int length) throws SAXException {
////        System.out.println("Characters " + new String(ch, start, length));
//        if (currentTagName == null) {
//            return;
//        }
//        if (isMedicine) {
//            if (currentTagName.equals(MedicineXmlTagType.NAME)) {
//                name = new String(ch, start, length);
//            } else if (currentTagName.equals(MedicineXmlTagType.GROUP)) {
//                id = new String(ch, start, length);
//            }
//        }
//
//    }
//}

package com.epam.secondtask.builder.dom;

import com.epam.secondtask.builder.AbstractMedicinesBuilder;
import com.epam.secondtask.model.Homeopathy;
import com.epam.secondtask.model.Medicine;
import com.epam.secondtask.model.Vaccine;
import com.epam.secondtask.model.Version;
import com.epam.secondtask.model.type.MedicineGroupType;
import com.epam.secondtask.model.type.MedicinePackageType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class MedicinesDomBuilder extends AbstractMedicinesBuilder {
    private final static String pathToFile = "src/main/resources/data/medicines.xml";

    public void buildListMedicines(String pathToFile) {
        List<Medicine> medicines = new ArrayList<>();
        Document doc;
        try {
            doc = buildDocument();
        } catch (Exception e) {
            System.out.println("Open parsing error " + e.toString());
            return;
        }

        Node rootNodeMedicines = doc.getFirstChild();  //получил корневой medicines
        NodeList medicinesChilds = rootNodeMedicines.getChildNodes(); //Получаю все обьекты Medicine, Vaccine и Homeopathy

        for (int i = 0; i < medicinesChilds.getLength(); i++) {
            Medicine medicine = null;
            Node item = medicinesChilds.item(i);
            if (item.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            switch (item.getNodeName()) {
                case "medicine" -> {
                    medicine = new Medicine();
                    buildMedicine(medicine, item);
                }
                case "homeopathy" -> {
                    medicine = new Homeopathy();
                    buildHomeopathy(medicine, item);
                }
                case "vaccine" -> {
                    medicine = new Vaccine();
                    buildVaccine(medicine, item);
                }
            }
            medicines.add(medicine);
        }
        medicines.forEach(System.out::println);
    }

    private static void buildVaccine(Medicine medicine, Node node) {
        buildMedicine(medicine, node);
        String bacteriaType = getTextContentByElement(node, "bacterium");
        ((Vaccine) medicine).setBacteria(bacteriaType);

    }

    private static void buildHomeopathy(Medicine medicine, Node node) {
        buildMedicine(medicine, node);
        String activeSubstance = getTextContentByElement(node, "active_substance");
        ((Homeopathy) medicine).setActiveSubstance(activeSubstance);
    }

    private static void buildMedicine(Medicine medicine, Node node) {
        if (node == null) {
            return;
        }
        NodeList medicineChilds = node.getChildNodes();
        for (int j = 0; j < medicineChilds.getLength(); j++) {
            Node nextMedicineChild = medicineChilds.item(j);
            if (nextMedicineChild.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            String medicineID = node.getAttributes().getNamedItem("id").getTextContent();
            medicine.setMedicineId(medicineID);
            String medicinePrescription = node.getAttributes().getNamedItem("prescription").getTextContent();
            medicine.setPrescription(medicinePrescription);
            String medicineGroup = getTextContentByElement(node, "group");
            medicine.setMedicineGroup(MedicineGroupType.valueOf(medicineGroup.toUpperCase()));
            String medicineName = getTextContentByElement(node, "name");
            medicine.setMedicineName(medicineName);
            switch (nextMedicineChild.getNodeName()) {
                case "analogs" -> buildMedicineAnalogsList(medicine, nextMedicineChild);
                case "versions" -> buildMedicineVersionsList(medicine, nextMedicineChild);
            }
        }
    }

    private static void buildMedicineVersionsList(Medicine medicine, Node nodeVersions) {
        List<Version> versions = new ArrayList<>();
        NodeList childNodes = nodeVersions.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Version version = readVersion(item);
                versions.add(version);
            }
        }
        medicine.setMedicineVersions(versions);
    }

    private static void buildMedicineAnalogsList(Medicine medicine, Node nodeAnalogs) {
        List<String> analogs = new ArrayList<>();
        NodeList childNodes = nodeAnalogs.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                String analogContent = item.getTextContent();
                analogs.add(analogContent);
            }

        }
        medicine.setAnalogs(analogs);
    }

    private static Version readVersion(Node nodeVersion) {
        Version version = new Version();
        String packageTextContent = getTextContentByElement(nodeVersion, "package");
        version.setMedicinePackage(MedicinePackageType.valueOf(packageTextContent.toUpperCase()));
        String pharmCompany = getTextContentByElement(nodeVersion, "pharm");
        version.setPharmCompany(pharmCompany);
        String dosageMedicine = getTextContentByElement(nodeVersion, "dosage");
        version.setMedicineDosage(dosageMedicine);
        String certificateMedicine = getTextContentByElement(nodeVersion, "certificate");
        version.setMedicineCertificate(certificateMedicine);
        String expirationDate = getTextContentByElement(nodeVersion, "expiration_date");
        version.setExpirationDate(YearMonth.parse(expirationDate));
        return version;
    }

    private static String getTextContentByElement(Node node, String childElementName) {
        Element versionElement = (Element) node;
        NodeList elementsByTagName = versionElement.getElementsByTagName(childElementName);
        Node item = elementsByTagName.item(0);
        return item.getTextContent();
    }

    private static Document buildDocument() throws Exception {
        File file = new File(pathToFile);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        return builderFactory.newDocumentBuilder().parse(file);

    }
}

// ЭТО ВСЕ ШАПКА НА БУДУЩЕЕ, КОГДА БУДЕТ ОБЩИЙ ШАБЛОН БИЛДЕРА
//    private DocumentBuilder docBuilder;
//    public MedicinesDomBuilder() {
//// more code
//    }
//    public MedicinesDomBuilder(Set<Medicine> students) {
//        super(students);
//// more code
//    }
//    @Override
//    public void buildSetMedicines(String fileName) {
//// more code
//    }
//// private methods

package com.epam.secondtask;

import com.epam.secondtask.model.Medicine;
import com.epam.secondtask.model.Version;
import com.epam.secondtask.model.enumeration.MedicineGroupType;
import com.epam.secondtask.model.enumeration.MedicinePackageType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static String pathToFile = "src/main/resources/data/medicines.xml";

    public static void main(String[] args) {

        List<Medicine> medicines = new ArrayList<>();
        Document doc;
        try {
            doc = buildDocument();
        } catch (Exception e) {
            System.out.println("Open parsing error " + e.toString());
            return;
        }

        Node rootNodeMedicines = doc.getFirstChild();  //получил корневой medicines
        System.out.println("Самый корневой элемент - " + rootNodeMedicines.getNodeName());

        NodeList medicinesChilds = rootNodeMedicines.getChildNodes(); //Получаю все обьекты Medicine, Vaccine и Homeopathy
        Node medicineNode = null;
        Node homeopathyNode = null;
        Node vaccineNode = null;
        int count = 0;
        for (int i = 0; i < medicinesChilds.getLength(); i++) {
            Medicine medicine = new Medicine();
            if (medicinesChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {  //фильтрую барахло типа #text
                count += 1;
                continue;
            }
            System.out.println("Ребенок Медицинс -  " + count + " " + medicinesChilds.item(i).getNodeName());

            switch (medicinesChilds.item(i).getNodeName()) {
                case "medicine" -> {
                    medicineNode = medicinesChilds.item(i);
                    buildMedicine(medicine, medicineNode);
                }
                case "homeopathy" -> {
                    homeopathyNode = medicinesChilds.item(i);
                    buildHomeopathy();
                }
                case "vaccine" -> {
                    vaccineNode = medicinesChilds.item(i);
                    buildVaccine();
                }
            }
            medicines.add(medicine);
            }
        medicines.forEach(System.out::println);
    }


    private static void buildVaccine() {
    }

    private static void buildHomeopathy() {
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
//            String medicinePrescription = getTextContentByElement(node, "prescription"); //todo
//            medicine.setPrescription(medicinePrescription);
            String medicineGroup = getTextContentByElement(node, "group");
            medicine.setMedicineGroup(MedicineGroupType.valueOf(medicineGroup.toUpperCase()));
            String medicineName  = getTextContentByElement(node, "name");
            medicine.setMedicineName(medicineName);
            System.out.println("Ребенок Медицины - " + nextMedicineChild.getNodeName());
            switch (nextMedicineChild.getNodeName()) {
                case "analogs" ->
                        buildMedicineAnalogsList(medicine, nextMedicineChild);
                case "versions"->
                    buildMedicineVersionsList(medicine, nextMedicineChild);
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
        String expirationDate = getTextContentByElement(nodeVersion,"expiration_date");
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

//                if (analogNode != null) {
//                    NodeList analogs = analogNode.getChildNodes();
//                    for (int j = 0; j < analogs.getLength(); j++) {
//                        if (analogs.item(j).getNodeType() != Node.ELEMENT_NODE) {
//                            continue;
//                        }
//                        System.out.println("Ребенок Аналогов - " + analogs.item(j).getNodeName());
//                    }
//                }
//
//                if (versionNode != null) {
//                    NodeList versions = versionNode.getChildNodes();
//                    for (int j = 0; j < versions.getLength(); j++) {
//                        if (versions.item(j).getNodeType() != Node.ELEMENT_NODE) {
//                            continue;
//                        }
//                        System.out.println("Ребенок Версий - " + versions.item(j).getNodeName());
//                    }
//                }
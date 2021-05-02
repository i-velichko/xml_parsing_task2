package com.epam.secondtask.builder;

import com.epam.secondtask.model.Medicine;
import com.epam.secondtask.model.Version;
import com.epam.secondtask.model.enumeration.MedicineGroupType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MedicinesDomBuilder {
    private Set<Medicine> medicines;
    private DocumentBuilder docBuilder;

    public MedicinesDomBuilder() {
        medicines = new HashSet<Medicine>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace(); // log
        }
    }

    public Set<Medicine> getMedicines() {
        return medicines;
    }

    public void buildSetMedicines(String filename) {
        Document doc;
        try {
            doc = docBuilder.parse(filename);
            Element root = doc.getDocumentElement();
// getting a list of <medicine> child elements
            NodeList medicinesList = root.getElementsByTagName("medicine");
            for (int i = 0; i < medicinesList.getLength(); i++) {
                Element medicineElement = (Element) medicinesList.item(i);
                Medicine medicine = buildMedicine(medicineElement);
                medicines.add(medicine);
            }
        } catch (IOException | SAXException e) {
            e.printStackTrace(); // log
        }
    }

    private Medicine buildMedicine(Element medicineElement) {
        Medicine medicine = new Medicine();
        ArrayList<String> analogs = new ArrayList<>();
// add null check
        medicine.setMedicineId((medicineElement.getAttribute("id")));
//        medicine.setPrescription(Boolean.parseBoolean(medicineElement.getAttribute("prescription")));
        medicine.setMedicineName(getElementTextContent(medicineElement, "name"));
        medicine.setMedicineGroup(MedicineGroupType.valueOf(getElementTextContent(medicineElement,"group").toUpperCase())); //todo

        NodeList analogs1 = medicineElement.getChildNodes();
        Node item = analogs1.item(0);
        for (int i = 0; i < analogs1.getLength(); i++) {
            Node analog = analogs1.item(i);
            analog.getTextContent();

        }
        for (int i = 0; i < medicineElement.getElementsByTagName("versions").getLength(); i++) {
            Version version = new Version();
            Element versionElement = (Element) medicineElement.getElementsByTagName("version").item(i);
            version.setPharmCompany(getElementTextContent(versionElement, "pharm"));
            version.setMedicineCertificate(getElementTextContent(versionElement, "pharm"));
            version.setMedicineCertificate(getElementTextContent(versionElement, "certificate"));
            medicine.getMedicineVersions().add(version);
        }
        return medicine;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
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

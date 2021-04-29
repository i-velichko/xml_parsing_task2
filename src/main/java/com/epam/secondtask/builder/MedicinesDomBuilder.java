package com.epam.secondtask.builder;

import com.epam.secondtask.model.Medicine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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
// getting a list of <student> child elements
            NodeList medicinesList = root.getElementsByTagName("student");
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
// add null check
        medicine.setPrescription(Boolean.parseBoolean(medicineElement.getAttribute("prescription")));
        medicine.setMedicineName(getElementTextContent(medicineElement, "name"));
        medicine.setMedicineGroup(getElementTextContent(medicineElement, "name"));
        medicine.setAnalogs(getElementTextContent(medicineElement, "name"));
        medicine.setMedicineVersions(getElementTextContent(medicineElement, "name"));
        Integer tel = Integer.parseInt(getElementTextContent(medicineElement, "telephone"));
        medicine.setTelephone(tel);
        Student.Address address = medicine.getAddress();
// init an address object
        Element adressElement =
                (Element) medicineElement.getElementsByTagName("address").item(0);
        address.setCountry(getElementTextContent(adressElement, "country"));
        address.setCity(getElementTextContent(adressElement, "city"));
        address.setStreet(getElementTextContent(adressElement, "street"));
        medicine.setMedicineId(medicineElement.getAttribute("id"));
        return medicine;
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

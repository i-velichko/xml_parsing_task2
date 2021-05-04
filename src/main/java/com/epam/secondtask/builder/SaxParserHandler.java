package com.epam.secondtask.builder;

import com.epam.secondtask.model.Medicine;
import com.epam.secondtask.model.Version;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Set;

public class SaxParserHandler extends DefaultHandler { //класс, который будет вызывать парсер
    private Set<Medicine> medicineSet;
    private Set<Version> versionSet;
    private Set<String> analogs;
    Medicine medicine = new Medicine();
    private String currentTagName;
    private boolean isMedicine = false;
    private String name;
    private String id;


    public Medicine getMedicine() {
        return medicine;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start document");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException { //стартЕлемент это тег, мы ъодим по тегам
//        System.out.println("Start element " + qName);
        currentTagName = qName;
        if (currentTagName.equals(MedicineXmlTagType.MEDICINE)) {
            isMedicine = true;

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        System.out.println("End element " + qName);
        if (currentTagName != null) {
            if (currentTagName.equals(MedicineXmlTagType.MEDICINE)) {
                isMedicine = false;
                Medicine medicine = new Medicine(id,name);
                medicineSet.add(medicine);
            }
        }
        currentTagName = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
//        System.out.println("Characters " + new String(ch, start, length));
        if (currentTagName == null) {
            return;
        }
        if (isMedicine) {
            if (currentTagName.equals(MedicineXmlTagType.NAME)) {
                name = new String(ch, start, length);
            } else if (currentTagName.equals(MedicineXmlTagType.GROUP)) {
                id = new String(ch, start, length);
            }
        }

    }
}

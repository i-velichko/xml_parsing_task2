package com.epam.secondtask.builder;

import com.epam.secondtask.model.Medicine;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class MedicinesSaxBuilder {
    private final static String pathToFile = "src/main/resources/data/medicines.xml";
    private Set<Medicine> medicines;
    private MedicineHandler handler = new MedicineHandler();
    private XMLReader reader;

    public MedicinesSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace(); // log
        }
        reader.setErrorHandler(new MedicineErrorHandler());
        reader.setContentHandler(handler);
    }

    public Set<Medicine> getMedicines() {
        return medicines;
    }

    public void buildSetMedicines(String pathToFile) {
        try {
            reader.parse(pathToFile);
        } catch (IOException | SAXException e) {
            e.printStackTrace(); // log
        }
        medicines = handler.getMedicines();
    }
}

//    public Medicine parse(){
//        SAXParserFactory factory = SAXParserFactory.newInstance(); //создаем Парсер
//        MedicineHandler handler = new MedicineHandler();  //Создаем обработчик
//        SAXParser saxParser = null;
//        try {
//            saxParser = factory.newSAXParser();  //получаем ПАрсер
//        } catch (Exception e) {
//            System.out.println("open sax parser error " + e.toString());
//            return null; //todo выкинуть свой exception вместо null
//        }
//
//        File file = new File(pathToFile);  //открываем файл
//        try {
//            saxParser.parse(file, handler);   //вот такойто файл парсим и результат присваивай в хендлер, дальше вся работа будет в классе Хендлера (вся бизнеслогика)
//        } catch (SAXException e) {
//            System.out.println("Sax parsing error " + e.toString());
//        } catch (IOException e) {
//            System.out.println("IO parsing error " + e.toString());
//            return null;  //todo выкинуть свой exception вместо null
//        }
//        return handler.getMedicine();
//    }
//
//}

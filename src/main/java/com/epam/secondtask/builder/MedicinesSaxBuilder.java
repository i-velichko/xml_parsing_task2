package com.epam.secondtask.builder;

import com.epam.secondtask.model.Medicine;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class MedicinesSaxBuilder {
    private final static String pathToFile = "src/main/resources/data/medicines.xml";

    public Medicine parse(){
        SAXParserFactory factory = SAXParserFactory.newInstance(); //создаем Парсер
        SaxParserHandler handler = new SaxParserHandler();  //Создаем обработчик
        SAXParser saxParser = null;
        try {
            saxParser = factory.newSAXParser();  //получаем ПАрсер
        } catch (Exception e) {
            System.out.println("open sax parser error " + e.toString());
            return null; //todo выкинуть свой exception вместо null
        }

        File file = new File(pathToFile);  //открываем файл
        try {
            saxParser.parse(file, handler);   //вот такойто файл парсим и результат присваивай в хендлер, дальше вся работа будет в классе Хендлера (вся бизнеслогика)
        } catch (SAXException e) {
            System.out.println("Sax parsing error " + e.toString());
        } catch (IOException e) {
            System.out.println("IO parsing error " + e.toString());
            return null;  //todo выкинуть свой exception вместо null
        }
        return handler.getMedicine();
    }

}

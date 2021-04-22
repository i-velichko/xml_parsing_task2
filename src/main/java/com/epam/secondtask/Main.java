package com.epam.secondtask;

import com.epam.secondtask.model.Root;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main {
    public static void main(String[] args) {

        Root root = null;
        File file = new File("src/main/resources/data/test.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            doc = dbf.newDocumentBuilder().parse(file);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Open parsing error" + e.toString());
            return;
        }  //ксок кода от обьявления файла, открывает файл для парсинга

        //далее три важных обьекта в DOM - Node/ NodeList / Element
        //Node это абсолютно любой элемент файла XML - строка, пробел, элемент и тд. Любой пишк это обьект нод
        //Nodelist - например просим лист всех дочерних элемнтов рута и тд
        //Element - этоуже конкретный обьект, тег

//        Node rootNode = doc.getFirstChild();                                    1 способ достать элементы
//        System.out.println("Корневой нод (элемент) " + rootNode.getNodeName());
//        NodeList childNodes = rootNode.getChildNodes();

        NodeList name = doc.getElementsByTagName("name");
        System.out.println(name.toString());


    }
}

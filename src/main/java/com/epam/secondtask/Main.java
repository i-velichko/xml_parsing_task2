package com.epam.secondtask;

public class Main {
    public static void main(String[] args) {
//        String pathToFile = "src/main/resources/data/medicines.xml";

    }
}









//    Medicine medicine = null;
//    File file = new File("src/main/resources/data/medicines.xml");
//
//    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
//    Document document = null;
//        try {
//                document = builderFactory.newDocumentBuilder().parse(file);
//                } catch (Exception e) {
//                System.out.println("Open parsing error " + e.toString());
//                return;
//                }
//
//                Node rootNode = document.getFirstChild();  //получил корневой medcines
//                System.out.println(rootNode.getNodeName());
//
//                NodeList childNodes = rootNode.getChildNodes();


//далее три важных обьекта в DOM - Node/ NodeList / Element
//Node это абсолютно любой элемент файла XML - строка, пробел, элемент и тд. Любой пишк это обьект нод
//Nodelist - например просим лист всех дочерних элемнтов рута и тд
//Element - этоуже конкретный обьект, тег

//        Node rootNode = doc.getFirstChild();                                    1 способ достать элементы
//        System.out.println("Корневой нод (элемент) " + rootNode.getNodeName());
//        NodeList childNodes = rootNode.getChildNodes();
//        Root root = new Root();
//        Document doc;
//        try {
//            doc = buildDocument();
//        } catch (Exception e) {
//            System.out.println("Open parsing error" + e.toString());
//            return;
//        }
//
//        Node rootNode = doc.getFirstChild();
//
//        NodeList rootChilds = rootNode.getChildNodes();
//
//        String mainNAme = null;
//        Node peopleNode = null;
//        for (int i = 0; i < rootChilds.getLength(); i++) {
//            if (rootChilds.item(i).getNodeType() != Node.ELEMENT_NODE) { //проверка только на элементы, а не на все что видит нод (пустые строки, отсутпы и тд)
//                continue;
//            }
//            System.out.println("Print " + rootChilds.item(i).getNodeName());
//
//            switch (rootChilds.item(i).getNodeName()) {
//                case "name": {
//                    mainNAme = rootChilds.item(i).getTextContent();
//                    System.out.println("Main name " + mainNAme); // печатаем содержимое main name
//
//                }
//                case "people": {
//                    peopleNode = rootChilds.item(i);
//                }
//            }
//        }
//        root.setName(mainNAme);
//
//        if (peopleNode == null) {
//            return;
//        }
//        List<People> peopleList = new ArrayList<>();
//        NodeList peopleChilds = peopleNode.getChildNodes();
//        for (int i = 0; i < peopleChilds.getLength(); i++) {
//            if (peopleChilds.item(i).getNodeType() != Node.ELEMENT_NODE) { //проверка только на элементы, а не на все что видит нод (пустые строки, отсутпы и тд)
//                continue;
//            }
//            if (!peopleChilds.item(i).getNodeName().equals("element")) {
//                continue;
//            }
//
//        }
//
//    }
//
//    private static Document buildDocument() throws Exception {
//        File file = new File("src/main/resources/data/test.xml");
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        return dbf.newDocumentBuilder().parse(file);
//    }  //ксок кода от обьявления файла, открывает файл для парсинга
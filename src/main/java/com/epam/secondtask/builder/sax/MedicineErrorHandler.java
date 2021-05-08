package com.epam.secondtask.builder.sax;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MedicineErrorHandler implements ErrorHandler {
    @Override
    public void warning(SAXParseException exception) throws SAXException {  //todo logs

    }

    @Override
    public void error(SAXParseException exception) throws SAXException {  //todo logs

    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {   //todo logs

    }
}

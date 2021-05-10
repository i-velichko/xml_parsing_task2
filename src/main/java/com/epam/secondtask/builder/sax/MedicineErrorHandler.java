package com.epam.secondtask.builder.sax;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MedicineErrorHandler implements ErrorHandler {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void warning(SAXParseException e) throws SAXException {
        LOGGER.warn(e.getMessage());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        LOGGER.error(e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        LOGGER.fatal(e.getMessage());
    }

}

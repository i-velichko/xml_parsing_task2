package com.epam.secondtask.builder;

import com.epam.secondtask.builder.dom.MedicinesDomBuilder;
import com.epam.secondtask.builder.sax.MedicinesSaxBuilder;
import com.epam.secondtask.builder.stax.MedicinesStaxBuilder;
import com.epam.secondtask.exception.MedicineXmlException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MedicineBuilderFactory {
    private final static Logger LOGGER = LogManager.getLogger();

    public AbstractMedicinesBuilder createMedicineBuilder(String typeParser) throws MedicineXmlException {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());

        switch (type) {
            case DOM -> {
                LOGGER.info("DOM builder was created");
                return new MedicinesDomBuilder();
            }
            case STAX -> {
                LOGGER.info("STAX builder was created");
                return new MedicinesStaxBuilder();
            }
            case SAX -> {
                LOGGER.info("SAX builder was created");
                return new MedicinesSaxBuilder();
            }
            default -> throw new EnumConstantNotPresentException(
                    type.getDeclaringClass(), type.name());
        }
    }

    private enum TypeParser {
        SAX, STAX, DOM
    }

}

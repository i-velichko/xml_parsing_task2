package com.epam.secondtask.builder;

import com.epam.secondtask.builder.dom.MedicinesDomBuilder;
import com.epam.secondtask.builder.sax.MedicinesSaxBuilder;
import com.epam.secondtask.builder.stax.MedicinesStaxBuilder;
import com.epam.secondtask.exception.MedicineXmlException;

public class MedicineBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    public AbstractMedicinesBuilder createMedicineBuilder(String typeParser) throws MedicineXmlException {
        TypeParser type;
        try {
            type = TypeParser.valueOf(typeParser.toUpperCase());//todo logs
        }catch (IllegalArgumentException e){
            throw new MedicineXmlException();
        }

        switch (type) {
            case DOM -> {
                return new MedicinesDomBuilder();
            }
            case STAX -> {
                return new MedicinesStaxBuilder();
            }
            case SAX -> {
                return new MedicinesSaxBuilder();
            }
            default -> throw new EnumConstantNotPresentException(
                    type.getDeclaringClass(), type.name());
        }
    }

}

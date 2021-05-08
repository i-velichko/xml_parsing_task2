package com.epam.secondtask.builder.common;

import com.epam.secondtask.builder.dom.MedicinesDomBuilder;
import com.epam.secondtask.builder.sax.MedicinesSaxBuilder;
import com.epam.secondtask.builder.stax.MedicinesStaxBuilder;

public class MedicineBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    private MedicineBuilderFactory() {
    }

    public static AbstractMedicinesBuilder createMedicineBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
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

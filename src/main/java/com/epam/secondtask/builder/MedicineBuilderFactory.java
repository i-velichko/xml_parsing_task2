package com.epam.secondtask.builder;

public class MedicineBuilderFactory {
    private MedicineBuilderFactory() {
    }

    public static AbstractMedicinesBuilder createMedicineBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
//            case DOM -> {
//                return new MedicinesDomBuilder();
//            }
            case STAX -> {
                return new MedicinesStaxBuilder();
            }
//            case SAX -> {
//                return new MedicinesSaxBuilder();
//            }
            default -> throw new EnumConstantNotPresentException(
                    type.getDeclaringClass(), type.name());
        }
    }

    private enum TypeParser {
        DOM, SAX, STAX
    }
}

package com.epam.secondtask.exception;

public class MedicineXmlException extends Exception {

    public MedicineXmlException() {
    }

    public MedicineXmlException(String message) {
        super(message);
    }

    public MedicineXmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public MedicineXmlException(Throwable cause) {
        super(cause);
    }
}

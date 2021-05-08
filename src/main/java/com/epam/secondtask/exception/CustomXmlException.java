package com.epam.secondtask.exception;

public class CustomXmlException extends Exception{

    public CustomXmlException() {
    }

    public CustomXmlException(String message) {
        super(message);
    }

    public CustomXmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomXmlException(Throwable cause) {
        super(cause);
    }
}

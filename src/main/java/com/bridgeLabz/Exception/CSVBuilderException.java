package com.bridgeLabz.Exception;

public class CSVBuilderException extends Exception {
    public ExceptionType exceptionType;

    public CSVBuilderException(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public enum ExceptionType {
        FILE_NOT_FOUND,
        DELIMITER_OR_HEADER_INCORRECT,
        UNABLE_TO_PARSE,
        INVALID_COUNTRY
    }
}

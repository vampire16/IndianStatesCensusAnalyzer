package com.bridgeLabz.Exception;

public class CensusAnalyzerException extends Exception {
    public enum ExceptionType{
        FILE_NOT_FOUND,
        DELIMITER_INCORRECT
    }

    public ExceptionType exceptionType;

    public CensusAnalyzerException(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }
}

package com.bridgeLabz.Exception;

public class CensusAnalyzerException extends Throwable {
    public enum ExceptionType{
        FILE_NOT_FOUND
    }

    public ExceptionType exceptionType;

    public CensusAnalyzerException(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
}

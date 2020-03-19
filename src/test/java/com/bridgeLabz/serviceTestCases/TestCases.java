package com.bridgeLabz.serviceTestCases;

import com.bridgeLabz.Exception.CSVBuilderException;
import com.bridgeLabz.model.CSVStateCensus;
import com.bridgeLabz.model.StateCodePojo;
import com.bridgeLabz.service.StateCensusAnalyzer;
import org.junit.Assert;
import org.junit.Test;

public class TestCases {

    @Test
    public void givenNumberOfRecords_WhenMatched_ShouldReturnTrue() throws CSVBuilderException {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(CSV_FILE_PATH, CSVStateCensus.class);
        int numberOfRecords = stateCensusAnalyzer.loadRecords();
        Assert.assertEquals(29, numberOfRecords);
    }

    @Test
    public void givenFileName_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/stateCensusData.csv";
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(CSV_FILE_PATH, CSVStateCensus.class);
        try {
            stateCensusAnalyzer.loadRecords();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenFileType_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.txt";
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(CSV_FILE_PATH, CSVStateCensus.class);
        try {
            stateCensusAnalyzer.loadRecords();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenFile_WhenDelimiterIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData1.csv";
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(CSV_FILE_PATH, CSVStateCensus.class);
        try {
            stateCensusAnalyzer.loadRecords();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenFile_WhenHeaderIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData2.csv";
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(CSV_FILE_PATH, CSVStateCensus.class);
        try {
            stateCensusAnalyzer.loadRecords();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenNumberOfRecordsOfStateCode_WhenMatched_ShouldReturnTrue() throws CSVBuilderException {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(CSV_FILE_PATH, StateCodePojo.class);
        int numberOfRecords = stateCensusAnalyzer.loadRecords();
        Assert.assertEquals(37, numberOfRecords);
    }

    @Test
    public void givenFileNameOfStateCode_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/stateCode.csv";
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(CSV_FILE_PATH, StateCodePojo.class);
        try {
            stateCensusAnalyzer.loadRecords();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenFileTypeOfStateCode_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.txt";
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(CSV_FILE_PATH, StateCodePojo.class);
        try {
            stateCensusAnalyzer.loadRecords();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenFileOfStateCode_WhenDelimiterIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode1.csv";
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(CSV_FILE_PATH, StateCodePojo.class);
        try {
            stateCensusAnalyzer.loadRecords();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenFileOfStateCode_WhenHeadersIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode2.csv";
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(CSV_FILE_PATH, StateCodePojo.class);
        try {
            stateCensusAnalyzer.loadRecords();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.exceptionType);
        }
    }
}

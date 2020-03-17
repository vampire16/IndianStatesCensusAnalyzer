package com.bridgeLabz.serviceTestCases;

import com.bridgeLabz.Exception.CensusAnalyzerException;
import com.bridgeLabz.service.StateCensusAnalyzer;
import org.junit.Assert;
import org.junit.Test;

public class TestCases {

    @Test
    public void givenNumberOfRecords_WhenMatched_ReturnTrue() throws CensusAnalyzerException {
        final String CSV_FILE_PATH = "/home/admin2/IndianStatesCensusAnalyzer/src/test/resources/StateCensusData.csv";
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(CSV_FILE_PATH);
        int numberOfRecords = stateCensusAnalyzer.loadRecords();
        Assert.assertEquals(29,numberOfRecords);
    }

    @Test
    public void givenFileName_WhenWrong_ReturnCustomiseException(){
        final String CSV_FILE_PATH = "/home/admin2/IndianStatesCensusAnalyzer/src/test/resources/stateCensusData.csv";
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(CSV_FILE_PATH);
        try {
            stateCensusAnalyzer.loadRecords();
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.FILE_NOT_FOUND,e.exceptionType);
        }
    }

    @Test
    public void givenFileType_WhenWrong_ReturnCustomiseException(){
        final String CSV_FILE_PATH = "/home/admin2/IndianStatesCensusAnalyzer/src/test/resources/StateCensusData.txt";
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(CSV_FILE_PATH);
        try {
            stateCensusAnalyzer.loadRecords();
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.FILE_NOT_FOUND,e.exceptionType);
        }
    }

    @Test
    public void givenFile_WhenDelimiterIncorrect_ReturnCustomiseException(){
        final String CSV_FILE_PATH = "/home/admin2/IndianStatesCensusAnalyzer/src/test/resources/StateCensusData1.csv";
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(CSV_FILE_PATH);
        try {
            stateCensusAnalyzer.loadRecords();
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.DELIMITER_INCORRECT,e.exceptionType);
        }
    }

    @Test
    public void givenFile_WhenHeaderIncorrect_ReturnCustomiseException(){
        final String CSV_FILE_PATH = "/home/admin2/IndianStatesCensusAnalyzer/src/test/resources/StateCensusData2.csv";
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(CSV_FILE_PATH);
        try {
            stateCensusAnalyzer.loadRecords();
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.DELIMITER_INCORRECT,e.exceptionType);
        }
    }

}

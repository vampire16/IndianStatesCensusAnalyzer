package com.bridgeLabz.serviceTestCases;

import com.bridgeLabz.Exception.CSVBuilderException;
import com.bridgeLabz.model.StateCensusPojo;
import com.bridgeLabz.model.StateCodePojo;
import com.bridgeLabz.service.StateCensusAnalyzer;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class TestCases {

    StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();

    @Test
    public void givenNumberOfRecords_WhenMatched_ShouldReturnTrue() throws CSVBuilderException {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        int numberOfRecords = stateCensusAnalyzer.loadCensusRecords(CSV_FILE_PATH);
        Assert.assertEquals(29, numberOfRecords);
    }

    @Test
    public void givenFileName_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/CensusData.csv";
        try {
            stateCensusAnalyzer.loadCensusRecords(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenFileType_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.txt";
        try {
            stateCensusAnalyzer.loadCensusRecords(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenFile_WhenDelimiterIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData1.csv";
        try {
            stateCensusAnalyzer.loadCensusRecords(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenFile_WhenHeaderIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData2.csv";
        try {
            stateCensusAnalyzer.loadCensusRecords(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenNumberOfRecordsOfStateCode_WhenMatched_ShouldReturnTrue() throws CSVBuilderException {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        int numberOfRecords = stateCensusAnalyzer.loadStateCodeRecords(CSV_FILE_PATH);
        Assert.assertEquals(37, numberOfRecords);
    }

    @Test
    public void givenFileNameOfStateCode_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/tateCode.csv";
        try {
            stateCensusAnalyzer.loadStateCodeRecords(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenFileTypeOfStateCode_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.txt";
        try {
            stateCensusAnalyzer.loadStateCodeRecords(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenFileOfStateCode_WhenDelimiterIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode1.csv";
        try {
            stateCensusAnalyzer.loadStateCodeRecords(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenFileOfStateCode_WhenHeadersIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode2.csv";
        try {
            stateCensusAnalyzer.loadStateCodeRecords(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenCensusData_WhenSorted_ShouldReturnSortedList() throws CSVBuilderException {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        stateCensusAnalyzer.loadCensusRecords(CSV_FILE_PATH);
        String SortedData = stateCensusAnalyzer.getSortedCensusData();
        StateCensusPojo[] censusCSV = new Gson().fromJson(SortedData, StateCensusPojo[].class);
        Assert.assertEquals("Andhra Pradesh", censusCSV[0].getState());
    }

    @Test
    public void givenStateCodeData_WhenSorted_ShouldReturnSortedList() throws CSVBuilderException {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        stateCensusAnalyzer.loadStateCodeRecords(CSV_FILE_PATH);
        String SortedData = stateCensusAnalyzer.getSortedStateCodeData();
        StateCodePojo[] StateCodes = new Gson().fromJson(SortedData, StateCodePojo[].class);
        Assert.assertEquals("AD", StateCodes[0].getStateCode());
    }

    @Test
    public void givenStateCensusData_WhenPopulationSorted_ShouldReturnSortedResult() throws CSVBuilderException {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        stateCensusAnalyzer.loadCensusRecords(CSV_FILE_PATH);
        String sortedData = stateCensusAnalyzer.getSortedCensusDataPopulationWise();
        StateCensusPojo[] stateCensusPojo = new Gson().fromJson(sortedData, StateCensusPojo[].class);
        Assert.assertEquals(199812341, stateCensusPojo[0].population);
    }

    @Test
    public void givenStateCensusData_WhenDensitySorted_ShouldReturnSortedResult() throws CSVBuilderException {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        stateCensusAnalyzer.loadCensusRecords(CSV_FILE_PATH);
        String sortedData = stateCensusAnalyzer.getSortedCensusDataDensityWise();
        StateCensusPojo[] stateCensusPojo = new Gson().fromJson(sortedData, StateCensusPojo[].class);
        Assert.assertEquals(1102, stateCensusPojo[0].density);
    }

    @Test
    public void givenStateCensusData_WhenAreaSorted_ShouldReturnSortedResult() throws CSVBuilderException {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        stateCensusAnalyzer.loadCensusRecords(CSV_FILE_PATH);
        String sortedData = stateCensusAnalyzer.getSortedCensusDataAreaWise();
        StateCensusPojo[] stateCensusPojo = new Gson().fromJson(sortedData, StateCensusPojo[].class);
        Assert.assertEquals(342239, stateCensusPojo[0].area);
    }

    @Test
    public void givenUSCensusData_WhenRecordsEqual_ShouldReturnTrue() throws CSVBuilderException {
        final String CSV_FILE_PATH = "src/test/resources/USCensusData.csv";
        int numOfRecords = stateCensusAnalyzer.loadUSCensusRecords(CSV_FILE_PATH);
        Assert.assertEquals(51, numOfRecords);
    }
}

package com.bridgeLabz.serviceTestCases;

import com.bridgeLabz.Exception.CSVBuilderException;
import com.bridgeLabz.model.CSVUSCensus;
import com.bridgeLabz.model.StateCensusPojo;
import com.bridgeLabz.service.StateCensusAnalyzer;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import static com.bridgeLabz.service.StateCensusAnalyzer.Country.INDIA;
import static com.bridgeLabz.service.StateCensusAnalyzer.Country.US;

public class TestCases {

    StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(INDIA);
    StateCensusAnalyzer usStateCensusAnalyzer = new StateCensusAnalyzer(US);

    String STATECENSUS_PATH = "src/test/resources/StateCensusData.csv";
    String STATECODE_PATH = "src/test/resources/StateCode.csv";
    String USSTATE_PATH = "src/test/resources/USCensusData.csv";

    @Test
    public void givenNumberOfRecords_WhenMatched_ShouldReturnTrue() throws CSVBuilderException {
        int numberOfRecords = stateCensusAnalyzer.loadCensusRecords(STATECENSUS_PATH);
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
        int numberOfRecords = stateCensusAnalyzer.loadCensusRecords(STATECENSUS_PATH, STATECODE_PATH);
        Assert.assertEquals(37, numberOfRecords);
    }

    @Test
    public void givenFileNameOfStateCode_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/tateCode.csv";
        try {
            stateCensusAnalyzer.loadCensusRecords(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenFileTypeOfStateCode_WhenWrong_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.txt";
        try {
            stateCensusAnalyzer.loadCensusRecords(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenFileOfStateCode_WhenDelimiterIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode1.csv";
        try {
            stateCensusAnalyzer.loadCensusRecords(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenFileOfStateCode_WhenHeadersIncorrect_ShouldReturnCustomiseException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode2.csv";
        try {
            stateCensusAnalyzer.loadCensusRecords(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, e.exceptionType);
        }
    }

    @Test
    public void givenCensusData_WhenSorted_ShouldReturnSortedList() throws CSVBuilderException {
        stateCensusAnalyzer.loadCensusRecords(STATECENSUS_PATH);
        String SortedData = stateCensusAnalyzer.getSortedCensusData(StateCensusAnalyzer.SortingMode.STATE);
        StateCensusPojo[] censusCSV = new Gson().fromJson(SortedData, StateCensusPojo[].class);
        Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
    }

    @Test
    public void givenStateCensusData_WhenPopulationSorted_ShouldReturnSortedResult() throws CSVBuilderException {
        stateCensusAnalyzer.loadCensusRecords(STATECENSUS_PATH);
        String sortedData = stateCensusAnalyzer.getSortedCensusData(StateCensusAnalyzer.SortingMode.POPULATION);
        StateCensusPojo[] stateCensusPojo = new Gson().fromJson(sortedData, StateCensusPojo[].class);
        Assert.assertEquals(199812341, stateCensusPojo[0].population, 0);
    }

    @Test
    public void givenStateCensusData_WhenDensitySorted_ShouldReturnSortedResult() throws CSVBuilderException {
        stateCensusAnalyzer.loadCensusRecords(STATECENSUS_PATH);
        String sortedData = stateCensusAnalyzer.getSortedCensusData(StateCensusAnalyzer.SortingMode.DENSITY);
        StateCensusPojo[] stateCensusPojo = new Gson().fromJson(sortedData, StateCensusPojo[].class);
        Assert.assertEquals(1102, stateCensusPojo[0].density, 0);
    }

    @Test
    public void givenStateCensusData_WhenAreaSorted_ShouldReturnSortedResult() throws CSVBuilderException {
        stateCensusAnalyzer.loadCensusRecords(STATECENSUS_PATH);
        String sortedData = stateCensusAnalyzer.getSortedCensusData(StateCensusAnalyzer.SortingMode.AREA);
        StateCensusPojo[] stateCensusPojo = new Gson().fromJson(sortedData, StateCensusPojo[].class);
        Assert.assertEquals(342239, stateCensusPojo[0].area, 0);
    }

    @Test
    public void givenUSCensusData_WhenRecordsEqual_ShouldReturnTrue() throws CSVBuilderException {
        int numOfRecords = usStateCensusAnalyzer.loadCensusRecords(USSTATE_PATH);
        Assert.assertEquals(51, numOfRecords);
    }

    @Test
    public void givenTheUSCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() throws CSVBuilderException {
        usStateCensusAnalyzer.loadCensusRecords(USSTATE_PATH);
        String sortedData = usStateCensusAnalyzer.getSortedCensusData(StateCensusAnalyzer.SortingMode.POPULATION);
        CSVUSCensus[] censusDAO = new Gson().fromJson(sortedData, CSVUSCensus[].class);
        Assert.assertEquals("California", censusDAO[0].usState);
    }

    @Test
    public void givenTheUSCensusData_WhenSortedByDensity_ShouldReturnSortedResult() throws CSVBuilderException {
        usStateCensusAnalyzer.loadCensusRecords(USSTATE_PATH);
        String sortedData = usStateCensusAnalyzer.getSortedCensusData(StateCensusAnalyzer.SortingMode.DENSITY);
        CSVUSCensus[] censusDAO = new Gson().fromJson(sortedData, CSVUSCensus[].class);
        Assert.assertEquals("District of Columbia", censusDAO[0].usState);
    }

    @Test
    public void givenTheUSCensusData_WhenSortedByArea_ShouldReturnSortedResult() throws CSVBuilderException {
        usStateCensusAnalyzer.loadCensusRecords(USSTATE_PATH);
        String sortedData = usStateCensusAnalyzer.getSortedCensusData(StateCensusAnalyzer.SortingMode.AREA);
        CSVUSCensus[] censusDAO = new Gson().fromJson(sortedData, CSVUSCensus[].class);
        Assert.assertEquals("Alaska", censusDAO[0].usState);
    }

    @Test
    public void givenIndianCensusData_WhenSortedPopulationAndDensity_ShouldReturnSortedList() throws CSVBuilderException {
        stateCensusAnalyzer.loadCensusRecords(STATECENSUS_PATH);
        String sortedCensusData = stateCensusAnalyzer.getDualSortByPopulationDensity();
        StateCensusPojo[] stateCensuses = new Gson().fromJson(sortedCensusData, StateCensusPojo[].class);
        Assert.assertEquals("Uttar Pradesh", stateCensuses[0].state);
    }

    @Test
    public void givenUSCensusData_WhenSortedPopulationAndDensity_ShouldReturnSortedList() throws CSVBuilderException {
        usStateCensusAnalyzer.loadCensusRecords(USSTATE_PATH);
        String sortedCensusData = usStateCensusAnalyzer.getDualSortByPopulationDensity();
        CSVUSCensus[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVUSCensus[].class);
        Assert.assertEquals("California", stateCensuses[0].usState);
    }
}


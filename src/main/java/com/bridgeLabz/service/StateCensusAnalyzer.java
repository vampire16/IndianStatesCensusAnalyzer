package com.bridgeLabz.service;

import com.bridgeLabz.Exception.CSVBuilderException;
import com.bridgeLabz.model.StateCensusPojo;
import com.bridgeLabz.model.StateCodePojo;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class StateCensusAnalyzer {

    //    VARIABLES
    List<StateCensusPojo> stateCensusRecords = null;
    List<StateCodePojo> stateCodeRecords = null;
    Map<String, StateCensusPojo> stateCensusMap;
    Map<String, StateCodePojo> stateCodeMap;

    public StateCensusAnalyzer() {
        this.stateCensusMap = new HashMap<>();
        this.stateCodeMap = new HashMap<>();
    }

    //    METHOD TO LOAD RECORDS OF CSV FILE
    public int loadCensusRecords(String path) throws CSVBuilderException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(path))
        ) {
            OpenCSVBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<StateCensusPojo> censusIterator = csvBuilder.getIterator(reader, StateCensusPojo.class);
            while (censusIterator.hasNext()) {
                StateCensusPojo stateCensusPojo = censusIterator.next();
                this.stateCensusMap.put(stateCensusPojo.state, stateCensusPojo);
                stateCensusRecords = stateCensusMap.values().stream().collect(Collectors.toList());
            }
            int numberOfRecords = stateCensusMap.size();
            return numberOfRecords;
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.FILE_NOT_FOUND);
        } catch (RuntimeException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public int loadStateCodeRecords(String path) throws CSVBuilderException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(path))
        ) {
            OpenCSVBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<StateCodePojo> codeIterator = csvBuilder.getIterator(reader, StateCodePojo.class);
            while (codeIterator.hasNext()) {
                StateCodePojo stateCodePojo = codeIterator.next();
                this.stateCodeMap.put(stateCodePojo.stateCode, stateCodePojo);
                stateCodeRecords = stateCodeMap.values().stream().collect(Collectors.toList());
            }
            int numberOfRecords = stateCodeMap.size();
            return numberOfRecords;
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.FILE_NOT_FOUND);
        } catch (RuntimeException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public String getSortedCensusData() {
        Comparator<StateCensusPojo> CSVComparator = Comparator.comparing(census -> census.state);
        this.sort(CSVComparator, stateCensusRecords);
        String SortedCSVJson = new Gson().toJson(stateCensusRecords);
        return SortedCSVJson;
    }

    public String getSortedStateCodeData() {
        Comparator<StateCodePojo> CodeComparator = Comparator.comparing(code -> code.stateCode);
        this.sort(CodeComparator, stateCodeRecords);
        String SortedCodeJson = new Gson().toJson(stateCodeRecords);
        return SortedCodeJson;
    }

    private <E> void sort(Comparator<E> csvComparator, List<E> censusRecords) {
        for (int i = 0; i < censusRecords.size() - 1; i++) {
            for (int j = 0; j < censusRecords.size() - i - 1; j++) {
                E census1 = censusRecords.get(j);
                E census2 = censusRecords.get(j + 1);
                if (csvComparator.compare(census1, census2) > 0) {
                    censusRecords.set(j, census2);
                    censusRecords.set(j + 1, census1);
                }
            }
        }
    }
}


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
    List<CensusDAO> stateCensusList;
    Map<String, CensusDAO> stateCensusMap;

    public StateCensusAnalyzer() {
        this.stateCensusMap = new HashMap<>();
        this.stateCensusList = new ArrayList<>();
    }

    //    METHOD TO LOAD RECORDS OF CSV FILE
    public int loadCensusRecords(String path) throws CSVBuilderException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(path))
        ) {
            OpenCSVBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<StateCensusPojo> censusIterator = csvBuilder.getIterator(reader, StateCensusPojo.class);
            while (censusIterator.hasNext()) {
                CensusDAO censusDAO = new CensusDAO(censusIterator.next());
                this.stateCensusMap.put(censusDAO.state, censusDAO);
                stateCensusList = stateCensusMap.values().stream().collect(Collectors.toList());
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
                CensusDAO censusDAO = new CensusDAO (codeIterator.next());
                this.stateCensusMap.put(censusDAO.stateCode, censusDAO);
                stateCensusList = stateCensusMap.values().stream().collect(Collectors.toList());
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


    public String getSortedCensusData() {
        Comparator<CensusDAO> csvComparator = Comparator.comparing(census -> census.state);
        this.sort(csvComparator);
        String SortedCSVJson = new Gson().toJson(stateCensusList);
        return SortedCSVJson;
    }

    public String getSortedStateCodeData() {
        Comparator<CensusDAO> codeComparator = Comparator.comparing(code -> code.stateCode);
        this.sort(codeComparator);
        String SortedCodeJson = new Gson().toJson(stateCensusList);
        return SortedCodeJson;
    }

    public String getSortedCensusDataPopulationWise(){
        Comparator<CensusDAO> populationComparator = Comparator.comparing(census -> census.population);
        this.sort(populationComparator);
        Collections.reverse(stateCensusList);
        String sortedPopulationJson = new Gson().toJson(stateCensusList);
        return sortedPopulationJson;
    }

    public String getSortedCensusDataDensityWise(){
        Comparator<CensusDAO> densityComparator = Comparator.comparing(census -> census.density);
        this.sort(densityComparator);
        Collections.reverse(stateCensusList);
        String sortedDensityJson = new Gson().toJson(stateCensusList);
        return sortedDensityJson;
    }

    public String getSortedCensusDataAreaWise() {
        Comparator<CensusDAO> areaComparator = Comparator.comparing(census -> census.area);
        this.sort(areaComparator);
        Collections.reverse(stateCensusList);
        String sortedAreaJson = new Gson().toJson(stateCensusList);
        return sortedAreaJson;
    }

    private <E> void sort(Comparator<CensusDAO> csvComparator) {
        for (int i = 0; i < stateCensusList.size() - 1; i++) {
            for (int j = 0; j < stateCensusList.size() - i - 1; j++) {
                CensusDAO census1 = stateCensusList.get(j);
                CensusDAO census2 = stateCensusList.get(j + 1);
                if (csvComparator.compare(census1, census2) > 0) {
                    stateCensusList.set(j, census2);
                    stateCensusList.set(j + 1, census1);
                }
            }
        }
    }
}


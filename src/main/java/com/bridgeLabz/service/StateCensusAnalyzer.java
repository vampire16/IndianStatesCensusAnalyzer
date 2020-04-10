package com.bridgeLabz.service;

import com.bridgeLabz.Exception.CSVBuilderException;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class StateCensusAnalyzer {

    //    VARIABLES
    List<CensusDAO> stateCensusList;
    Map<String, CensusDAO> stateCensusMap;
    private Country country;

    public StateCensusAnalyzer() {
        this.stateCensusMap = new HashMap<>();
        this.stateCensusList = new ArrayList<>();
    }

    public StateCensusAnalyzer(Country country) {
        this.country = country;
    }

    //    METHOD TO LOAD DATA
    public int loadCensusRecords(String... csvPath) throws CSVBuilderException {
        stateCensusMap = AdapterFactory.getCensusData(country, csvPath);
        return stateCensusMap.size();
    }

    //    METHOD TO SORT DATA
    public String getSortedCensusData(SortingMode mode) {
        ArrayList arrayList = stateCensusMap.values().stream()
                .sorted(CensusDAO.getSortComparator(mode))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(arrayList);
    }

    public String getDualSortByPopulationDensity() {
        ArrayList arrayList = stateCensusMap.values().stream()
                .sorted(Comparator.comparingDouble(CensusDAO::getPopulation).thenComparingDouble(CensusDAO::getDensity).reversed())
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(arrayList);
    }

    public enum Country {INDIA, US}

    public enum SortingMode {STATE, POPULATION, DENSITY, AREA}
}


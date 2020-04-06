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

    public enum Country {INDIA, US}
    public enum SortingMode {STATE, POPULATION, DENSITY, AREA}

    public int loadCensusRecords(Country country, String... csvPath) throws CSVBuilderException {
        stateCensusMap = AdapterFactory.getCensusData(country, csvPath);
        stateCensusList = stateCensusMap.values().stream().collect(Collectors.toList());
        return stateCensusMap.size();
    }

    public String getSortedCensusData(SortingMode mode) {
        ArrayList arrayList = stateCensusMap.values().stream()
                .sorted(CensusDAO.getSortComparator(mode))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(arrayList);
    }

    public String getPopulationWiseUSSortedCensusData(){
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.population);
        this.sort(censusComparator);
        Collections.reverse(stateCensusList);
        String sortedStateCensusJson = new Gson().toJson(stateCensusList);
        return sortedStateCensusJson;
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


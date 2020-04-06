package com.bridgeLabz.service;

import com.bridgeLabz.model.CSVUSCensus;
import com.bridgeLabz.model.StateCensusPojo;
import com.bridgeLabz.model.StateCodePojo;

import java.util.Comparator;

public class CensusDAO {
    public String state;
    public double population;
    public double area;
    public double density;
    public String stateCode;
    public String stateName;
    public String srNo;
    public String tin;

    public CensusDAO(StateCensusPojo stateCensusPojo){
        this.state = stateCensusPojo.state;
        this.population = stateCensusPojo.population;
        this.area = stateCensusPojo.area;
        this.density = stateCensusPojo.density;
    }

    public CensusDAO(StateCodePojo stateCodePojo){
        this.stateCode = stateCodePojo.stateCode;
        this.stateName = stateCodePojo.stateName;
        this.srNo = stateCodePojo.srNo;
        this.tin = stateCodePojo.tin;
    }

    public CensusDAO(CSVUSCensus csvUSCensus) {
        this.state = csvUSCensus.usState;
        this.stateCode = csvUSCensus.usStateId;
        this.population = csvUSCensus.usPopulation;
        this.area = csvUSCensus.usArea;
        this.density = csvUSCensus.usPopulationDensity;
    }

    public static Comparator<CensusDAO> getSortComparator(StateCensusAnalyzer.SortingMode mode) {
        if (mode.equals(StateCensusAnalyzer.SortingMode.STATE))
            return Comparator.comparing(census -> census.state);
        if (mode.equals(StateCensusAnalyzer.SortingMode.POPULATION))
            return Comparator.comparing(CensusDAO::getPopulation).reversed();
        if (mode.equals(StateCensusAnalyzer.SortingMode.AREA))
            return Comparator.comparing(CensusDAO::getArea).reversed();
        if (mode.equals(StateCensusAnalyzer.SortingMode.DENSITY))
            return Comparator.comparing(CensusDAO::getDensity).reversed();
        return null;
    }

    public double getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    public double getDensity() {
        return density;
    }

    public Object getCensusDTO(StateCensusAnalyzer.Country country) {
        if (country.equals(StateCensusAnalyzer.Country.INDIA))
            return new StateCensusPojo(state, population, area, density);
        if (country.equals(StateCensusAnalyzer.Country.US))
            return new CSVUSCensus(stateCode, state, population, area, density);
        return null;
    }
}

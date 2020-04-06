package com.bridgeLabz.model;

import com.opencsv.bean.CsvBindByName;

public class CSVUSCensus {
    @CsvBindByName(column = "State Id", required = true)
    public String usStateId;
    @CsvBindByName(column = "State", required = true)
    public String usState;
    @CsvBindByName(column = "Population", required = true)
    public double usPopulation;
    @CsvBindByName(column = "Total area", required = true)
    public double usArea;
    @CsvBindByName(column = "Population Density", required = true)
    public double usPopulationDensity;

    public CSVUSCensus() {
    }

    public CSVUSCensus(String usStateId, String usState, double usPopulation, double usArea, double usPopulationDensity) {
        this.usStateId = usStateId;
        this.usState = usState;
        this.usPopulation = usPopulation;
        this.usArea = usArea;
        this.usPopulationDensity = usPopulationDensity;
    }

}

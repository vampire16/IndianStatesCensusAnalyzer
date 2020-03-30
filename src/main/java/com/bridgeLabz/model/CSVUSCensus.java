package com.bridgeLabz.model;

import com.opencsv.bean.CsvBindByName;

public class CSVUSCensus {
    @CsvBindByName(column = "State Id", required = true)
    public String usStateId;
    @CsvBindByName(column = "State", required = true)
    public String usState;
    @CsvBindByName(column = "Population", required = true)
    public String usPopulation;
    @CsvBindByName(column = "Total area", required = true)
    public String usArea;
    @CsvBindByName(column = "Population Density", required = true)
    public String usPopulationDensity;
}

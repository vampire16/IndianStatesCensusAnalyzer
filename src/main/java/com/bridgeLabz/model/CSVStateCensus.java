package com.bridgeLabz.model;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    private String population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    private String area;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    private String density;

    public CSVStateCensus() {
    }

    public CSVStateCensus(String state, String population, String area, String density) {
        this.state = state;
        this.population = population;
        this.area = area;
        this.density = density;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }
}

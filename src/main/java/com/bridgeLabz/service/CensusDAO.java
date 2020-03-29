package com.bridgeLabz.service;

import com.bridgeLabz.model.StateCensusPojo;
import com.bridgeLabz.model.StateCodePojo;

public class CensusDAO {
    public String state;
    public int population;
    public int area;
    public int density;
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

    }
}

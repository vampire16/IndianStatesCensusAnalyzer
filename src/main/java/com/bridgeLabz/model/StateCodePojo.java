package com.bridgeLabz.model;

import com.opencsv.bean.CsvBindByName;

public class StateCodePojo {
    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;
    @CsvBindByName(column = "SrNo", required = true)
    private String srno;
    @CsvBindByName(column = "StateName", required = true)
    private String stateName;
    @CsvBindByName(column = "TIN", required = true)
    private String tin;

    public StateCodePojo() {
    }

    public StateCodePojo(String srno, String stateName, String tin, String stateCode) {
        this.srno = srno;
        this.stateName = stateName;
        this.tin = tin;
        this.stateCode = stateCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}

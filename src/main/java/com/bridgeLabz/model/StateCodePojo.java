package com.bridgeLabz.model;

import com.opencsv.bean.CsvBindByName;

public class StateCodePojo {
    @CsvBindByName(column = "SrNo", required = true)
    private String srno;

    @CsvBindByName(column = "StateName", required = true)
    private String stateName;

    @CsvBindByName(column = "TIN", required = true)
    private String tin;

    @CsvBindByName(column = "StateCode", required = true)
    private String stateCode;

    public StateCodePojo() {
    }

    public StateCodePojo(String srno, String stateName, String tin, String stateCode) {
        this.srno = srno;
        this.stateName = stateName;
        this.tin = tin;
        this.stateCode = stateCode;
    }

    public String getSrno() {
        return srno;
    }

    public void setSrno(String srno) {
        this.srno = srno;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}

package com.bridgeLabz.model;

import com.opencsv.bean.CsvBindByName;

public class StateCodePojo {
    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;
    @CsvBindByName(column = "SrNo", required = true)
    public String srNo;
    @CsvBindByName(column = "StateName", required = true)
    public String stateName;
    @CsvBindByName(column = "TIN", required = true)
    public String tin;

    public StateCodePojo() {
    }

    public StateCodePojo(String srNo, String stateName, String tin, String stateCode) {
        this.srNo = srNo;
        this.stateName = stateName;
        this.tin = tin;
        this.stateCode = stateCode;
    }
}

package com.bridgeLabz.model;

import com.opencsv.bean.CsvBindByName;

public class StateCodePojo {
    @CsvBindByName(column = "SrNo")
    private String srno;

    @CsvBindByName(column = "State")
    private String state;

    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "TIN")
    private String tin;

    public StateCodePojo() {
    }

    public StateCodePojo(String srno, String state, String name, String tin) {
        this.srno = srno;
        this.state = state;
        this.name = name;
        this.tin = tin;
    }

    public String getSrno() {
        return srno;
    }

    public void setSrno(String srno) {
        this.srno = srno;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }
}

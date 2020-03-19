package com.bridgeLabz.service;

public class CSVBuilderFactory {
    public static OpenCSVBuilder createCsvBuilder(){
        return new OpenCSVBuilder();
    }
}

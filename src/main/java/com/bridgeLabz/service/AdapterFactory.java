package com.bridgeLabz.service;

import com.bridgeLabz.Exception.CSVBuilderException;

import java.util.Map;

public class AdapterFactory {
    public static Map<String, CensusDAO> getCensusData(StateCensusAnalyzer.Country country, String[] csvFilePath) throws CSVBuilderException {
        if (country.equals(StateCensusAnalyzer.Country.INDIA))
            return new IndianCensusAdapter().loadCensusData(csvFilePath);
        else if (country.equals(StateCensusAnalyzer.Country.US))
            return new USCensusAdapter().loadCensusData(csvFilePath);
        else
            throw new CSVBuilderException( "INVALID_COUNTRY", CSVBuilderException.ExceptionType.INVALID_COUNTRY);
    }
}



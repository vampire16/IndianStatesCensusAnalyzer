package com.bridgeLabz.service;

import com.bridgeLabz.Exception.CensusAnalyzerException;
import com.bridgeLabz.model.CSVStateCensus;
import com.bridgeLabz.model.StateCodePojo;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCode {
    private static String CSV_FILE_PATH;
    int count = 0;

    public StateCode(String path) {
        CSV_FILE_PATH = path;
    }

    public int loadStateCodeRecords() throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))
        ) {
            CsvToBean<StateCodePojo> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(StateCodePojo.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<StateCodePojo> userIterator = csvToBean.iterator();

            while (userIterator.hasNext()) {
                StateCodePojo stateCodePojo = userIterator.next();
                System.out.println("SrNo : " + stateCodePojo.getSrno());
                System.out.println("State : " + stateCodePojo.getState());
                System.out.println("Name : " + stateCodePojo.getName());
                System.out.println("TIN : " + stateCodePojo.getTin());
                System.out.println("==========================");
                count++;
            }
        }
        return count;
    }
}

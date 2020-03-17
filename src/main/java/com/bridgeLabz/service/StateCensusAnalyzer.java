package com.bridgeLabz.service;

import com.bridgeLabz.model.CSVStateCensus;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyzer {
        private static final String CSV_FILE_PATH = "/home/admin2/IndianStatesCensusAnalyzer/src/test/resources/StateCensusData.csv";
        int count=0;

        public int loadRecords() throws IOException {
            try (
                    Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
            ) {
                CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(CSVStateCensus.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                Iterator<CSVStateCensus> csvUserIterator = csvToBean.iterator();

                while (csvUserIterator.hasNext()) {
                    CSVStateCensus csvStateCensus = csvUserIterator.next();
                    System.out.println("Name : " + csvStateCensus.getState());
                    System.out.println("Email : " + csvStateCensus.getPopulation());
                    System.out.println("Phone : " + csvStateCensus.getArea());
                    System.out.println("Country : " + csvStateCensus.getDensity());
                    System.out.println("==========================");
                    count++;
                }
            }
            return count;
        }
    }


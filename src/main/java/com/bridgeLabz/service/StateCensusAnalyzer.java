package com.bridgeLabz.service;

import com.bridgeLabz.Exception.CSVBuilderException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class StateCensusAnalyzer <E>{

//    VARIABLES
    private static String CSV_FILE_PATH;
    private final Class<E> csvClass;
    int count = 0;

    public StateCensusAnalyzer(String path, Class<E> csvClss) {
        CSV_FILE_PATH = path;
        csvClass = csvClss;
    }

//    METHOD TO LOAD RECORDS OF CSV FILE
    public int loadRecords() throws CSVBuilderException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))
        ) {
            OpenCSVBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            List<E> csvUserList = csvBuilder.getList(reader, csvClass);
            return csvUserList.size();
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.FILE_NOT_FOUND);
        } catch (RuntimeException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}


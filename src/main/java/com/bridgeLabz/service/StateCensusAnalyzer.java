package com.bridgeLabz.service;

import com.bridgeLabz.Exception.CensusAnalyzerException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

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
    public int loadRecords() throws CensusAnalyzerException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))
        ) {
            Iterator csvUserIterator = new OpenCSVBuilder().getIterator(reader, csvClass);
            while (csvUserIterator.hasNext()) {
                csvUserIterator.next();
                count++;
            }
        } catch (NoSuchFileException e) {
            throw new CensusAnalyzerException(e.getMessage(),
                    CensusAnalyzerException.ExceptionType.FILE_NOT_FOUND);
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(e.getMessage(),
                    CensusAnalyzerException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}


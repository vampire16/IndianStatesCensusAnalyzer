package com.bridgeLabz.service;

import com.bridgeLabz.Exception.CSVBuilderException;
import com.bridgeLabz.model.CSVUSCensus;
import com.bridgeLabz.model.StateCensusPojo;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;


public abstract class CensusAdapter {
    public abstract Map<String, CensusDAO> loadCensusData(String... csvPath) throws CSVBuilderException;

    public <E> Map<String, CensusDAO> loadCensusData(Class<E> csvClass, String csvPath) throws CSVBuilderException {
        Map<String, CensusDAO> censusDAOMap = new HashMap<>();
        try (
                    Reader reader = Files.newBufferedReader(Paths.get(csvPath))
        ) {
            OpenCSVBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<E> censusIterator = csvBuilder.getIterator(reader, csvClass);
            Iterable<E> censusIterable = () -> censusIterator;
            if (csvClass.getName().equals("com.bridgeLabz.model.StateCensusPojo")) {
                StreamSupport.stream(censusIterable.spliterator(), false)
                        .map(StateCensusPojo.class::cast)
                        .forEach(censusCSV -> censusDAOMap.put(censusCSV.state,
                                new CensusDAO(censusCSV)));
            } else if (csvClass.getName().equals("com.bridgeLabz.model.CSVUSCensus")) {
                StreamSupport.stream(censusIterable.spliterator(), false)
                        .map(CSVUSCensus.class::cast)
                        .forEach(censusCSV -> censusDAOMap.put(censusCSV.usState,
                                new CensusDAO(censusCSV)));
            }
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.FILE_NOT_FOUND);
        } catch (RuntimeException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return censusDAOMap;
    }
}

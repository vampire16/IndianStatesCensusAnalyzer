package com.bridgeLabz.service;

import com.bridgeLabz.Exception.CSVBuilderException;
import com.bridgeLabz.model.StateCensusPojo;
import com.bridgeLabz.model.StateCodePojo;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndianCensusAdapter extends CensusAdapter{
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvPath) throws CSVBuilderException {
        Map<String, CensusDAO> censusDAOMap = super.loadCensusData(StateCensusPojo.class, csvPath[0]);
        if (csvPath.length == 1)
            return censusDAOMap;
        return loadStateCodeRecords(censusDAOMap, csvPath[1]);
    }

    public Map<String, CensusDAO> loadStateCodeRecords(Map<String, CensusDAO> censusDAOMap,String path) throws CSVBuilderException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(path))
        ) {
            OpenCSVBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<StateCodePojo> codeIterator = csvBuilder.getIterator(reader, StateCodePojo.class);
            Iterable<StateCodePojo> codeIterable = () -> codeIterator;
            StreamSupport.stream(codeIterable.spliterator(), false)
                    .map(StateCodePojo.class::cast)
                    .forEach(stateCodePojo -> censusDAOMap.put(stateCodePojo.stateName, new CensusDAO(stateCodePojo)));
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

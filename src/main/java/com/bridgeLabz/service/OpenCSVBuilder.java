package com.bridgeLabz.service;

import com.bridgeLabz.Exception.CSVBuilderException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSVBuilder implements ICSVBuilder {

    //    ITERATOR OF CSV FILE
    @Override
    public <E> Iterator getIterator(Reader reader, Class csvClass) throws CSVBuilderException {
        try {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(csvClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.iterator();
        } catch (IllegalStateException e) {
            throw new CSVBuilderException("Unable to parse", CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

    //    LIST OF CSV FILE
    @Override
    public <E> List getList(Reader reader, Class csvClass) {
        CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader)
                .withType(csvClass)
                .withIgnoreLeadingWhiteSpace(true);
        return csvToBeanBuilder.build().parse();
    }
}


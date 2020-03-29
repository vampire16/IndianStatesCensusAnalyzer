package com.bridgeLabz.service;

import com.bridgeLabz.Exception.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder {
    <E> Iterator getIterator(Reader reader, Class csvClass) throws CSVBuilderException;
    <E> List getList(Reader reader, Class csvClass);
}

package com.bridgeLabz.serviceTestCases;

import com.bridgeLabz.service.StateCensusAnalyzer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestCases {

    @Test
    public void givenNumberOfRecords_WhenMatched_ReturnTrue() throws IOException {
        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer();
        int numberOfRecords = stateCensusAnalyzer.loadRecords();
        Assert.assertEquals(29,numberOfRecords);
    }
}

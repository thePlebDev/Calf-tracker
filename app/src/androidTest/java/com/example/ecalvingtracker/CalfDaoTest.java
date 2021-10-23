package com.example.ecalvingtracker;

import com.elliottSoftware.ecalvingtracker.models.Calf;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CalfDaoTest extends CalfDatabaseTest{
    Calf calfTest1 = new Calf(1,"test-1", "TEST 1", new Date(),"Bull","test-1");
    @Test
    public void insertTest() throws Exception{
        //INSERT
        InsertUtil insertUtil = new InsertUtil(calfDatabase);
        int returnedValue = insertUtil.insertCalf(calfTest1);

        //ASSERT
        Assert.assertEquals(1,returnedValue);

    }


    @Test
    public void getCalfTest() throws ExecutionException, InterruptedException {

        //INSERT
        RetrieveUtil retrieveUtil = new RetrieveUtil(calfDatabase);
        retrieveUtil.insertCalf(calfTest1);

        //RETRIEVE
        int returnedCalfId = retrieveUtil.retrieveCalf(1).getId(); //THIS WILL BLOCK

        //ASSERT
        Assert.assertEquals(1,returnedCalfId);
    }

        @Test
    public void updateTest() throws Exception{
            //INSERT
            UpdateUtil updateUtil = new UpdateUtil(calfDatabase);

            updateUtil.insertCalf(calfTest1);

            //UPDATE
            String UPDATED_TAG_NUMBER = "223F";
            Calf updatedCalf = new Calf(1,"test-1", "TEST 1", new Date(),"Bull","test-1");
            updatedCalf.setTagNumber(UPDATED_TAG_NUMBER);

            updateUtil.updateCalf(updatedCalf); //THIS WILL BLOCK

            //RETRIEVE
            String returnedCalfTagNumber = updateUtil.retrieveCalf(1).getTagNumber();

            //ASSERT
            Assert.assertEquals(UPDATED_TAG_NUMBER,returnedCalfTagNumber);
    }

    @Test
    public void deleteTest(){

    }
    @Test
    public void deleteAll(){

    }


}


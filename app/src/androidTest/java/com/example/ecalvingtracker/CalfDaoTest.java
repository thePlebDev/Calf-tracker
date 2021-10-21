package com.example.ecalvingtracker;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class CalfDaoTest extends CalfDatabaseTest{

    Calf calfTest1 = new Calf(1,"test-1", "TEST 1", new Date(),"Bull","test-1");


    @Test
    public void insertReadDelete() throws Exception{
        //INSERT
        // we need a future so that it will block until it happens correctly

        int testingReturn = 1;

        Future<Integer> calf =  CalfRoomDatabase
                .databaseWriteExecutor.submit(new Runnable() {
                    @Override
                    public void run() {
                        getCalfDao().insert(calfTest1);
                    }
                }, testingReturn);

            int returningCalf = calf.get(); //This will block

        Assert.assertEquals(testingReturn,returningCalf);

        //READ
        //THIS COULD BE MORE ASYNC CODE TO GET THE CALF WITH THE ID

        //ASSERT

    }
}


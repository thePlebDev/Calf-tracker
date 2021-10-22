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

    @Test
    public void insertTest() throws Exception{
        //INSERT
        Calf calfTest1 = new Calf(1,"test-1", "TEST 1", new Date(),"Bull","test-1");
        int testingReturnValue = 1;

        //
        Future<Integer> calf =  CalfDatabaseTest
                .databaseWriteExecutor.submit(new Runnable() { // RUNNABLE WITH RETURN VALUE UPON SUCCESS
                    @Override
                    public void run() {
                        getCalfDao().insert(calfTest1);
                    }
                }, testingReturnValue); //RETURN A FUTURE OBJECT

            int returningCalfValue = calf.get(); //This will block and get the runnable return value

        Assert.assertEquals(testingReturnValue,returningCalfValue);



    }
}


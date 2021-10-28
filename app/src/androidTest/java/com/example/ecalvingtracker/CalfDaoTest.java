package com.example.ecalvingtracker;

import android.os.Handler;
import android.os.Looper;

import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.example.ecalvingtracker.util.DeleteUtil;
import com.example.ecalvingtracker.util.InsertUtil;
import com.example.ecalvingtracker.util.LiveDataUtil;
import com.example.ecalvingtracker.util.RetrieveUtil;
import com.example.ecalvingtracker.util.UpdateUtil;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

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
    public void deleteTest() throws Exception {
        //INSERT
        DeleteUtil deleteUtil = new DeleteUtil(calfDatabase);
        deleteUtil.insertCalf(calfTest1);

        //DELETE
        deleteUtil.deleteCalf(calfTest1);

        //RETRIEVE
        Calf returnedCalf = deleteUtil.retrieveCalf(1);//THIS WILL BE null

        Assert.assertEquals(null,returnedCalf);
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
    public void deleteAll(){

    }

    @Test
    public void retrieveAll() throws Exception{
        //Insert
        InsertUtil insertUtil = new InsertUtil(calfDatabase);
        int returnedValue = insertUtil.insertCalf(calfTest1); //blocks here

        //Retrieve
        LiveData<List<Calf>> calfLiveDataList = getCalfDao().getAllCalves(); //Automatically created Async code

        LiveDataUtil liveDataUtil = new LiveDataUtil();
        Calf returnedCalf = liveDataUtil.getValue(calfLiveDataList);// will block

        int returnedId = returnedCalf.getId();

        //ASSERT
        Assert.assertEquals(1,returnedId);

    }

    @Test(expected = InterruptedException.class)
    public void simulatedInterruptedException () throws InterruptedException {
        LiveDataUtil liveDataUtil = new LiveDataUtil();
        liveDataUtil.throwInterruptedException();

    }

    @Test
    public void properInsertTest() throws ExecutionException, InterruptedException {
        //Insert
        InsertUtil insertUtil = new InsertUtil(calfDatabase);
        long returnedValue = insertUtil.properInsertCalf(calfTest1); //blocks here

        Assert.assertEquals(1L,returnedValue);

    }


}













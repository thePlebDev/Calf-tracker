package com.example.ecalvingtracker;

import android.os.Handler;
import android.os.Looper;

import com.elliottSoftware.ecalvingtracker.models.Calf;

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


        final CountDownLatch latch = new CountDownLatch(1);

        final Calf[] data = new Calf[1];



        Observer<List<Calf>> observer = new Observer<List<Calf>>() {

            @Override
            public void onChanged(List<Calf> listLiveData) {
                latch.countDown(); // this releases all the threads

                data[0] = listLiveData.get(0);

            }
        };



       Handler handler = new Handler(Looper.getMainLooper()); //This is the main thread

       handler.post(new Runnable() {
                        @Override
                        public void run() {
                            calfLiveDataList.observeForever(observer);
                        }
                    }
       );


        latch.await(2, TimeUnit.SECONDS);

        Calf returnedCalf = data[0];

        int id = returnedCalf.getId();


        Assert.assertEquals(1,id);

    }


}













package com.example.ecalvingtracker;

import android.os.Handler;
import android.os.Looper;

import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.repositories.RepositoryInsertUtil;
import com.elliottSoftware.ecalvingtracker.util.Resource;
import com.elliottSoftware.ecalvingtracker.util.concurrent.ConcurrentDelete;
import com.elliottSoftware.ecalvingtracker.util.concurrent.ConcurrentInsert;
import com.elliottSoftware.ecalvingtracker.util.concurrent.ConcurrentUpdate;
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

/**
 * TODO: rewrite all tests with the new util classes
 * **/
public class CalfDaoTest extends CalfDatabaseTest{
    Calf calfTest1 = new Calf(1,"test-1", "TEST 1", new Date(),"Bull","test-1");


    @Test
    public void insertTestSuccess(){
        long expectedReturnedValue = 1L;
        //INSERT
        ConcurrentInsert concurrentInsert = new ConcurrentInsert(getCalfDao());
        Resource<Long> resource = concurrentInsert.insertCalf(calfTest1);

        long returnedValue = resource.getData();

        //ASSERT
        Assert.assertEquals(1L,returnedValue);

    }

    @Test
    public void insertTestFail(){
        //SETUP
        int expectedReturnValue = -1;
        Calf calfTest2 = new Calf(1,"test-1", "TEST 1", new Date(),"Bull","test-1");
        calfTest2.setTagNumber(null);
        //INSERT
        ConcurrentInsert concurrentInsert = new ConcurrentInsert(getCalfDao());

        Resource<Long> resource = concurrentInsert.insertCalf(calfTest2);
        long returnedValue = resource.getData();

        Assert.assertEquals(-1,returnedValue);



    }


    @Test
    public void updateTest(){
            Calf calfTest2 = new Calf(1,"test-1", "TEST 1", new Date(),"Bull","test-1");
            Calf calfTest2Updated = new Calf(1,"test-2", "TEST 1", new Date(),"Bull","test-1");
            //INSERT
            ConcurrentUpdate concurrentUpdate = new ConcurrentUpdate(getCalfDao());
            Resource<Long> resource = concurrentUpdate.insertCalf(calfTest2);
            long returnedValue = resource.getData();

            //UPDATE
            Resource<Integer> updateResource = concurrentUpdate.updateCalf(calfTest2Updated);
            int data = updateResource.getData();

            //ASSERT
            Assert.assertEquals(1,data);
    }

    @Test
    public void updateTestFail(){
        Calf calfTest2 = new Calf(1,"test-1", "TEST 1", new Date(),"Bull","test-1");
        Calf calfTest2Updated = new Calf(2,"test-2", "TEST 1", new Date(),"Bull","test-1");
        //INSERT
        ConcurrentUpdate concurrentUpdate = new ConcurrentUpdate(getCalfDao());
        Resource<Long> resource = concurrentUpdate.insertCalf(calfTest2);
        long returnedValue = resource.getData();

        //UPDATE
        Resource<Integer> updateResource = concurrentUpdate.updateCalf(calfTest2Updated);
        int data = updateResource.getData();

        //ASSERT
        Assert.assertEquals(0,data);

    }

    @Test
    public void deleteTest() throws Exception {
        //SET UP
        int SUCESSDELETEVALUE = 1;
        //INSERT
        ConcurrentDelete concurrentDelete = new ConcurrentDelete(getCalfDao());
        concurrentDelete.insertCalf(calfTest1);

        //DELETE
       Resource<Integer> deleteResource = concurrentDelete.deleteCalf(calfTest1);
       int deleteValue = deleteResource.getData();

       //ASSERT
        Assert.assertEquals(SUCESSDELETEVALUE,1);
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



    @Test
    public void properInsertTestResourceFail(){
        RepositoryInsertUtil insertUtil = new RepositoryInsertUtil(getCalfDao());
        String errorMessage = "Error! Try again";
        Calf calfTest1 = new Calf(1,"test-1", "TEST 1", new Date(),"Bull","test-1");
        calfTest1.setTagNumber(null);

        Resource<Integer> resource = insertUtil.insertMethod(calfTest1);
        int resourceData =resource.getData();
        String resourceMessage = resource.getMessage();


        Assert.assertEquals(-1,resourceData);
        Assert.assertEquals(errorMessage,resourceMessage);

    }


}













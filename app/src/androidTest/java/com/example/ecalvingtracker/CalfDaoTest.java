package com.example.ecalvingtracker;

import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.repositories.RepositoryInsertUtil;
import com.elliottSoftware.ecalvingtracker.util.Resource;
import com.elliottSoftware.ecalvingtracker.util.concurrent.delete.ConcurrentDeleteAll;
import com.elliottSoftware.ecalvingtracker.util.concurrent.delete.ConcurrentDeleteBase;
import com.elliottSoftware.ecalvingtracker.util.concurrent.delete.ConcurrentDeleteSingleItem;
import com.elliottSoftware.ecalvingtracker.util.concurrent.retrieve.ConcurrentRetrieve;
import com.elliottSoftware.ecalvingtracker.util.concurrent.retrieve.ConcurrentRetrieveBase;
import com.elliottSoftware.ecalvingtracker.util.concurrent.retrieve.ConcurrentRetrieveSingleItem;
import com.elliottSoftware.ecalvingtracker.util.concurrent.update.ConcurrentUpdate;
import com.elliottSoftware.ecalvingtracker.util.concurrent.insert.ConcurrentInsertBase;
import com.elliottSoftware.ecalvingtracker.util.concurrent.insert.ConcurrentInsertSingleItem;
import com.elliottSoftware.ecalvingtracker.util.concurrent.update.ConcurrentUpdateBase;
import com.elliottSoftware.ecalvingtracker.util.concurrent.update.ConcurrentUpdateSingleItem;
import com.example.ecalvingtracker.util.InsertUtil;
import com.example.ecalvingtracker.util.LiveDataUtil;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

/**
 * TODO: rewrite all tests with the new util classes
 * **/
public class CalfDaoTest extends CalfDatabaseTest{
    Calf calfTest1 = new Calf(1,"test-1", "TEST 1", new Date(),"Bull","test-1");


    @Test
    public void insertTestSuccess(){
        long expectedReturnedValue = 1L;
        //INSERT
        ConcurrentInsertBase concurrentInsertSingleItem = new ConcurrentInsertSingleItem(getCalfDao());
        Resource<Long> returnedResource = concurrentInsertSingleItem.insertCalf(calfTest1);

        long data = returnedResource.getData();
        Assert.assertEquals(1,data);

    }

    @Test
    public void insertTestFail(){

        //SETUP
        long expectedReturnValue = -1L;
        Calf calfTest2 = new Calf(1,"test-1", "TEST 1", new Date(),"Bull","test-1");
        calfTest2.setTagNumber(null);

        //INSERT
        ConcurrentInsertBase concurrentInsertSingleItem = new ConcurrentInsertSingleItem(getCalfDao());
        Resource<Long> returnedResource = concurrentInsertSingleItem.insertCalf(calfTest2);

        //ASSERT
        long data = returnedResource.getData();
        Assert.assertEquals(expectedReturnValue,data);



    }


    @Test
    public void updateTest(){
            //SETUP
            String UPDATE_TEXT = "TEST_2";
            int SUCCESSFUL_UPDATE = 1;
            Calf calfTest2 = new Calf(1,"test-1", "TEST 1", new Date(),"Bull","test-1");
            Calf calfTest2Updated = new Calf(1,UPDATE_TEXT, "TEST 1", new Date(),"Bull","test-1");
            //INSERT
            ConcurrentInsertBase concurrentInsertSingleItem = new ConcurrentInsertSingleItem(getCalfDao());
            concurrentInsertSingleItem.insertCalf(calfTest1);
            //UPDATE
            ConcurrentUpdateBase concurrentUpdateSingleItem = new ConcurrentUpdateSingleItem(getCalfDao());
            Resource<Integer> resource = concurrentUpdateSingleItem.updateCalf(calfTest2Updated);
            int data = resource.getData();
            //ASSERT
            Assert.assertEquals(SUCCESSFUL_UPDATE,data);
    }


    @Test
    public void deleteTest(){
        //SET UP
        int  SUCCESSFUL_DELETE = 1;

        //INSERT
        ConcurrentInsertBase concurrentInsertSingleItem = new ConcurrentInsertSingleItem(getCalfDao());
        Resource<Long> returnedResource = concurrentInsertSingleItem.insertCalf(calfTest1);

        //DELETE
        ConcurrentDeleteBase concurrentDeleteBaseSingleItem = new ConcurrentDeleteSingleItem(getCalfDao());
        Resource<Integer> deleteResource = concurrentDeleteBaseSingleItem.deleteCalf(calfTest1);

       //ASSERT
        int data = deleteResource.getData();
        Assert.assertEquals(SUCCESSFUL_DELETE,data);
    }

    @Test
    public void deleteAllTest(){
        //Arrange
        int SUCCESS_DELETE_VALUE = 2;
        //INSERT
        Calf calfTest1 = new Calf(1,"test-1", "TEST 1", new Date(),"Bull","test-1");
        Calf calfTest2 = new Calf(2,"test-1", "TEST 1", new Date(),"Bull","test-1");
        ConcurrentInsertBase concurrentInsert = new ConcurrentInsertSingleItem(getCalfDao());
        concurrentInsert.insertCalf(calfTest1);
        concurrentInsert.insertCalf(calfTest2);
        //DELETE
        ConcurrentDeleteBase concurrentDeleteAll = new ConcurrentDeleteAll(getCalfDao());
        Resource<Integer> resource = concurrentDeleteAll.deleteCalf(null);

        int resourceData = resource.getData();

        //Assert
        Assert.assertEquals(SUCCESS_DELETE_VALUE,resourceData);

    }


    @Test
    public void getCalfTestSuccess(){
        //SETUP
        int SUCCESSFUL_RETURNED_ID =1;

        //INSERT
        ConcurrentInsertBase concurrentInsertSingleItem = new ConcurrentInsertSingleItem(getCalfDao());
        concurrentInsertSingleItem.insertCalf(calfTest1);

        //RETRIEVE
        ConcurrentRetrieveBase concurrentRetrieveSingleCalf = new ConcurrentRetrieveSingleItem(getCalfDao());
        Resource<Calf> resource = concurrentRetrieveSingleCalf.retrieveCalf(1);
        int returnedCalfId = resource.getData().getId();
        //ASSERT
        Assert.assertEquals(SUCCESSFUL_RETURNED_ID,returnedCalfId);
    }
    /**
     * TODO: the code fails of there is no calf with that id
     * **/
    @Test
    public void getCalfTestFailure(){
        //INSERT
        ConcurrentRetrieve concurrentRetrieve = new ConcurrentRetrieve(getCalfDao());
        concurrentRetrieve.insertCalf(calfTest1);

        //RETRIEVE
        Resource<Calf> returnedCalf = concurrentRetrieve.retrieveCalf(2); //THIS WILL BLOCK
        int returnedCalfId = returnedCalf.getData().getId();

        //ASSERT
        Assert.assertEquals(1,returnedCalfId);

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


    }


}













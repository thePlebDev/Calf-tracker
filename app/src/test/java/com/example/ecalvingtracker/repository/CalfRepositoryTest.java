package com.example.ecalvingtracker.repository;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.repositories.CalfRepository;
import com.elliottSoftware.ecalvingtracker.util.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

public class CalfRepositoryTest {
    Calf calfTest1 = new Calf(1,"test-1", "TEST 1", new Date(),"Bull","test-1");


    //SYSTEM UNDER TEST
    private CalfRepository calfRepository;


    //the mocked dependencies
    private CalfDao calfDao;


    @Before
    public void initEach(){
        calfDao = Mockito.mock(CalfDao.class);
        calfRepository = new CalfRepository(calfDao);
    }

    /**
     * insert Calf
     * verify the correct method is called
     * confirm observer is triggered ?
     * confirm new rows inserted
     * **/

    @Test
    public void insertCalf() throws Exception{
        //Arrange
        final Long returnedData = 1l;
        Mockito.when(calfDao.insert(Mockito.any(Calf.class))).thenReturn(returnedData);

        //Act
        Resource<Long> data = calfRepository.(calfTest1);
        long successfulReturn = data.getData();

        //Assert
        Assert.assertEquals(1l,successfulReturn);

    }

//    @Test
//    public void updateCalf(){
//        //Arrange
//        final int returnedData = 1;
//        Mockito.when(calfDao.updateCalf(Mockito.any(Calf.class))).thenReturn(returnedData);
//
//        //Act
//        Resource<Integer> data = calfRepository.updateCalf(calfTest1);
//        int successfulReturned = data.getData();
//
//        //Assert
//        Assert.assertEquals(1,successfulReturned);
//
//    }

    @Test
    public void deleteCalf(){
        //Arrange
        final int returnedData = 1;
        Mockito.when(calfDao.delete(Mockito.any(Calf.class))).thenReturn(returnedData);

        //Act
        Resource<Integer> data = calfRepository.delete(calfTest1);
        int successfulReturnedData = data.getData();

        Assert.assertEquals(returnedData,successfulReturnedData);

    }

    @Test
    public void deleteAllCalves(){



    }

    @Test
    public void retrieveCalf(){
        //Arrange
        final int CALF_ID = 1;
        final Calf returnedCalf = new Calf(CALF_ID,"test-1", "TEST 1", new Date(),"Bull","test-1");
        Mockito.when(calfDao.getCalf(1)).thenReturn(returnedCalf);

        //Act
        Resource<Calf> data = calfRepository.getCalf(1);
        int returnedId = data.getData().getId();

        //Assert
        Assert.assertEquals(CALF_ID,returnedId);
    }




}

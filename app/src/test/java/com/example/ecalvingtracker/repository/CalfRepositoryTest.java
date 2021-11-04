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
    public void insertCalf() throws Exception {

        //Arrange
        final Long returnedData = 1l;
        Mockito.when(calfDao.insert(Mockito.any(Calf.class))).thenReturn(returnedData);

        //Act
        Resource<Long> data = calfRepository.insertCalf(calfTest1);
        long successfulReturn = data.getData();

        //Assert
        Assert.assertEquals(1l,successfulReturn);

    }

    @Test
    public void deleteCalf() throws Exception{
        //Arrange
        final int RETURNED_DATA =1;
        final int SUCCESSFUL_RETURN_VALUE =1;
        Mockito.when(calfDao.delete(Mockito.any(Calf.class))).thenReturn(RETURNED_DATA);

        //Act
        Resource<Integer> data = calfRepository.delete(calfTest1);
        int successfulReturn = data.getData();

        //Assert
        Assert.assertEquals(SUCCESSFUL_RETURN_VALUE,successfulReturn);
    }

    @Test
    public void updateCalf() throws Exception{
        //Arrange
        final int RETURNED_DATA = 1;
        final int EXPECTED_UPDATE_VALUE =1;
        Mockito.when(calfDao.updateCalf(Mockito.any(Calf.class))).thenReturn(RETURNED_DATA);

        //Act
        Resource<Integer> resource = calfRepository.updateCalf(calfTest1);
        int successfulUpdate = resource.getData();

        //Assert
        Assert.assertEquals(EXPECTED_UPDATE_VALUE,successfulUpdate);
    }

    @Test
    public void deleteAll() throws Exception {
        //Arrange
        final int EXPECTED_DELETE_VALUE =2;
        Mockito.when(calfDao.deleteAll()).thenReturn(EXPECTED_DELETE_VALUE);

        //Act
        Resource<Integer> resource = calfRepository.deleteAll();
        int successfulDelete = resource.getData();

        //Assert
        Assert.assertEquals(EXPECTED_DELETE_VALUE,successfulDelete);
    }

    @Test
    public void getCalf() throws Exception{
        //Arrange
        Mockito.when(calfDao.getCalf(1)).thenReturn(calfTest1);

        //Act
        Resource<Calf> resource = calfRepository.getCalf(1);
        int successfulRetrieval = resource.getData().getId();

        //Assert
        Assert.assertEquals(1,successfulRetrieval);

    }








}

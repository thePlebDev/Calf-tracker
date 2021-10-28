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

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

public class CalfRepositoryTest {
    Calf calfTest1 = new Calf(1,"test-1", "TEST 1", new Date(),"Bull","test-1");
    //SYSTEM UNDER TEST
    private CalfRepository calfRepository;



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
    public void insertNote_returnRow() throws Exception{
        //Arrange
        final Long returnedData = 1l; //WHAT SHOULD BE RETURNED

        Mockito.when(calfDao.properInsert(Mockito.any(Calf.class))).thenReturn(returnedData);


        //Act
        final Resource<Integer> returnedValue = calfRepository.properInsert(calfTest1);

        //Assert
        Mockito.verify(calfDao).properInsert(Mockito.any(Calf.class));


        Assert.assertEquals(Resource.success(1).getData(),returnedValue.getData());

    }

}

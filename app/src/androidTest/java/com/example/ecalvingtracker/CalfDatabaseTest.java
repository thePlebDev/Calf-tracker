package com.example.ecalvingtracker;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;

import org.junit.After;
import org.junit.Before;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

public class CalfDatabaseTest {

    private CalfRoomDatabase calfDatabase;

//    public CalfDao getCalfDao(){
//        // THE getCalfDao() NEEDS TO BE IMPLEMENTED
//        return calfDatabase.getCalfDao();
//    }

    @Before
    public void init(){
        calfDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                CalfRoomDatabase.class
        ).build();
    }

    @After
    public void finish(){
        calfDatabase.close();
    }
}

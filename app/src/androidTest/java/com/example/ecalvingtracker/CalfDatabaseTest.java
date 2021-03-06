package com.example.ecalvingtracker;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;

import org.junit.After;
import org.junit.Before;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

public class CalfDatabaseTest {

    protected volatile CalfRoomDatabase calfDatabase; // volatile allows all threads to see the changes made
    private  static final int NUMBER_OF_THREADS = 4;
    protected static  final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * GIVE ACCESS TO THE CALF DAO
     * **/
    public CalfDao getCalfDao(){
        // IMPLEMENTED BY ROOM
        return calfDatabase.getCalfDao();
    }


        /**
         * THIS WILL RUN BEFORE EACH TEST
         * **/
    @Before
    public void init(){
        calfDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                CalfRoomDatabase.class
        ).build();
    }

    /**
     * THIS WILL RUN AFTER EACH TEST
     * **/
    @After
    public void finish(){
        calfDatabase.close();
    }
}
















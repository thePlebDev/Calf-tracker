package com.example.ecalvingtracker.util;

import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;

import org.junit.Assert;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class InsertUtil {
    protected static volatile CalfRoomDatabase calfDatabase;

    private int returnedInsertValue =1;

    public InsertUtil(CalfRoomDatabase calfDatabase){
        this.calfDatabase = calfDatabase;
    }

    public int insertCalf(Calf insertCalf) throws ExecutionException, InterruptedException {


        Future<Integer> calf = calfDatabase.databaseWriteExecutor.submit(new Runnable() { // RUNNABLE WITH RETURN VALUE UPON SUCCESS
            @Override
            public void run() {
                calfDatabase.getCalfDao().insert(insertCalf);
            }
        }, returnedInsertValue); //RETURN A FUTURE OBJECT

        int returnedValue = calf.get();

        return returnedValue; //This will block and get the runnable return value
    }

    public long properInsertCalf(Calf insertCalf) throws ExecutionException, InterruptedException {

        Future<Long> calf = calfDatabase.databaseWriteExecutor.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return calfDatabase.getCalfDao().properInsert(insertCalf);
            }
        }); //RETURN A FUTURE OBJECT

        long returnedValue = calf.get();

        return returnedValue;

    }

    //GETTERS
    public CalfRoomDatabase getCalfDatabase(){
        return calfDatabase;
    }














}

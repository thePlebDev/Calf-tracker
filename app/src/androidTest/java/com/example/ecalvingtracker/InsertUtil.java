package com.example.ecalvingtracker;

import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;

import org.junit.Assert;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class InsertUtil {
    private volatile CalfRoomDatabase calfDatabase;

    private Calf calfTest;
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


    //SETTERS
    public void setCalfTest(Calf calfTest){
        this.calfTest = calfTest;
    }
    public void setReturnedInsertValue(int value){
        this.returnedInsertValue = value;
    }

    //GETTERS
    public CalfRoomDatabase getCalfDatabase(){
        return this.calfDatabase;
    }


}

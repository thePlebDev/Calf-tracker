package com.elliottSoftware.ecalvingtracker.util.concurrent.delete;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ConcurrentDeleteAll extends ConcurrentDeleteBase{
    public ConcurrentDeleteAll(CalfDao calfDao) {
        super(calfDao);
    }

    @Override
    <T> int deleteCalfCall(T callData) throws ExecutionException, InterruptedException {
        Future<Integer> integerFuture = CalfRoomDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return getCalfDao().deleteAll();
            }
        });
        int returnedValue = integerFuture.get(); //THIS METHOD DOES THE BLOCKING
        return returnedValue;
    }
}

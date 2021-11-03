package com.elliottSoftware.ecalvingtracker.util.concurrent.update;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ConcurrentUpdateSingleItem extends ConcurrentUpdateBase{
    public ConcurrentUpdateSingleItem(CalfDao calfDao) {
        super(calfDao);
    }

    @Override
    <T> int deleteCalfCall(T callData) throws ExecutionException, InterruptedException {
        Future<Integer> integerFuture = CalfRoomDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return getCalfDao().updateCalf((Calf) callData);
            }
        });
        int returnedIntegerFuture = integerFuture.get();
        return returnedIntegerFuture;
    }
}

package com.elliottSoftware.ecalvingtracker.util.concurrent.insert;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ConcurrentInsertSingleItem extends ConcurrentInsertBase{
    public ConcurrentInsertSingleItem(CalfDao calfDao) {
        super(calfDao);
    }

    @Override
     long insertCalfCall(Calf calfObject) throws ExecutionException, InterruptedException {
        Future<Long> longFuture = CalfRoomDatabase.databaseWriteExecutor.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return getCalfDao().insert(calfObject);
            }
        });
        long returnedLongFuture = longFuture.get();

        return returnedLongFuture;
    }
}

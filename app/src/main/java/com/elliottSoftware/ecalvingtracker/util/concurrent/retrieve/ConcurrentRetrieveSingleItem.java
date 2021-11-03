package com.elliottSoftware.ecalvingtracker.util.concurrent.retrieve;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ConcurrentRetrieveSingleItem extends ConcurrentRetrieveBase{
    public ConcurrentRetrieveSingleItem(CalfDao calfDao) {
        super(calfDao);
    }

    @Override
    public Calf retrieveItems(int retrieveData) throws ExecutionException, InterruptedException {
        Future<Calf> calfFuture = CalfRoomDatabase.databaseWriteExecutor.submit(new Callable<Calf>() {
            @Override
            public Calf call() throws Exception {
                return getCalfDao().getCalf(retrieveData);
            }
        });
        Calf returnCalfFuture = calfFuture.get();

        return returnCalfFuture;
    }
}



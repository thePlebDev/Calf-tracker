package com.example.ecalvingtracker;


import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DeleteUtil extends UpdateUtil {
    public DeleteUtil(CalfRoomDatabase calfDatabase) {
        super(calfDatabase);
    }

    public void deleteCalf(Calf calf) throws ExecutionException, InterruptedException {
         int successReturnedValue = 1;
        Future<Integer> returnedFuture = getCalfDatabase().databaseWriteExecutor.submit(new Runnable() {
            @Override
            public void run() {
                getCalfDatabase().getCalfDao().delete(calf);
            }
        },successReturnedValue);

        int returnedValue =returnedFuture.get();
    }
}

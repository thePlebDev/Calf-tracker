package com.example.ecalvingtracker;

import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class RetrieveUtil extends InsertUtil {
    public RetrieveUtil(CalfRoomDatabase calfDatabase) {
        super(calfDatabase);
    }

    public Calf retrieveCalf(int calfId) throws ExecutionException, InterruptedException {
        Future<Calf> returnedCalf = getCalfDatabase().databaseWriteExecutor.submit(new Callable<Calf>() {
            @Override
            public Calf call() {
                return getCalfDatabase().getCalfDao().getCalf(calfId);
            }
        });
        Calf returnedCalfValue = returnedCalf.get();

        return returnedCalfValue;
    }
}

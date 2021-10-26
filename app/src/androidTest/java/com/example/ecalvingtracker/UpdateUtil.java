package com.example.ecalvingtracker;

import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class UpdateUtil extends RetrieveUtil {

    private int returnedInsertValue = 1;
    public UpdateUtil(CalfRoomDatabase calfDatabase) {
        super(calfDatabase);
    }

    public int updateCalf(Calf updatedCalf) throws ExecutionException, InterruptedException {
        Future<Integer> calf = getCalfDatabase().databaseWriteExecutor.submit(new Runnable() { // RUNNABLE WITH RETURN VALUE UPON SUCCESS
            @Override
            public void run() {
                getCalfDatabase().getCalfDao().updateCalf(updatedCalf);
            }
        }, returnedInsertValue); //RETURN A FUTURE OBJECT

        int returnedInt = calf.get(); //BLOCKS UNTIL VALUE IS RETURNED
        return returnedInt;
    }
}

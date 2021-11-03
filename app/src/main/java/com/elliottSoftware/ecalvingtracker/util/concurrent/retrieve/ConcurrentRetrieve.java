package com.elliottSoftware.ecalvingtracker.util.concurrent.retrieve;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;
import com.elliottSoftware.ecalvingtracker.util.Resource;
import com.elliottSoftware.ecalvingtracker.util.concurrent.delete.ConcurrentDelete;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ConcurrentRetrieve extends ConcurrentDelete {

    public ConcurrentRetrieve(CalfDao calfDao) {
        super(calfDao);
    }

    /**
     * TODO: the Resource.error null data probably won't work for the UI. Needs to be -1
     * **/
    public Resource<Calf> retrieveCalf(int calfId){
        try{
            Calf returnedCalf = threadedCalfRetrieve(calfId);
            Resource<Calf> resource = new Resource<>(returnedCalf,"Success");
            return resource;
        } catch (InterruptedException e) {
            Resource<Calf> resource = Resource.error(null, "Error please try again");
            return resource;
        } catch (ExecutionException e) {
            Resource<Calf> resource = Resource.error(null, "Error please try again");
            return resource;
        }
    }

    public Calf threadedCalfRetrieve(int calfId) throws ExecutionException, InterruptedException {
        Future<Calf> calfFuture = CalfRoomDatabase.databaseWriteExecutor.submit(new Callable<Calf>() {
            @Override
            public Calf call() throws Exception {
                return getCalfDao().getCalf(calfId);
            }
        });
        Calf calfValue = calfFuture.get();
        return calfValue;
    }
}

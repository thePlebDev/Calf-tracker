package com.elliottSoftware.ecalvingtracker.util.concurrent;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;
import com.elliottSoftware.ecalvingtracker.util.Resource;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ConcurrentUpdate extends ConcurrentInsert{
    public ConcurrentUpdate(CalfDao calfDao) {
        super(calfDao);
    }


    public Resource<Integer> updateCalf(Calf calf){
        try{
            int returnedValue = threadedUpdate(calf);
            Resource<Integer> resource = new Resource<>(returnedValue,"Update Successful");
            return resource;
        } catch (InterruptedException e) {
            Resource<Integer> resource = new Resource<>(-1,"Update Successful");
            return resource;
        } catch (ExecutionException e) {
            Resource<Integer> resource = new Resource<>(-1,"Update Successful");
            return resource;
        }

    }


    public int threadedUpdate(Calf calf) throws ExecutionException, InterruptedException {
        Future<Integer> integerFuture = CalfRoomDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return getCalfDao().updateCalf(calf); // will return the amount of updated rows
            }
        });

        int returnedValue = integerFuture.get();
        return  returnedValue;
    }
}

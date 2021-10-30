package com.elliottSoftware.ecalvingtracker.util.concurrent;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;
import com.elliottSoftware.ecalvingtracker.util.Resource;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ConcurrentDelete extends ConcurrentUpdate{
    public ConcurrentDelete(CalfDao calfDao) {
        super(calfDao);
    }



    public Resource<Integer> deleteCalf(Calf calf){

        try{
            int returnedValue = threadedDelete(calf);
            Resource<Integer> resource = new Resource<>(returnedValue,"Success");
            return resource;
        } catch (InterruptedException e) {
            Resource<Integer> resource = new Resource<>(-1,"Error, please try again");
            return resource;
        } catch (ExecutionException e) {
            Resource<Integer> resource = new Resource<>(-1,"Error, please try again");
            return resource;
        }


    }


    public int threadedDelete(Calf calf) throws ExecutionException, InterruptedException {
        Future<Integer> integerFuture = CalfRoomDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return getCalfDao().delete(calf);
            }
        });
         int returnedFuture = integerFuture.get();
         return returnedFuture;
    }
}

package com.elliottSoftware.ecalvingtracker.util.concurrent.insert;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;
import com.elliottSoftware.ecalvingtracker.util.Resource;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ConcurrentInsert{

    private final CalfDao calfDao;

    public ConcurrentInsert(CalfDao calfDao){
        this.calfDao = calfDao;
    }


    public  Resource<Long> insertCalf(Calf insertCalf) {
        try{
            long returnedId = threadedInsert(insertCalf);
            Resource<Long> resource = new Resource<Long>(returnedId,"Success!");
            return resource;
        } catch (InterruptedException e) {
            Resource<Long> resource = new Resource<Long>(-1L,"Error, please try again");
            return resource;
        } catch (ExecutionException e) {
            Resource<Long> resource = new Resource<Long>(-1L,"Error, please try again");
            return resource;
        }

    }

public long threadedInsert(Calf calf) throws ExecutionException, InterruptedException {
    Future<Long> longFuture = CalfRoomDatabase.databaseWriteExecutor.submit(new Callable<Long>() {
        @Override
        public Long call() throws Exception {
            return calfDao.insert(calf);
        }
    });

    long calfValue = longFuture.get();
    return calfValue;
    }

    public CalfDao getCalfDao() {
        return calfDao;
    }
}

package com.elliottSoftware.ecalvingtracker.util.concurrent.insert;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.util.Resource;

import java.util.concurrent.ExecutionException;

public abstract class ConcurrentInsertBase {
    private final CalfDao calfDao;

    public ConcurrentInsertBase(CalfDao calfDao){
        this.calfDao = calfDao;
    }

    abstract long insertCalfCall(Calf callData) throws ExecutionException, InterruptedException;


    public Resource<Long> insertCalf(Calf insertCalf) {
        try{
            long returnedId = insertCalfCall(insertCalf);
            Resource<Long> resource = new Resource<Long>(returnedId,"Success!");
            return resource;
        } catch (Exception e) {
            Resource<Long> resource = new Resource<Long>(-1L,"Error, please try again");
            return resource;
        }
    }

    public CalfDao getCalfDao(){
        return this.calfDao;
    }

}

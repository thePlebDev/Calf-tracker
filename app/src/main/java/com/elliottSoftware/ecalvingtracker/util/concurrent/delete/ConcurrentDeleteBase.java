package com.elliottSoftware.ecalvingtracker.util.concurrent.delete;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.util.Resource;

import java.util.concurrent.ExecutionException;

public abstract class ConcurrentDeleteBase {
    private final CalfDao calfDao;

    public ConcurrentDeleteBase(CalfDao calfDao){
        this.calfDao = calfDao;

    }

    abstract <T> int deleteCalfCall(T callData) throws ExecutionException, InterruptedException;


    public <T> Resource<Integer> deleteCalf(T calf){

        try{
            int returnedValue = deleteCalfCall(calf);
            Resource<Integer> resource = new Resource<>(returnedValue,"Success");
            return resource;
        } catch (Exception e) {
            Resource<Integer> resource = new Resource<>(-1,"Error, please try again");
            return resource;
        }


    }

    public CalfDao getCalfDao(){
        return this.calfDao;
    }
}

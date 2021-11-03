package com.elliottSoftware.ecalvingtracker.util.concurrent.update;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.util.Resource;

import java.util.concurrent.ExecutionException;

public abstract class ConcurrentUpdateBase {
    private final CalfDao calfDao;


    public ConcurrentUpdateBase(CalfDao calfDao){
        this.calfDao = calfDao;
    }


    abstract <T> int deleteCalfCall(T callData) throws ExecutionException, InterruptedException;

    public Resource<Integer> updateCalf(Calf calf){
        try{
            int returnedValue = deleteCalfCall(calf);
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

    public CalfDao getCalfDao(){
        return this.calfDao;
    }

}

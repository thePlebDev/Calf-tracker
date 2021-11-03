package com.elliottSoftware.ecalvingtracker.util.concurrent.retrieve;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.util.Resource;

import java.util.concurrent.ExecutionException;

public abstract class ConcurrentRetrieveBase {

    private final CalfDao calfDao;

    public ConcurrentRetrieveBase(CalfDao calfDao){
        this.calfDao = calfDao;
    }

    public abstract  Calf retrieveItems(int retrieveData) throws ExecutionException, InterruptedException;


    public  Resource<Calf> retrieveCalf(int id){

        try{
            Calf returnedValue = retrieveItems(id);
            Resource<Calf> resource = new Resource<>(returnedValue,"Success");
            return resource;
        } catch (Exception e) {
            Resource<Calf> resource = new Resource<>(null,"Error, please try again");
            return resource;
        }


    }

    public CalfDao getCalfDao(){
        return this.calfDao;
    }



}

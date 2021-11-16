package com.elliottSoftware.ecalvingtracker.util.concurrent.retrieveLiveData;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.util.Resource;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public abstract class ConcurrentRetrieveLiveDataBase {
    private final CalfDao calfDao;

    ConcurrentRetrieveLiveDataBase(CalfDao calfDao){
        this.calfDao = calfDao;
    }

    public abstract LiveData<List<Calf>> retrieveItems(String retrieveData) throws ExecutionException, InterruptedException;


    public Resource<LiveData<List<Calf>>> retrieveCalf(String searchQuery){

        try{
            LiveData<List<Calf>> returnedValue = retrieveItems(searchQuery);
            Resource<LiveData<List<Calf>>> resource = new Resource<>(returnedValue,"Success");
            return resource;
        } catch (Exception e) {
            Resource<LiveData<List<Calf>>> resource = new Resource<>(null,"Error, please try again");
            return resource;
        }


    }
    public CalfDao getCalfDao(){
        return this.calfDao;
    }


}

package com.elliottSoftware.ecalvingtracker.util.concurrent.retrieveLiveData;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class ConcurrentRetrieveLiveDataTagNumberSearch extends ConcurrentRetrieveLiveDataBase{
    ConcurrentRetrieveLiveDataTagNumberSearch(CalfDao calfDao) {
        super(calfDao);
    }

    @Override
    public LiveData<List<Calf>> retrieveItems(String retrieveData) throws ExecutionException, InterruptedException {
        return getCalfDao().searchCalfTagNumber(retrieveData);
    }
}

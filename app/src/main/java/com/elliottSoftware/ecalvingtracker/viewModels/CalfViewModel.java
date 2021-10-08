package com.elliottSoftware.ecalvingtracker.viewModels;

import android.app.Application;

import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.repositories.CalfRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CalfViewModel extends AndroidViewModel {

    private CalfRepository mRepository;
    private final LiveData<List<Calf>> mAllCalves;


    public CalfViewModel( Application application) {
        //APPLICATION COULD BE MY POINT OF FAILURE
        super(application);
        mRepository = new CalfRepository(application);
        mAllCalves = mRepository.getAllCalves();

    }

    public LiveData<List<Calf>> getAllCalves(){
        return mAllCalves;
    }

    //we might do the checking here
    public Calf getCalf(int calfId) throws ExecutionException, InterruptedException {

       Calf calf = mRepository.getCalf(calfId);
        return calf;

    }

    public void insert(Calf calf){
        mRepository.insert(calf);
    }

    public void updateCalf(Calf calf){mRepository.updateCalf(calf);}


    public void deleteAll(){
        mRepository.deleteAll();
    }
    public void delete(Calf calf){
        mRepository.delete(calf);
    }
}

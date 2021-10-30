package com.elliottSoftware.ecalvingtracker.viewModels;

import android.app.Application;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;
import com.elliottSoftware.ecalvingtracker.repositories.CalfRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CalfViewModel extends ViewModel {

    private CalfRepository mRepository;
    private final LiveData<List<Calf>> mAllCalves;


    public CalfViewModel( CalfRepository repository) {
        //APPLICATION COULD BE MY POINT OF FAILURE

       //  CalfDao calfDao = CalfRoomDatabase.getDatabase(application).getCalfDao(); //ALSO INJECTED
        //mRepository = new CalfRepository(calfDao); //this should be injected
        mRepository = repository;
        mAllCalves = mRepository.getAllCalves();

    }

    public LiveData<List<Calf>> getAllCalves(){
        return mAllCalves;
    }

   /**
    * GETS THE CALF WITH THE MATCHING ID
    * **/
//    public Calf getCalf(int calfId) throws ExecutionException, InterruptedException {
//
//       Calf calf = mRepository.getCalf(calfId);
//        return calf;
//
//    }




    public void updateCalf(Calf calf){mRepository.updateCalf(calf);}


    public void deleteAll(){
        mRepository.deleteAll();
    }
    public void delete(Calf calf){
        mRepository.delete(calf);
    }
}

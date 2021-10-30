package com.elliottSoftware.ecalvingtracker.repositories;

import android.app.Application;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;
import com.elliottSoftware.ecalvingtracker.util.Resource;
import com.elliottSoftware.ecalvingtracker.util.concurrent.ConcurrentInsert;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import androidx.lifecycle.LiveData;

public class CalfRepository {
    private CalfDao mCalfDao;
    private LiveData<List<Calf>> mAllCalves;

    private static final String INSERT_SUCCESS ="Insert Success";
    private static final String INSERT_FAIL ="Insert Failed";
    private static final String CALF_TAGNUMBER_NULL ="Please enter tag number";

    //NOTE THAT IN ORDER TO UNIT TEST CalfRepository, WE HAVE TO REMOVE APPLICATION DEPENDENCY
    //FOR NOW IT IS FINE

    public CalfRepository( CalfDao calfDao){


        mCalfDao = calfDao; //DEPENDENCY INJECTION, SHOULD BE IN CONSTRUCTOR
        mAllCalves = mCalfDao.getAllCalves();
    }

    //ROOM EXECUTES ALL QUERIES ON A SEPARATE THREAD
    public LiveData<List<Calf>> getAllCalves(){
        return mAllCalves;
    }


    public Calf getCalf(int calfId) throws ExecutionException, InterruptedException {
        //THIS IS RETURNING A FUTURE
        //we are going to add a new callable to the submit
        Future<Calf> calf =  CalfRoomDatabase
                .databaseWriteExecutor.submit(new Callable<Calf>() {

            @Override
            public Calf call()  {
                return mCalfDao.getCalf(calfId);
            }
        });

        //GET SHOULD WAIT UNTIL WE GET WHAT WE WANT
        Calf gettingCalf = calf.get();
        String names = gettingCalf.getTagNumber();
        return gettingCalf;
    }
/**
 * method used to get calves from the database that have the matching tagNumber
 * TODO: ACTUA
 * **/



    //WE MUST CALL THIS ON A NON-UI THREAD, OR ELSE WE WILL GET AN EXCEPTION



    public void updateCalf(Calf calf){
        CalfRoomDatabase.databaseWriteExecutor.execute(()-> mCalfDao.updateCalf(calf));
    }

    public void delete(Calf calf){
        CalfRoomDatabase.databaseWriteExecutor.execute(()->{
            mCalfDao.delete(calf);
        });
    }

    public void deleteAll(){
        CalfRoomDatabase.databaseWriteExecutor.execute(()->{
            mCalfDao.deleteAll();
        });
    }

    /**
     * THIS METHOD IS HOW ALL METHODS SHOULD BE CREATED
     * **/
    public Resource<Integer> properInsert(Calf calf){

        RepositoryInsertUtil insertUtil = new RepositoryInsertUtil(mCalfDao);
        return insertUtil.insertMethod(calf);

    }

    public void checkTagNumber(Calf calf) throws Exception{
        if(calf.getTagNumber() == null){
            throw new Exception(CALF_TAGNUMBER_NULL);
        }
    }


}

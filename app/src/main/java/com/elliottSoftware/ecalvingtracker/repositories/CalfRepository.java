package com.elliottSoftware.ecalvingtracker.repositories;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.util.Resource;
import com.elliottSoftware.ecalvingtracker.util.concurrent.delete.ConcurrentDelete;
import com.elliottSoftware.ecalvingtracker.util.concurrent.delete.ConcurrentDeleteAll;
import com.elliottSoftware.ecalvingtracker.util.concurrent.delete.ConcurrentDeleteBase;
import com.elliottSoftware.ecalvingtracker.util.concurrent.delete.ConcurrentDeleteSingleItem;
import com.elliottSoftware.ecalvingtracker.util.concurrent.insert.ConcurrentInsertBase;
import com.elliottSoftware.ecalvingtracker.util.concurrent.insert.ConcurrentInsertSingleItem;
import com.elliottSoftware.ecalvingtracker.util.concurrent.retrieve.ConcurrentRetrieve;
import com.elliottSoftware.ecalvingtracker.util.concurrent.retrieve.ConcurrentRetrieveBase;
import com.elliottSoftware.ecalvingtracker.util.concurrent.retrieve.ConcurrentRetrieveSingleItem;
import com.elliottSoftware.ecalvingtracker.util.concurrent.update.ConcurrentUpdate;
import com.elliottSoftware.ecalvingtracker.util.concurrent.update.ConcurrentUpdateBase;
import com.elliottSoftware.ecalvingtracker.util.concurrent.update.ConcurrentUpdateSingleItem;

import java.util.List;

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
        mCalfDao = calfDao;
        mAllCalves = mCalfDao.getAllCalves();
    }

    //ROOM EXECUTES ALL QUERIES ON A SEPARATE THREAD
    public LiveData<List<Calf>> getAllCalves(){
        return mAllCalves;
    }


    public Resource<Calf> getCalf(int calfId) {
        ConcurrentRetrieveBase concurrentRetrieve = new ConcurrentRetrieveSingleItem(mCalfDao);
        Resource<Calf>  resource = concurrentRetrieve.retrieveCalf(calfId);

        return resource;

    }


    public Resource<Integer> updateCalf(Calf calf){
        ConcurrentUpdateBase concurrentUpdateSingleItem = new ConcurrentUpdateSingleItem(mCalfDao);
        Resource<Integer> resource = concurrentUpdateSingleItem.updateCalf(calf);
        return resource;
    }

    public Resource<Integer> delete(Calf calf){
        ConcurrentDeleteBase concurrentDeleteSingleItem = new ConcurrentDeleteSingleItem(mCalfDao);
        Resource<Integer> resource = concurrentDeleteSingleItem.deleteCalf(calf);
        return resource;
    }

    public Resource<Integer> deleteAll(){
        ConcurrentDeleteBase concurrentDeleteAll = new ConcurrentDeleteAll(mCalfDao);
        Resource<Integer> resource = concurrentDeleteAll.deleteCalf(null);
        return resource;
    }

    /**
     * THIS METHOD IS HOW ALL METHODS SHOULD BE CREATED
     * **/
    public Resource<Long> insertCalf(Calf calf){

        ConcurrentInsertBase concurrentInsertSingleItem = new ConcurrentInsertSingleItem(mCalfDao);
        Resource<Long> resource = concurrentInsertSingleItem.insertCalf(calf);
        return resource;


    }

    public void checkTagNumber(Calf calf) throws Exception{
        if(calf.getTagNumber() == null){
            throw new Exception(CALF_TAGNUMBER_NULL);
        }
    }


}

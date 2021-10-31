package com.elliottSoftware.ecalvingtracker.repositories;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.util.Resource;
import com.elliottSoftware.ecalvingtracker.util.concurrent.delete.ConcurrentDelete;
import com.elliottSoftware.ecalvingtracker.util.concurrent.ConcurrentRetrieve;
import com.elliottSoftware.ecalvingtracker.util.concurrent.ConcurrentUpdate;

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
        ConcurrentRetrieve concurrentRetrieve = new ConcurrentRetrieve(mCalfDao);
        Resource<Calf>  resource = concurrentRetrieve.retrieveCalf(calfId);

        return resource;

    }


    public Resource<Integer> updateCalf(Calf calf){
        ConcurrentUpdate concurrentUpdate = new ConcurrentUpdate(mCalfDao);
        Resource<Integer> resource = concurrentUpdate.updateCalf(calf);
        return resource;
    }

    public Resource<Integer> delete(Calf calf){
        ConcurrentDelete concurrentDelete = new ConcurrentDelete(mCalfDao);
        Resource<Integer> resource = concurrentDelete.deleteCalf(calf);
        return resource;
    }

    public Resource<Integer> deleteAll(){
        ConcurrentDelete concurrentDelete = new ConcurrentDelete(mCalfDao);
        Resource<Integer> resource = concurrentDelete.deleteAllCalves();
        return resource;
    }

    /**
     * THIS METHOD IS HOW ALL METHODS SHOULD BE CREATED
     * **/
    public Resource<Long> properInsert(Calf calf){

        RepositoryInsertUtil insertUtil = new RepositoryInsertUtil(mCalfDao);
        return insertUtil.insertMethod(calf);

    }

    public void checkTagNumber(Calf calf) throws Exception{
        if(calf.getTagNumber() == null){
            throw new Exception(CALF_TAGNUMBER_NULL);
        }
    }


}

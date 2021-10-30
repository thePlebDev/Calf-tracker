package com.elliottSoftware.ecalvingtracker.repositories;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;
import com.elliottSoftware.ecalvingtracker.util.Resource;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import androidx.room.RoomDatabase;

public class RepositoryInsertUtil {

    private final CalfDao mCalfDao;

    public RepositoryInsertUtil(CalfDao mCalfDao){
        this.mCalfDao = mCalfDao;

    }

    public Resource<Integer>insertMethod(Calf calf){
        //ALL THE EXCEPTIONS ARE HANDLED HERE
        try{
            long data = threadedInsert(calf);
            Resource<Integer> resource = Resource.success(1);
            return resource;
        } catch (Exception e) {
            Resource<Integer> resource = Resource.error(-1,"Error! Try again");
            return resource;
        }
    }

    public long threadedInsert(Calf calf) throws ExecutionException, InterruptedException {

        Future<Long> calfFuture = CalfRoomDatabase.databaseWriteExecutor.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mCalfDao.properInsert(calf);
            }
        });

        long futureValue = calfFuture.get();
        return futureValue;
    }


}

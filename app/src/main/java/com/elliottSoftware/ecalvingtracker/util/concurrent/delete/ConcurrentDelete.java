package com.elliottSoftware.ecalvingtracker.util.concurrent.delete;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;
import com.elliottSoftware.ecalvingtracker.util.Resource;
import com.elliottSoftware.ecalvingtracker.util.concurrent.ConcurrentUpdate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
/**
 * TODO: NOTICE ALL THE DUPLICATE BETWEEN deleteCalf and deleteAllCalves
 * **/
public class ConcurrentDelete extends ConcurrentUpdate {
    public ConcurrentDelete(CalfDao calfDao) {
        super(calfDao);
    }


    //SHOULD BE EXTRACTED AND PUT INTO A BASE CLASS FOR CODE REUSE
    public Resource<Integer> deleteCalf(Calf calf){

        try{
            int returnedValue = threadedDelete(calf);
            Resource<Integer> resource = new Resource<>(returnedValue,"Success");
            return resource;
        } catch (InterruptedException e) {
            Resource<Integer> resource = new Resource<>(-1,"Error, please try again");
            return resource;
        } catch (ExecutionException e) {
            Resource<Integer> resource = new Resource<>(-1,"Error, please try again");
            return resource;
        }


    }

    // SHOULD BE AN ABSTRACT METHOD INSIDE THE BASE CLASS
    public int threadedDelete(Calf calf) throws ExecutionException, InterruptedException {
        Future<Integer> integerFuture = CalfRoomDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return getCalfDao().delete(calf);
            }
        });
         int returnedFuture = integerFuture.get();
         return returnedFuture;
    }

    public Resource<Integer> deleteAllCalves(){
        try {
            int returnedValue = threadedDeleteAllCalves();
            Resource<Integer> resource = Resource.success(returnedValue);
            return resource;
        } catch (InterruptedException e) {
            Resource<Integer> resource = Resource.error(-1,"Error! Please try again");
            return resource;
        } catch (ExecutionException e) {
            Resource<Integer> resource = Resource.error(-1,"Error! Please try again");
            return resource;
        }

    }

    public int threadedDeleteAllCalves() throws ExecutionException, InterruptedException {
        Future<Integer> integerFuture = CalfRoomDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return getCalfDao().deleteAll();
            }
        });
        int returnedFuture = integerFuture.get(); // THE AMOUNT OF ROWS THAT ARE EFFECTED
        return returnedFuture;
    }


}

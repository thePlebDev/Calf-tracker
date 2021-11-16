package com.example.ecalvingtracker.util;

import android.os.Handler;
import android.os.Looper;

import com.elliottSoftware.ecalvingtracker.models.Calf;

import org.junit.Assert;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

public class LiveDataUtil {


    public Calf getValue(LiveData<List<Calf>> liveDataQuery) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final Calf[] data = new Calf[2];


        Observer<List<Calf>> observer = new Observer<List<Calf>>() {

            @Override
            public void onChanged(List<Calf> listLiveData) {
                latch.countDown(); // this releases all the thread
                data[0] = listLiveData.get(0);


            }
        };

        addObserver(liveDataQuery,observer);

        try{
            latch.await(2, TimeUnit.SECONDS); // if time elapses data.length will be 0

        } catch(InterruptedException ex){
            throw new InterruptedException("latch.await() failure");
        }

        if(data.length > 0){
            return  data[0];
        }else{
            return null;
        }

    }

    private void addObserver(LiveData<List<Calf>> liveDataQuery,Observer observer){
        Handler handler = new Handler(Looper.getMainLooper()); //main thread queue
        handler.post(new Runnable() { // task to run on the main thread queue
            @Override
            public void run() {
                liveDataQuery.observeForever(observer);
            }
        });

    }

    public void throwInterruptedException() throws InterruptedException{

        throw new InterruptedException("Simulated latch.await() failure");
    }
}

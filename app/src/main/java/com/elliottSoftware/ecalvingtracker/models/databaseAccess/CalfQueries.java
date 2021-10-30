package com.elliottSoftware.ecalvingtracker.models.databaseAccess;

import android.os.Bundle;

import com.elliottSoftware.ecalvingtracker.Views.fragments.NewCalfFragmentArgs;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.viewModels.CalfViewModel;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.ViewModelProvider;
/**
 * TODO: properly implement this class
 * **/
public class CalfQueries {
    private int calfId;
    private String sex;
    private String tagNumber;
    private String details;
    private String cciaNumber;

    private Date date;
    private CalfViewModel mCalfViewModel;

    private Calf calf;

    public CalfQueries(CalfViewModel calfViewModel, int calfId){
        this.mCalfViewModel = calfViewModel;
        this.calfId = calfId;

        retrieveCalfFromViewModel();

    }

    public void retrieveCalfFromViewModel(){


        //THIS IS THE ONLY THING THAT IS REALLY UNIQUE
        if(mCalfViewModel != null){

//            try {
//
//                this.calf = mCalfViewModel.getCalf(calfId); //getting the calf
//                this.tagNumber = this.calf.getTagNumber();
//                this.details = this.calf.getDetails();
//                this.cciaNumber = this.calf.getCciaNumber();
//                this.sex = this.calf.getSex();
//                this.date = this.calf.getDate();
//
//
//
//
//            } catch (ExecutionException e) {
//                //HOW SHOULD WE HANDLE EXCEPTIONS IN ANDROID
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }


        }

    }

    //GETTERS FOR ALL THE CALF INFORMATION
    public String getCalfTagNumber(){
        return calf.getTagNumber();
    }
    public String getCalfDetails(){
        return details;
    }
    public String getCalfCciaNumber(){
        return cciaNumber;
    }
    public String getCalfSex(){
        return sex;
    }
    public Date getCalfDate(){
        return date;
    }


}

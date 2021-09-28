package com.elliottSoftware.ecalvingtracker.fragments.fragmentUtils;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfViewModel;

import java.util.Date;

import androidx.fragment.app.FragmentActivity;

public class UpdateCalfInterfaceImplementation implements SaveCalfInterface{
    private View view;
    private CalfViewModel mCalfViewModel;
    private FragmentActivity activity;

    public UpdateCalfInterfaceImplementation(View view,CalfViewModel calfViewModel,FragmentActivity activity){
        //THIS CODE SHOULD BE REUSED
        this.view = view;
        this.mCalfViewModel = calfViewModel;
        this.activity = activity;

    }

    public boolean saveNewCalfInstance(String tagNumber, String description,
                                       String cciaNumber, String sex ) {
        //THIS CODE SHOULD BE REUSED

        if(tagNumber.trim().isEmpty()){
            Toast.makeText(activity, "Please enter a tag number", Toast.LENGTH_SHORT).show();
            return false;
        }
        Calf calf = new Calf(tagNumber,description,new Date(),sex,cciaNumber);
        // THIS IS THE ONLY UNIQUE CODE
        //THIS UPDATE NEEDS TO BE ABLE TO CHANGE
        mCalfViewModel.updateCalf(calf);
        return true;
    }




    public String checkButton(View v, RadioGroup radioGroup){
        //THIS CODE SHOULD BE REUSED
        int radioId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = v.findViewById(radioId);
        return radioButton.getText().toString();
    }

}

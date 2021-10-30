package com.elliottSoftware.ecalvingtracker.Views.fragments.fragmentUtils;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.viewModels.CalfViewModel;

import java.util.Date;

import androidx.fragment.app.FragmentActivity;

/**
 * NewCalfInterfaceImplementation is a class that implements the SaveCalfInterface and is used to
 * provide methods to help facilitate activities related to saving calf instances
 *
 * @author Tristan Elliott
 * @version 1.0
 * **/
public class NewCalfInterfaceImplementation  implements SaveCalfInterface {

    private View view;
    private CalfViewModel mCalfViewModel;
    private FragmentActivity activity;

    /**
     * Used to save a new Calf instance
     *
     * @param tagNumber  the tag number of the calf
     * @param description the description of the calf
     * @param cciaNumber  special number used by farmers for identification
     * @param sex the identifier for if a calf is a bull or heifer
     *
     * @return a boolean depending if a tag number was entered by a user or not
     * **/
    public boolean saveNewCalfInstance(String tagNumber, String description,
                         String cciaNumber, String sex ) {


        if(tagNumber.trim().isEmpty()){
            Toast.makeText(activity, "Please enter a tag number", Toast.LENGTH_SHORT).show();
            return false;
        }
        Calf calf = new Calf(tagNumber,description,new Date(),sex,cciaNumber);
        //This should be its own
        //mCalfViewModel.insert(calf);
        return true;
    }


    /**
     * Used to determine which radio button was clicked
     *
     * @param v the current view of the fragment
     * @param radioGroup the radioGroup surrounding the heifer and bull buttons
     *
     * @return a String representing the button that was clicked
     * **/
    public String checkButton(View v, RadioGroup radioGroup){
        int radioId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = v.findViewById(radioId);
        return radioButton.getText().toString();
    }


}

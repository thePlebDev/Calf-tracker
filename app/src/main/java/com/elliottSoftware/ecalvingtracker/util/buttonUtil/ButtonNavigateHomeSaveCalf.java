package com.elliottSoftware.ecalvingtracker.util.buttonUtil;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfViewModel;
import com.elliottSoftware.ecalvingtracker.util.snackbarUtil.SnackBarBase;
import com.example.ecalvingtracker.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

/**
 * Class made for navigating back to the main fragment and saving a new calf instance
 *
 * @author thePlebDev
 * **/
public class ButtonNavigateHomeSaveCalf extends ButtonNavigateHome{
    private EditText tagNumber;
    private EditText description;
    private EditText cciaNumber;
    private RadioButton femaleButton;
    private RadioButton maleButton;
    private String sex = "heifer";

    private CalfViewModel mCalfViewModel;

    private RadioGroup radioGroup;
    private SnackBarBase snackBarCreation;

    public ButtonNavigateHomeSaveCalf(View view, CalfViewModel activity){
        tagNumber = view.findViewById(R.id.edit_text_title);
        description = view.findViewById(R.id.edit_text_description);
        cciaNumber = view.findViewById(R.id.edit_text_cciaNumber);
        femaleButton = view.findViewById(R.id.radio_one);
        maleButton = view.findViewById(R.id.radio_two);
        radioGroup = view.findViewById(R.id.radioGroup);

         mCalfViewModel = activity;


        snackBarCreation = new SnackBarBase(); //THIS SHOULD BE INJECTED
        setHeiferBullButtonListeners();


    }


    @Override
    public void buttonAction(View view){
        if(!checkIfTagNumber()){
            createAndSaveCalf();
            super.buttonAction(view);
            snackBarCreation.createSnackbarCalfSaved(view,tagNumber.getText().toString());
        }else{
            //CREATE NEW CALF AND SAVE IT
            snackBarCreation.createSnackbar(view);


        }

    }
    public void createAndSaveCalf(){

        Calf calf = new Calf(tagNumber.getText().toString(),
                description.getText().toString(),new Date(),
                sex,cciaNumber.getText().toString());
        mCalfViewModel.insert(calf);
    }

    public boolean checkIfTagNumber(){
        String tagNumberText = tagNumber.getText().toString();
        String tagNumberTextNoSpaces = tagNumberText.replaceAll(" ","");
        boolean isTagNumberEmpty = tagNumberTextNoSpaces.isEmpty();

        return isTagNumberEmpty;
    }

    //BELOW IS ALL FOR THE SEX BUTTONS

    public void setHeiferBullButtonListeners(){
        femaleButton.setOnClickListener(this::setSex); //CHECK OUT WHAT THESE AND LAMBDAS ARE
        maleButton.setOnClickListener(this::setSex);

    }


    public String checkButton(View v, RadioGroup radioGroup){
        int radioId = radioGroup.getCheckedRadioButtonId(); //gets id of the clicked button
        RadioButton radioButton = v.findViewById(radioId); // finds the clicked button
        return radioButton.getText().toString(); // returns the string of the clicked button
    }

    public void setSex(View v){ //gets fired when the button is fired
        this.sex = checkButton(v, radioGroup);
    }



}

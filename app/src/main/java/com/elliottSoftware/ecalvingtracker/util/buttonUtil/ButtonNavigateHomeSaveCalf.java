package com.elliottSoftware.ecalvingtracker.util.buttonUtil;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.viewModels.CalfViewModel;
import com.elliottSoftware.ecalvingtracker.util.snackbarUtil.SnackBarBase;
import com.example.ecalvingtracker.R;

import java.util.Date;

/**
 * Class contains all the necessary parts for saving an instance of the calf model
 * and to navigate back to the MainFragment. It extends ButtonNavigateHome and overrides
 * its buttonAction() method to implement its own logic into the navigation functionality
 *
 * @author thePlebDev
 * **/
public class ButtonNavigateHomeSaveCalf extends ButtonNavigateHome{
    private EditText tagNumber;
    private EditText description;
    private EditText cciaNumber;
    private RadioButton femaleButton;
    private RadioButton maleButton;
    private String sex = "Heifer";

    private CalfViewModel mCalfViewModel;

    private RadioGroup radioGroup;
    private SnackBarBase snackBarCreation;

    private View testingView;

/**
 * The constructor for the class, it is used to find all the necessary view elements, setup the
 * ViewModel, Snackbar and the sexCheck buttons
 *
 * @param view the current view of the Fragment
 * @param viewModel the ViewModel to be used for saving Calf objects
 * **/
    public ButtonNavigateHomeSaveCalf(View view, CalfViewModel viewModel){
        tagNumber = view.findViewById(R.id.edit_text_title);
        description = view.findViewById(R.id.edit_text_description);
        cciaNumber = view.findViewById(R.id.edit_text_cciaNumber);
        femaleButton = view.findViewById(R.id.radio_one);
        maleButton = view.findViewById(R.id.radio_two);
        radioGroup = view.findViewById(R.id.radioGroup);

         mCalfViewModel = viewModel;

        snackBarCreation = new SnackBarBase(); //THIS SHOULD BE INJECTED?
        setHeiferBullButtonListeners();


    }

    /**
     * constructor to be called when there needs to be different action used for
     * the navigation component
     *
     *
     * **/
    public ButtonNavigateHomeSaveCalf(View view, CalfViewModel viewModel,int navigationAction){
        this(view,viewModel);
        super.setNavigationId(navigationAction);
    }

/**
 *  the method inherited from ButtonNavigateHome and will be called when the
 *  F.A.B is clicked. It will first check if there is a tagNumber. If there
 *  is not then it will display a snackBar, Otherwise it will navigate back to
 *  the Main fragment and display a appropriate snackBar
 *
 * @param view the View that is given to setOnClickListener
 * **/
//    @Override
//    public void buttonAction(View view){
//        if(!checkIfTagNumber()){
//            createAndSaveCalf();
//            super.buttonAction(view);//this navigates back to home
//            snackBarCreation.createSnackbarCalfSaved(view,tagNumber.getText().toString());
//        }else{
//            //CREATE NEW CALF AND SAVE IT
//            snackBarCreation.createSnackbar(view);
//
//
//        }
//
//    }
    /**
     * Called to create a Calf instance and save it via the appropriate ViewModel
     * **/
    public void createAndSaveCalf(){

        Calf calf = new Calf(tagNumber.getText().toString(),
                description.getText().toString(),new Date(),
                sex,cciaNumber.getText().toString());
        mCalfViewModel.insertCalf(calf);
    }

    /**
     * called to check if there is a tagNumber inside the tagNumber EditText
     * field
     *
     * @return boolean, determining if the tagNumber is empty or not
     * **/
    public boolean checkIfTagNumber(){
        String tagNumberText = tagNumber.getText().toString();
        String tagNumberTextNoSpaces = tagNumberText.replaceAll(" ","");
        boolean isTagNumberEmpty = tagNumberTextNoSpaces.isEmpty();

        return isTagNumberEmpty;
    }

    //BELOW IS ALL FOR THE SEX BUTTONS

    /**
     * used to set the onCLickListener for the heifer and bull buttons.
     * Object references are used, which are just syntactic sugar for
     * lambda expressions, which are just syntactic sugar for a anonymous
     * class that implements a View.onClickListener interface.
     * **/
    public void setHeiferBullButtonListeners(){
        femaleButton.setOnClickListener(this::setSex);
        //CHECK OUT WHAT THESE AND LAMBDAS ARE
        maleButton.setOnClickListener(this::setSex);

    }


    /**
     * called to set the string for sex
     *
     * @param v the current view of the Fragment
     * @param radioGroup the radio group related to the heifer and bull
     * buttons
     *
     * @return String it will be either heifer or bull
     * **/
    public String checkButton(View v, RadioGroup radioGroup){
        int radioId = radioGroup.getCheckedRadioButtonId(); //gets id of the clicked button
        RadioButton radioButton = v.findViewById(radioId); // finds the clicked button
        return radioButton.getText().toString(); // returns the string of the clicked button
    }

    /**
     * utility method called when the heifer and bull buttons are pressed
     *
     * @param v the current View of the Fragment
     * **/
    public void setSex(View v){ //gets fired when the button is fired
        this.sex = checkButton(v, radioGroup);
    }



}

package com.elliottSoftware.ecalvingtracker.util.buttonUtil;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.util.snackbarUtil.SnackBarBase;
import com.elliottSoftware.ecalvingtracker.viewModels.CalfViewModel;
import com.example.ecalvingtracker.R;

import java.util.Date;

/**
 * This class will be used in the decorator pattern to add the ability
 * for a button to save a Calf object.
 *
 * @author thePlebDev
 * **/
public class SaveCalf extends ButtonDecorator{
    private EditText tagNumber;
    private EditText description;
    private EditText cciaNumber;
    private RadioButton femaleButton;
    private RadioButton maleButton;
    private String sex = "Heifer";

    private CalfViewModel mCalfViewModel; // THIS WILL HAVE TO GET FIELD INJECTED

    private RadioGroup radioGroup;
    private SnackBarBase snackBarCreation;

    public SaveCalf(Button baseButton) {
        super(baseButton);
    }

    public SaveCalf(Button baseButton,CalfViewModel calfViewModel,View view){
        super(baseButton);
        this.mCalfViewModel = calfViewModel;
        setupView(view);
        setHeiferBullButtonListeners();
    }

    @Override
    public void onClick(View v) {
        if(IfNoTagNumber()){
            showSnackBar(v,"PLEASE ENTER TAG NUMBER");
        }else{
            super.onClick(v);
            createAndSaveCalf();
        }


    }

    //SET UP THE BUTTONS
    public void setupView(View view){
        tagNumber = view.findViewById(R.id.edit_text_title);
        description = view.findViewById(R.id.edit_text_description);
        cciaNumber = view.findViewById(R.id.edit_text_cciaNumber);
        femaleButton = view.findViewById(R.id.radio_one);
        maleButton = view.findViewById(R.id.radio_two);
        radioGroup = view.findViewById(R.id.radioGroup);
        snackBarCreation = new SnackBarBase();
    }



    public void showSnackBar(View view,String message){
        snackBarCreation.createSnackbar(view,message);
    }

  // BULL AND HEIFER BUTTON SETUP

    public void setHeiferBullButtonListeners(){
        femaleButton.setOnClickListener(this::setSex);
        //CHECK OUT WHAT THESE AND LAMBDAS ARE
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

  //CREATING AND SAVING THE CALF
  public void createAndSaveCalf(){

      Calf calf = new Calf(tagNumber.getText().toString(),
              description.getText().toString(),new Date(),
              sex,cciaNumber.getText().toString());
      mCalfViewModel.insertCalf(calf);
  }

  //Tag check
  public boolean IfNoTagNumber(){
      String tagNumberText = tagNumber.getText().toString();
      String tagNumberTextNoSpaces = tagNumberText.replaceAll(" ","");
      boolean isTagNumberEmpty = tagNumberTextNoSpaces.isEmpty();

      return isTagNumberEmpty;
  }



}

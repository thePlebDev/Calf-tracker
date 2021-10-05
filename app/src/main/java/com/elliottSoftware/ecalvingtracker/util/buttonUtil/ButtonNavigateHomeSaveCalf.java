package com.elliottSoftware.ecalvingtracker.util.buttonUtil;

import android.view.View;
import android.widget.EditText;

import com.elliottSoftware.ecalvingtracker.util.snackbarUtil.SnackBarBase;
import com.example.ecalvingtracker.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * Class made for navigating back to the main fragment and saving a new calf instance
 *
 * @author thePlebDev
 * **/
public class ButtonNavigateHomeSaveCalf extends ButtonNavigateHome{
    private EditText tagNumber;
    private EditText description;
    private EditText cciaNumber;
    private SnackBarBase snackBarCreation;

    public ButtonNavigateHomeSaveCalf(View view){
        tagNumber = view.findViewById(R.id.edit_text_title);
        description = view.findViewById(R.id.edit_text_description);
        cciaNumber = view.findViewById(R.id.edit_text_cciaNumber);
        snackBarCreation = new SnackBarBase();

    }


    @Override
    public void buttonAction(View view){
        if(checkIfTagNumber()){
            snackBarCreation.createSnackbar(view);
        }else{
            super.buttonAction(view);
            snackBarCreation.createSnackbarCalfSaved(view,tagNumber.getText().toString());
        }

    }

    public boolean checkIfTagNumber(){
        String tagNumberText = tagNumber.getText().toString();
        String tagNumberTextNoSpaces = tagNumberText.replaceAll(" ","");
        boolean isTagNumberEmpty = tagNumberTextNoSpaces.isEmpty();

        return isTagNumberEmpty;
    }

    public void setEditTextDetails(){

    }




}

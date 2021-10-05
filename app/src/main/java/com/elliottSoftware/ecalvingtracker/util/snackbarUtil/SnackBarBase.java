package com.elliottSoftware.ecalvingtracker.util.snackbarUtil;

import android.view.View;

import com.example.ecalvingtracker.R;
import com.google.android.material.snackbar.Snackbar;

public class SnackBarBase implements View.OnClickListener{

    public void createSnackbar(View view){
        Snackbar.make(view, R.string.tag_number,Snackbar.LENGTH_SHORT)
                .setAction(R.string.dismiss_tag_number, this).show();
    }

    public void createSnackbarCalfSaved(View view,String tagNumber){
        Snackbar.make(view,tagNumber + " saved",Snackbar.LENGTH_SHORT)
                .setAction(R.string.dismiss_tag_number, this).show();
    }

    @Override
    public void onClick(View v) {
        //If the method is left empty, the snack bar closes automatically
    }
}

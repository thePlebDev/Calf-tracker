package com.elliottSoftware.ecalvingtracker.util.snackbarUtil;

import android.view.View;

import com.example.ecalvingtracker.R;
import com.google.android.material.snackbar.Snackbar;
/**
 * Util base class to aid in the construction in SnackBars
 *
 * @author thePlebDev
 * **/
public class SnackBarBase implements View.OnClickListener{



    /**
     * Made to create a Snack bar stating to please enter a tag number
     *
     * @param view the current view of the fragment
     * **/
    public void createSnackbar(View view,String message){
        Snackbar.make(view, message,Snackbar.LENGTH_LONG)
                .setAction(R.string.dismiss_tag_number, this).show();
    }

    public void createSnackbarCalfUpdated(View view, String tagNumber){
        Snackbar.make(view, tagNumber + " updated",Snackbar.LENGTH_SHORT)
                .setAction(R.string.dismiss_tag_number, this).show();
    }

    public void createSnackbarCalfDeleted(View view){
        Snackbar.make(view, "Calf Deleted",Snackbar.LENGTH_SHORT)
                .setAction(R.string.dismiss_tag_number, this).show();
    }
    public void createSnackbarDeleteAllCalves(View view){
        Snackbar.make(view, "All calves deleted",Snackbar.LENGTH_SHORT)
                .setAction(R.string.dismiss_tag_number, this).show();
    }


    /**
     * Made to create a Snack bar notifying the user of a successful saving of the calf instance
     *
     * @param view the current view of the fragment
     * @param tagNumber the current tagNumber from the tagNumber EditText
     * **/
    public void createSnackbarCalfSaved(View view,String tagNumber){
        Snackbar.make(view,tagNumber + " saved",Snackbar.LENGTH_SHORT)
                .setAction(R.string.dismiss_tag_number, this).show();
    }

    /**
     * The implemented method from View.OnClickListener and will trigger when a button is pressed
     *
     * @param v the current View of the fragment
     * **/
    @Override
    public void onClick(View v) {
        //If the method is left empty, the snack bar closes automatically
    }
}

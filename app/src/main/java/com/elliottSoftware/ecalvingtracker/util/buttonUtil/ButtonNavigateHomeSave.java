package com.elliottSoftware.ecalvingtracker.util.buttonUtil;

import android.view.View;

public class ButtonNavigateHomeSave extends ButtonNavigateHome{

    public ButtonNavigateHomeSave(){
        //This method and the super() invocation is done automatically
        super();
    }

    @Override
    void buttonAction(View view){
        super.buttonAction(view); // call the original to navigate back to home.
        // we want the save stuff to be used in there, so probably in another method
    }
}

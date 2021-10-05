package com.elliottSoftware.ecalvingtracker.util.buttonUtil;

import android.view.View;
/**
 * Class made for navigating back to the main fragment and saving a new calf instance
 *
 * @author thePlebDev
 * **/
public class ButtonNavigateHomeSave extends ButtonNavigateHome{



    @Override
    public void buttonAction(View view){
        super.buttonAction(view); // call the original to navigate back to home.
        // we want the save stuff to be used in there, so probably in another method
    }


}

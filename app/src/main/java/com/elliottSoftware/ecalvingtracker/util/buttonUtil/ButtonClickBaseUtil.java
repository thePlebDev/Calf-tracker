package com.elliottSoftware.ecalvingtracker.util.buttonUtil;

import android.view.View;

/**
 * ButtonClickBaseUtil is  the abstract base class for all button clicks.
 * which allow classes to extend this base class and implement their own action
 *
 * @Author thePlebDev
 *
 * **/
public abstract class ButtonClickBaseUtil implements View.OnClickListener{

    /**
     * Performs an action related to a button click
     *
     * @param view the current view of the fragment
     * **/
    public abstract void buttonAction(View view);

    /**
     * Called when the button is click and runs the buttonAction(View view) method
     *
     * @param v the current view of the fragment
     * **/
    @Override
    public void onClick(View v) {
        buttonAction(v);
    }
}

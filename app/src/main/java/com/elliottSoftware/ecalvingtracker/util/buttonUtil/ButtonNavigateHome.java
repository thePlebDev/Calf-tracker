package com.elliottSoftware.ecalvingtracker.util.buttonUtil;

import android.view.View;

import com.example.ecalvingtracker.R;

import androidx.navigation.Navigation;
/**
 * Class for navigating back to the main fragment after a button is pressed
 *
 * @author thePlebDev
 * **/
public class ButtonNavigateHome extends ButtonClickBaseUtil{

    private int navigationId = R.id.action_newCalfFragment_to_mainFragment;

    /**
     * constructor to be called when the navigation to different fragment is needed
     *
     * @param navigationId the action to the desired destination
     * **/
    public ButtonNavigateHome(int navigationId){
        this.navigationId = navigationId;
    }

    /**
     * default constructor to be called when there is no need to change the
     * default navigationId
     * **/
    public ButtonNavigateHome(){

    }

    /**
     * When a button is click the user will navigate back to the main fragment
     * **/
    @Override
    public void buttonAction(View view) {
        Navigation.findNavController(view).navigate(navigationId);
    }

    /**
     * setter method to be called when there needs to a change to the
     * navigationId instance variable
     *
     * @param navigationId the action used by the navigation component to
     * navigate to the appropriate fragment
     * **/
    public void setNavigationId(int navigationId){
        this.navigationId = navigationId;
    }

}

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

    /**
     * When a button is click the user will navigate back to the main fragment
     * **/
    @Override
    public void buttonAction(View view) {
        Navigation.findNavController(view).navigate(R.id.action_newCalfFragment_to_mainFragment);
    }
}

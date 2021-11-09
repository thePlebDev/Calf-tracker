package com.elliottSoftware.ecalvingtracker.util.buttonUtil;

import android.view.View;

import com.example.ecalvingtracker.R;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class NavigateHome extends ButtonDecorator{
    public NavigateHome(Button baseButton) {
        super(baseButton);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        navigateHome(v);
    }

    public void navigateHome(View currentView){
        //HARDCODE FOR NOW. LATER SET WITH A FIELD INJECTION
        NavController navController = Navigation.findNavController(currentView); //THE VIEW THAT WAS CLICKED
        navController.navigate(R.id.action_newCalfFragment_to_mainFragment);

    }
}

package com.elliottSoftware.ecalvingtracker.util.buttonUtil;

import android.view.View;

import com.example.ecalvingtracker.R;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class NavHomeFromUpdate extends ButtonDecorator{
    private int navigationId = R.id.action_updateCalfFragment_to_mainFragment;


    public NavHomeFromUpdate(Button baseButton) {
        super(baseButton);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        navigate(v,navigationId);
    }

    public void navigate(View currentView,int navigationId){

        //HARDCODE FOR NOW. LATER SET WITH A FIELD INJECTION
        NavController navController = Navigation.findNavController(currentView); //THE VIEW THAT WAS CLICKED
        navController.navigate(navigationId);

    }

}

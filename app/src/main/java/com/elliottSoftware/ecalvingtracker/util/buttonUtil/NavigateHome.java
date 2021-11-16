package com.elliottSoftware.ecalvingtracker.util.buttonUtil;

import android.view.View;

import com.example.ecalvingtracker.R;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class NavigateHome extends ButtonDecorator{
    private int navigationId;

    public NavigateHome(Button baseButton) {
        super(baseButton);
    }

    public NavigateHome(Button baseButton,int navigationId){
        super(baseButton);
        this.navigationId = navigationId;
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

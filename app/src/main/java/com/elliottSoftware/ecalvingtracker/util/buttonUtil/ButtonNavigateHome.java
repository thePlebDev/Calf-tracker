package com.elliottSoftware.ecalvingtracker.util.buttonUtil;

import android.view.View;

import com.example.ecalvingtracker.R;

import androidx.navigation.Navigation;

public class ButtonNavigateHome extends ButtonClickBaseUtil{




    @Override
    void buttonAction(View view) {
        Navigation.findNavController(view).navigate(R.id.action_newCalfFragment_to_mainFragment);
    }
}

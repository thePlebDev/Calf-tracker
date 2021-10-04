package com.elliottSoftware.ecalvingtracker.util;

import android.view.View;

import com.example.ecalvingtracker.R;

import androidx.navigation.Navigation;

public class ButtonClickUtil implements View.OnClickListener {

    private void onButtonClick(View view){
        Navigation.findNavController(view).navigate(R.id.action_newCalfFragment_to_mainFragment);
    }

    @Override
    public void onClick(View v) {
        onButtonClick(v);
    }


}

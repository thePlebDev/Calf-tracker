package com.elliottSoftware.ecalvingtracker.util.buttonUtil;

import android.view.View;

public abstract class ButtonDecorator implements Button{
    private Button baseButton;

    public ButtonDecorator(Button baseButton){
        this.baseButton = baseButton;
    }

    @Override
    public void onClick(View v) {
        baseButton.onClick(v);
    }
}

package com.elliottSoftware.ecalvingtracker.util.buttonUtil;

import android.view.View;

public abstract class ButtonClickBaseUtil implements View.OnClickListener{
    abstract void buttonAction(View view);

    @Override
    public void onClick(View v) {
        buttonAction(v);
    }
}

package com.elliottSoftware.ecalvingtracker.fragments.fragmentUtils;

import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentActivity;

public interface SaveCalfInterface {


    public String checkButton(View v, RadioGroup radioGroup);

    public boolean saveNewCalfInstance(String tagNumber, String description, String cciaNumber, String sex);





}

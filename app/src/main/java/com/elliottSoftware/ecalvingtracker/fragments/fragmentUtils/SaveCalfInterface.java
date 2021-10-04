package com.elliottSoftware.ecalvingtracker.fragments.fragmentUtils;

import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentActivity;

/**
 * SaveCalfInterface is a interface for all classes that need to implement functionality related to
 * saving a calf instance to the Room database
 *
 * @version 1.0
 * **/
public interface SaveCalfInterface {

    /**
     *
     * **/
    public String checkButton(View v, RadioGroup radioGroup);

    public boolean saveNewCalfInstance(String tagNumber, String description, String cciaNumber, String sex);





}

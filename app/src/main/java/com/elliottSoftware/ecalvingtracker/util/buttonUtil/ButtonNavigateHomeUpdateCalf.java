package com.elliottSoftware.ecalvingtracker.util.buttonUtil;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.viewModels.CalfViewModel;
import com.example.ecalvingtracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

public class ButtonNavigateHomeUpdateCalf extends ButtonNavigateHome{
    private EditText updateTagNumber;
    private EditText updateTextDescription;
    private EditText updateCciaNumber;
    private RadioGroup updateRadioGroup;
    private RadioButton femaleButton;
    private RadioButton maleButton;
    private FloatingActionButton fabRight;
    private FloatingActionButton fabLeft;
    private int calfId;
    private String sex;
    private Date date;
    private CalfViewModel mCalfViewModel;


    public ButtonNavigateHomeUpdateCalf( int navComponentActionId){


    }



}

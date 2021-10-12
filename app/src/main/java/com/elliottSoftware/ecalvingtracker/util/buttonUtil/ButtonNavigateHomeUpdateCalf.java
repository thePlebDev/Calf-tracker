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

/**
 * Class contains all the necessary components to update the Calf instance and
 * navigate back to MainFragment it extends and inherits all of the fields from
 * ButtonHavigateHome
 *
 * - So I am thinking about having a third class that implements everything and
 * then have it take two classes that will be used for the FAB left and right buttons
 * - Is that what a factory is ?
 * **/
public class ButtonNavigateHomeUpdateCalf extends ButtonNavigateHome{
    private EditText updateTagNumber;
    private EditText updateTextDescription;
    private EditText updateCciaNumber;
    private RadioGroup updateRadioGroup;
    private RadioButton femaleButton;
    private RadioButton maleButton;
    /**
     * TODO: JUST HOOK THESE TWO UP FIRST
     * **/
    private FloatingActionButton fabRight;
    private FloatingActionButton fabLeft;
    private int calfId;
    private String sex;
    private Date date;
    private CalfViewModel mCalfViewModel;

    public ButtonNavigateHomeUpdateCalf(View view){
        this.fabLeft = view.findViewById(R.id.new_calf_fab_left);
    }


    public ButtonNavigateHomeUpdateCalf( int navComponentActionId){


    }



}

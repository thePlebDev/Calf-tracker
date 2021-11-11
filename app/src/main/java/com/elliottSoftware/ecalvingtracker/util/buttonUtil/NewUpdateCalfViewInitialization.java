package com.elliottSoftware.ecalvingtracker.util.buttonUtil;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ecalvingtracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**The job of this class is to initialize the shared view widgets
 * between the update and save calf instances. Also, provide methods
 * that allow the user of this class to retrieve and update those
 * shared widget values.
 * **/
public class NewUpdateCalfViewInitialization  implements View.OnClickListener{

/**
 * First lets just hook up tagNumber, details,cciaNumber
 * **/

    private EditText updateTagNumber;
    private EditText updateTextDescription;
    private EditText updateCciaNumber;
    private String sex;

    private RadioGroup updateRadioGroup;
    private RadioButton femaleButton;
    private RadioButton maleButton;

    private FloatingActionButton fabRight;
    private FloatingActionButton fabLeft;

    public NewUpdateCalfViewInitialization(View view){
        updateTagNumber = view.findViewById(R.id.edit_text_title);
        updateTextDescription = view.findViewById(R.id.edit_text_description);
        updateCciaNumber = view.findViewById(R.id.edit_text_cciaNumber);

        updateRadioGroup = view.findViewById(R.id.radioGroup);
        femaleButton = view.findViewById(R.id.radio_one);
        maleButton = view.findViewById(R.id.radio_two);

        fabRight = view.findViewById(R.id.new_calf_fab_right);
        fabLeft = view.findViewById(R.id.new_calf_fab_left);

        femaleButton.setOnClickListener(this::onClick);
        maleButton.setOnClickListener(this::onClick);

        fabLeft.setOnClickListener(
                new NavigateHome(
                        new BasicButton(),R.id.action_newCalfFragment_to_mainFragment)
        );


    }

    //GETTERS
    public String getUpdateTagNumber(){
        return this.updateTagNumber.getText().toString();
    }
    public String getUpdateTextDescription(){
        return this.updateTextDescription.getText().toString();
    }
    public String getUpdateCciaNumber(){
        return updateCciaNumber.getText().toString();
    }
    public String getSex(){
        return this.sex;
    }
    public RadioGroup getUpdateRadioGroup(){
        return this.updateRadioGroup;
    }
    public FloatingActionButton getFabRight(){
        return this.fabRight;
    }
    public FloatingActionButton getFabLeft(){
        return this.fabLeft;
    }
    //SETTERS
    public void setUpdateTagNumber(String tagNumber){
        this.updateTagNumber.setText(tagNumber);

    }
    public void setUpdateTextDescription(String description){
        this.updateTextDescription.setText(description);
    }
    public void setUpdateCciaNumber(String cciaNumber){
        this.updateCciaNumber.setText(cciaNumber);
    }
    public void setSex(String sex){
        this.sex = sex;
    }


    //UTILS
    public void togleFemaleButton(){
        femaleButton.toggle();
    }
    public void togleMaleButton(){
        maleButton.toggle();
    }

    public String checkButton(View v, RadioGroup radioGroup){
        //THIS CODE SHOULD BE REUSED
        int radioId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = v.findViewById(radioId);
        return radioButton.getText().toString();
    }

    @Override
    public void onClick(View v) {
        // DEPENDING ON WHAT BUTTON GETS CLICKED WE NEED TO ADD CHANGE THE SEX
        String sex =  checkButton(v,getUpdateRadioGroup());
        setSex(sex);

    }
}

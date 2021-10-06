package com.elliottSoftware.ecalvingtracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.elliottSoftware.ecalvingtracker.fragments.fragmentUtils.FragmentMenuUtil;
import com.elliottSoftware.ecalvingtracker.fragments.fragmentUtils.UpdateCalfInterfaceImplementation;
import com.example.ecalvingtracker.R;
import com.elliottSoftware.ecalvingtracker.fragments.NewCalfFragmentArgs;
import com.elliottSoftware.ecalvingtracker.fragments.fragmentUtils.SaveCalfInterface;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.models.CalfViewModel;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

/**
 * TODO REFACTOR THIS CUNT!!!!!!!!!!!!!!!!!!!!!!!!!
 * **/
public class UpdateCalfFragment extends FragmentMenuUtil implements View.OnClickListener{
    private EditText updateTagNumber;
    private EditText updateTextDescription;
    private EditText updateCciaNumber;
    private int calfId;
    private String sex;
    private Date date;

    private RadioGroup updateRadioGroup;

    private RadioButton femaleButton;
    private RadioButton maleButton;


    private SaveCalfInterface saveCalfUtil;
    private CalfViewModel mCalfViewModel;
    private View view;



    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //THE CONTAINER IS A FRAGMENTCONTAINERVIEW
        //since we provide false, FRAGMENTCONTAINERVIEW is used for proper LayoutParams
        View view = inflater.inflate(R.layout.new_calf_fragment,container,false);
        return view;
    }

    @Override
    public boolean saveCalfMenuButton() {

        String tagNumber = this.updateTagNumber.getText().toString();
        String description = this.updateTextDescription.getText().toString();
        String cciaNumber = this.updateCciaNumber.getText().toString();
        Date date = this.date;
        int calfId = this.calfId;

        // NEEDS THE ID TO UPDATE
        Calf calf = new Calf(calfId,tagNumber,description,date,sex,cciaNumber);

        mCalfViewModel.updateCalf(calf);

        //THIS SHOULD BE CHANGED OUT WE NEED A UPDATENEWCALFINSTANCE
        //boolean value = this.saveCalfUtil.saveNewCalfInstance(tagNumber,description,cciaNumber,sex);


        return true;
    }

    @Override
    public void navigate() {
        Navigation.findNavController(view).navigate(R.id.action_updateCalfFragment_to_mainFragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true); //states that this fragment wants to recieve appBar notifications

        //getActivity().setTitle(R.string.update_calf);

    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        // THIS MEHTOD IS WHERE ALL THE CHANGE OCCURS

        // THIS IS A DEPENDENCY THAT COULD BE INJECTED?
        mCalfViewModel = new ViewModelProvider(getActivity()).get(CalfViewModel.class);

        // THIS IS WHAT WE WANT TO MAKE REUSABLE
        this.saveCalfUtil = new UpdateCalfInterfaceImplementation(view,mCalfViewModel,getActivity());

        this.view = view;

        //THIS IS ALL THE SAME
        updateTagNumber = view.findViewById(R.id.edit_text_title);
        updateTextDescription = view.findViewById(R.id.edit_text_description);
        updateCciaNumber = view.findViewById(R.id.edit_text_cciaNumber);
        updateRadioGroup = view.findViewById(R.id.radioGroup);
        femaleButton = view.findViewById(R.id.radio_one);
        maleButton = view.findViewById(R.id.radio_two);
        femaleButton.setOnClickListener(this);
        maleButton.setOnClickListener(this);



        this.calfId = NewCalfFragmentArgs.fromBundle(getArguments()).getCalfId();
        //WE NEED TO MAKE A DATABASE CALL TO GET THE CALFID
        //So this should really be its own thing

        //THIS IS THE ONLY THING THAT IS REALLY UNIQUE
        if(getArguments().isEmpty() == false){
                //we should use composition for this
            // THIS IS UNIQUE TO THIS CLASS

            try {

                Calf calf  = mCalfViewModel.getCalf(calfId);
                //This is all the information we need plus the calf id

               this.sex = calf.getSex();
               this.date = calf.getDate();

                sexCheck(sex);

                // this sets all of the information
               updateTagNumber.setText(calf.getTagNumber());
               updateTextDescription.setText(calf.getDetails());
               updateCciaNumber.setText(calf.getCciaNumber());


            } catch (ExecutionException e) {
                //HOW SHOULD WE HANDLE EXCEPTIONS IN ANDROID
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }

    public void sexCheck(String sex){
        // THIS IS UNIQUE TO THIS CLASS

        if(sex.equals("Heifer")){
            femaleButton.toggle();
        }else{
            maleButton.toggle();
        }
    }



    @Override
    public void onClick(View v) {
        // DEPENDING ON WHAT BUTTON GETS CLICKED WE NEED TO ADD CHANGE THE SEX
                this.sex = saveCalfUtil.checkButton(v,updateRadioGroup);

    }
}

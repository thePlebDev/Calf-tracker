package com.elliottSoftware.ecalvingtracker.Views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.elliottSoftware.ecalvingtracker.Views.fragments.fragmentUtils.UpdateCalfInterfaceImplementation;
import com.elliottSoftware.ecalvingtracker.models.databaseAccess.CalfQueries;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.ButtonNavigateHome;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.ButtonNavigateHomeSaveCalf;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.ButtonNavigateHomeUpdateCalf;
import com.elliottSoftware.ecalvingtracker.util.snackbarUtil.SnackBarBase;
import com.example.ecalvingtracker.R;
import com.elliottSoftware.ecalvingtracker.Views.fragments.fragmentUtils.SaveCalfInterface;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.viewModels.CalfViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

/**
 * TODO REFACTOR THIS CUNT!!!!!!!!!!!!!!!!!!!!!!!!!
 * **/
public class UpdateCalfFragment extends Fragment implements View.OnClickListener{
    private EditText updateTagNumber;
    private EditText updateTextDescription;
    private EditText updateCciaNumber;
    private int calfId;
    private String sex;
    private Date date;

    private RadioGroup updateRadioGroup;

    private RadioButton femaleButton;
    private RadioButton maleButton;

    private FloatingActionButton fabRight;
    private FloatingActionButton fabLeft;


    private SaveCalfInterface saveCalfUtil;
    private CalfViewModel mCalfViewModel;
    private View view;

    private SnackBarBase snackBarCreation;



    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //THE CONTAINER IS A FRAGMENTCONTAINERVIEW
        //since we provide false, FRAGMENTCONTAINERVIEW is used for proper LayoutParams
        View view = inflater.inflate(R.layout.new_calf_fragment,container,false);
        return view;
    }

    /**
     * used to save a new calf instance
     * TODO change this over to the new buttons
     * **/
    public void saveCalfMenuButton(View view) {

        String tagNumber = this.updateTagNumber.getText().toString();
        String description = this.updateTextDescription.getText().toString();
        String cciaNumber = this.updateCciaNumber.getText().toString();
        Date date = this.date;
        int calfId = this.calfId;

        // NEEDS THE ID TO UPDATE
        Calf calf = new Calf(calfId,tagNumber,description,date,sex,cciaNumber);

        mCalfViewModel.updateCalf(calf);

        Navigation.findNavController(view).navigate(R.id.action_updateCalfFragment_to_mainFragment);
        snackBarCreation.createSnackbarCalfUpdated(view,tagNumber);




    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);



    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        snackBarCreation = new SnackBarBase();
        // THIS IS A DEPENDENCY THAT COULD BE INJECTED?
        mCalfViewModel = new ViewModelProvider(getActivity()).get(CalfViewModel.class);
        this.calfId = NewCalfFragmentArgs.fromBundle(getArguments()).getCalfId();

        // THIS IS WHAT WE WANT TO MAKE REUSABLE
        /**
         * TODO: fucking kill this saveCalfUtil B.S
         * **/
        this.saveCalfUtil = new UpdateCalfInterfaceImplementation(view,mCalfViewModel,getActivity());


        this.view = view;

        //WE MIGHT BE ABLE TO MOVE THIS ALL OVER TO THE ButtonNavigationHomeSaveCalf class
        updateTagNumber = view.findViewById(R.id.edit_text_title);
        updateTextDescription = view.findViewById(R.id.edit_text_description);
        updateCciaNumber = view.findViewById(R.id.edit_text_cciaNumber);
        updateRadioGroup = view.findViewById(R.id.radioGroup);

        femaleButton = view.findViewById(R.id.radio_one);
        maleButton = view.findViewById(R.id.radio_two);
        femaleButton.setOnClickListener(this);
        maleButton.setOnClickListener(this);



        fabRight = view.findViewById(R.id.new_calf_fab_right);
        fabLeft = view.findViewById(R.id.new_calf_fab_left);
//
        fabLeft.setOnClickListener(new ButtonNavigateHome(R.id.action_updateCalfFragment_to_mainFragment));

        fabRight.setOnClickListener(this::saveCalfMenuButton);




        /**
         *
         * TODO: refactor into own class once we have more methods
         * **/
        retrieveCalfFromViewModel();

    }

    /**
     * used to determine what button should be pressed when the
     * retrieved calf is displayed
     * **/
    public void sexCheck(String sex){
        // THIS IS UNIQUE TO THIS CLASS

        if(sex.equals("Heifer")){
            femaleButton.toggle();
        }else{
            maleButton.toggle();
        }
    }



    /**
     * TODO: GET RID OF  saveCalfUtil
     * inherited from View.OnClickListener and is used to check which
     * button is pressed.
     *
     * **/
    @Override
    public void onClick(View v) {
        // DEPENDING ON WHAT BUTTON GETS CLICKED WE NEED TO ADD CHANGE THE SEX
                this.sex = saveCalfUtil.checkButton(v,updateRadioGroup);

    }


    /**
     * TODO: MAKE THIS INTO ITS OWN CLASS BEFORE DOING ANYTHING ELSE
     * **/
    public void retrieveCalfFromViewModel(){
        this.calfId = NewCalfFragmentArgs.fromBundle(getArguments()).getCalfId();
        //WE NEED TO MAKE A DATABASE CALL TO GET THE CALFID
        //THIS SHOULD REALLY IN ITS OWN CLASS

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

}

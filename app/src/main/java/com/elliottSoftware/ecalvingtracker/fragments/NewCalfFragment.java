package com.elliottSoftware.ecalvingtracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.elliottSoftware.ecalvingtracker.fragments.fragmentUtils.NewCalfInterfaceImplementation;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.ButtonNavigateHome;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.ButtonNavigateHomeSaveCalf;
import com.example.ecalvingtracker.R;
import com.elliottSoftware.ecalvingtracker.fragments.fragmentUtils.SaveCalfInterface;
import com.elliottSoftware.ecalvingtracker.models.CalfViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class NewCalfFragment extends Fragment implements View.OnClickListener {

    private FloatingActionButton fabRight;
    private FloatingActionButton fabLeft;
    private String sex = "Heifer";


    private RadioGroup radioGroup;

    private CalfViewModel mCalfViewModel;
    private View view;

    //WITH THIS WE ARE EXPLOTING POLYMORPHISM TO HANDLE ALL THE NEW CALF SPECIFIC CALLS
    private SaveCalfInterface newCalfUtil;
    //SHOULD HAVE ANOTHER CLASS TO HANDLE THE SHARED METHODS

    public NewCalfFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //THE CONTAINER IS A FRAGMENTCONTAINERVIEW
        //since we provide false, FRAGMENTCONTAINERVIEW is used for proper LayoutParams
        View view = inflater.inflate(R.layout.new_calf_fragment,container,false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        radioGroup = view.findViewById(R.id.radioGroup);
        fabRight = view.findViewById(R.id.new_calf_fab_right);
        fabLeft = view.findViewById(R.id.new_calf_fab_left);

        //THIS MIGHT BE A DEPENDENCY INJECTION
        mCalfViewModel = new ViewModelProvider(getActivity()).get(CalfViewModel.class);
        this.newCalfUtil = new NewCalfInterfaceImplementation(view,mCalfViewModel,getActivity());

        this.view = view;


        //THE BUTTON STUFF STAYS HERE
        RadioButton femaleButton = view.findViewById(R.id.radio_one);
        RadioButton maleButton = view.findViewById(R.id.radio_two);
        femaleButton.setOnClickListener(this);
        maleButton.setOnClickListener(this);

        //ADD THE CLICKS EVENTS TO THE FABs
        fabRight.setOnClickListener(new ButtonNavigateHomeSaveCalf(view));

        fabLeft.setOnClickListener(new ButtonNavigateHome());



    }



    /**THIS CAN GET REMOVED TO MY BUTTON UTILS**/
    @Override
    public void onClick(View v) {

        this.sex = newCalfUtil.checkButton(v,radioGroup);
    }

    //@Override
    public boolean saveCalfMenuButton() {
        //THIS METHOD RUNS THE SAVE METHOD INSIDE WHEN THE SAVE BUTTON IS CLICKED


        //boolean value = newCalfUtil.saveNewCalfInstance(tagNumber,description,cciaNumber,sex);

        return true;
    }


}

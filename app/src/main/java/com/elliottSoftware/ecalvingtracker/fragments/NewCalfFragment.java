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

public class NewCalfFragment extends Fragment {

    private FloatingActionButton fabRight;
    private FloatingActionButton fabLeft;

    private CalfViewModel mCalfViewModel;
    private View view;


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

        fabRight = view.findViewById(R.id.new_calf_fab_right);
        fabLeft = view.findViewById(R.id.new_calf_fab_left);

        //THIS MIGHT BE A DEPENDENCY INJECTION
        mCalfViewModel = new ViewModelProvider(getActivity()).get(CalfViewModel.class);


        this.view = view;



        //ADD THE CLICKS EVENTS TO THE FABs
        fabRight.setOnClickListener(new ButtonNavigateHomeSaveCalf(view,mCalfViewModel));
        fabLeft.setOnClickListener(new ButtonNavigateHome());



    }






}

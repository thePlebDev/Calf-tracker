package com.elliottSoftware.ecalvingtracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.elliottSoftware.ecalvingtracker.fragments.fragmentUtils.FragmentMenuUtil;
import com.elliottSoftware.ecalvingtracker.fragments.fragmentUtils.NewCalfInterfaceImplementation;
import com.elliottSoftware.ecalvingtracker.util.ButtonClickUtil;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.ButtonNavigateHome;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.ButtonNavigateHomeSave;
import com.example.ecalvingtracker.R;
import com.elliottSoftware.ecalvingtracker.fragments.fragmentUtils.SaveCalfInterface;
import com.elliottSoftware.ecalvingtracker.models.CalfViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

public class NewCalfFragment extends Fragment implements View.OnClickListener {
    private EditText editTagNumber;
    private EditText editTextDescription;
    private EditText editTextCCIANumber;
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
        editTagNumber = view.findViewById(R.id.edit_text_title);
        editTextDescription = view.findViewById(R.id.edit_text_description);
        editTextCCIANumber = view.findViewById(R.id.edit_text_cciaNumber);
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
        fabRight.setOnClickListener(new ButtonNavigateHomeSave());

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
        String tagNumber = this.editTagNumber.getText().toString();
        String description = this.editTextDescription.getText().toString();
        String cciaNumber = this.editTextCCIANumber.getText().toString();

        boolean value = newCalfUtil.saveNewCalfInstance(tagNumber,description,cciaNumber,sex);

        return value;
    }


}

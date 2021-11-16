package com.elliottSoftware.ecalvingtracker.Views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;
import com.elliottSoftware.ecalvingtracker.repositories.CalfRepository;
import com.elliottSoftware.ecalvingtracker.util.Resource;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.BasicButton;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.Button;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.NavHomeFromUpdate;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.NavigateHome;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.NewUpdateCalfViewInitialization;
import com.elliottSoftware.ecalvingtracker.util.snackbarUtil.SnackBarBase;
import com.example.ecalvingtracker.R;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.viewModels.CalfViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

/**
 * TODO: transfer all the shared widgets over to NewUpdateCalfViewInitialization
 * **/
public class UpdateCalfFragment extends Fragment{

    private int calfId;//not shared
    private Date date; //not shared

    private CalfViewModel mCalfViewModel; //possibly shared

    private SnackBarBase snackBarCreation;
    private NewUpdateCalfViewInitialization newUpdateCalfViewInitialization;

    private FloatingActionButton fabLeft;


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //THE CONTAINER IS A FRAGMENTCONTAINERVIEW
        //since we provide false, FRAGMENTCONTAINERVIEW is used for proper LayoutParams
        View view = inflater.inflate(R.layout.new_calf_fragment,container,false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        snackBarCreation = new SnackBarBase();
        // THIS IS A DEPENDENCY THAT COULD BE INJECTED?
        CalfRepository repository = new CalfRepository(CalfRoomDatabase.getDatabase(getActivity().getApplicationContext()).getCalfDao());
        mCalfViewModel = new CalfViewModel(repository);
        this.calfId = NewCalfFragmentArgs.fromBundle(getArguments()).getCalfId();

        this.newUpdateCalfViewInitialization = new NewUpdateCalfViewInitialization(view);

        //ASYNC DATA CALL
       Resource<Calf> retrievedCalf =  mCalfViewModel.getCalf(calfId);

        // THIS IS WHAT WE WANT TO MAKE REUSABLE
        this.calfId = NewCalfFragmentArgs.fromBundle(getArguments()).getCalfId();


        //BUTTON SET UP
        fabLeft = view.findViewById(R.id.new_calf_fab_left);

        fabLeft.setOnClickListener(new NavHomeFromUpdate(new BasicButton()));



        newUpdateCalfViewInitialization.getFabRight().setOnClickListener(this::saveCalfMenuButton);



        /**
         *
         * methods to get calf data
         * **/
        newUpdateCalfViewInitialization.setSex(retrievedCalf.getData().getSex());
       this.date = retrievedCalf.getData().getDate();

        newUpdateCalfViewInitialization.setUpdateTagNumber(retrievedCalf.getData().getTagNumber());
        newUpdateCalfViewInitialization.setUpdateCciaNumber(retrievedCalf.getData().getCciaNumber());
        newUpdateCalfViewInitialization.setUpdateTextDescription(retrievedCalf.getData().getDetails());

        sexCheck(newUpdateCalfViewInitialization.getSex());


    }

    /**
     * THIS METHOD IS NOT SHARED
     * used to determine what button should be pressed when the
     * retrieved calf is displayed
     *
     * **/
    public void sexCheck(String sex){
        // THIS IS UNIQUE TO THIS CLASS

        if(sex.equals("Heifer")){
            newUpdateCalfViewInitialization.togleFemaleButton();
        }else{
            newUpdateCalfViewInitialization.togleMaleButton();
        }
    }

    /**
     * used to update a  calf instance
     * TODO change this over to the new buttons
     * **/
    public void saveCalfMenuButton(View view) {

        String tagNumber = newUpdateCalfViewInitialization.getUpdateTagNumber();
        String description = newUpdateCalfViewInitialization.getUpdateTextDescription();
        String cciaNumber = newUpdateCalfViewInitialization.getUpdateCciaNumber();
        String sex = newUpdateCalfViewInitialization.getSex();

        Date date = this.date;
        int calfId = this.calfId;

        // NEEDS THE ID TO UPDATE
        Calf calf = new Calf(calfId,tagNumber,description,date,sex,cciaNumber);

        mCalfViewModel.updateCalf(calf);

        Navigation.findNavController(view).navigate(R.id.action_updateCalfFragment_to_mainFragment);
        snackBarCreation.createSnackbarCalfUpdated(view,tagNumber);

    }
}

package com.elliottSoftware.ecalvingtracker.Views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;
import com.elliottSoftware.ecalvingtracker.models.databaseAccess.CalfQueries;
import com.elliottSoftware.ecalvingtracker.repositories.CalfRepository;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.NewUpdateCalfViewInitialization;
import com.elliottSoftware.ecalvingtracker.util.snackbarUtil.SnackBarBase;
import com.example.ecalvingtracker.R;
import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.viewModels.CalfViewModel;

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
    private CalfQueries calfQueries;
    private NewUpdateCalfViewInitialization newUpdateCalfViewInitialization;



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
        this.calfQueries = new CalfQueries(mCalfViewModel,this.calfId);
        this.newUpdateCalfViewInitialization = new NewUpdateCalfViewInitialization(view);

        // THIS IS WHAT WE WANT TO MAKE REUSABLE

        this.calfId = NewCalfFragmentArgs.fromBundle(getArguments()).getCalfId();



        newUpdateCalfViewInitialization.getFabRight().setOnClickListener(this::saveCalfMenuButton);



        /**
         *
         * methods to get calf data
         * **/
        newUpdateCalfViewInitialization.setSex(calfQueries.getCalfSex());
        this.date = calfQueries.getCalfDate();

        newUpdateCalfViewInitialization.setUpdateTagNumber(calfQueries.getCalfTagNumber());
        newUpdateCalfViewInitialization.setUpdateCciaNumber(calfQueries.getCalfCciaNumber());
        newUpdateCalfViewInitialization.setUpdateTextDescription(calfQueries.getCalfDetails());

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

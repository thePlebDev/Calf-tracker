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

import androidx.navigation.Navigation;

/**
 * this classes job is handle all the necessary business logic that is responsible for
 * updating the calf
 * **/
public class ButtonNavigateHomeUpdateCalf extends ButtonNavigateHome{



    public ButtonNavigateHomeUpdateCalf(){
        super(R.id.action_updateCalfFragment_to_mainFragment);// navigates back to the Main Fragment

    }

    /**
     * this method will get called when the button is clicked
     * **/
    @Override
    public void buttonAction(View view) {

    }


    public ButtonNavigateHomeUpdateCalf( int navComponentActionId){


    }

    /**
     * TODO: FIGURE OUT HOW THIS CAN BE REFACTORED
     * **/
//    public void saveCalfMenuButton(View view) {
//
//        String tagNumber = newUpdateCalfViewInitialization.getUpdateTagNumber();
//        String description = newUpdateCalfViewInitialization.getUpdateTextDescription();
//        String cciaNumber = newUpdateCalfViewInitialization.getUpdateCciaNumber();
//        String sex = newUpdateCalfViewInitialization.getSex();
//
//        Date date = this.date;
//        int calfId = this.calfId;
//
//        // NEEDS THE ID TO UPDATE
//        Calf calf = new Calf(calfId,tagNumber,description,date,sex,cciaNumber);
//
//        mCalfViewModel.updateCalf(calf);
//
//        Navigation.findNavController(view).navigate(R.id.action_updateCalfFragment_to_mainFragment);
//        snackBarCreation.createSnackbarCalfUpdated(view,tagNumber);
//
//
//
//
//    }


}

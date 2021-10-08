package com.elliottSoftware.ecalvingtracker.Views.fragments.fragmentUtils;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.ecalvingtracker.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public abstract class FragmentMenuUtil extends Fragment {
    //THIS CLASS SHOULD HANDLE ALL THE MENU STUFF

    //Abstract method for saveCalf and navigate
    // I DONT CARE WHAT THESE METHODS DO, I JUST WANT YOU TO RUN THEM
    //SAVE CALF SHOULD BE SHARED
    public abstract boolean saveCalfMenuButton();
    public abstract void navigate();

    private int menuId = R.menu.add_calf_menu;

    //I THINK WE WANT SAVE BEHAVIOR IN HERE
    // BUT HOW TO DO SO WITHOUT ALL THE VARIABLES????

    public void setMapId(int id){
        //THIS WORKS BUT WHAT HAPPENS IF SOMEONE FORGETS TO SET IT.
        //BUT IT DOES GIVE US THE ABILITY TO DYNAMICALLY UPDATE THE MEMU
        this.menuId = id;
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //TELLING THIS CLASS TO RECIEVE MENU EVENTS
        setHasOptionsMenu(true);
        getActivity().setTitle("Elliott Tracker");
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.save_calf:{
                if(saveCalfMenuButton() == true){
                    navigate();
                }
                return true;
            }
            case R.id.close_calf:
                navigate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    // I think we should pass the map to its own class
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater){
        //WE SHOULD THINK ABOUT CHANGING THIS TO BE DYNAMICALLY ADDED SOMEHOW
        inflater.inflate(menuId,menu);

    }



}

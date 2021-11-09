package com.elliottSoftware.ecalvingtracker.Views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;
import com.elliottSoftware.ecalvingtracker.repositories.CalfRepository;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.BasicButton;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.ButtonNavigateHome;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.ButtonNavigateHomeSaveCalf;
import com.elliottSoftware.ecalvingtracker.util.buttonUtil.NavigateHome;
import com.example.ecalvingtracker.R;
import com.elliottSoftware.ecalvingtracker.viewModels.CalfViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

/**
 * base class used for implementing all initial set up for the
 * new_calf_fragment
 *
 * @author thePlebDev
 * @version 2
 * **/
public class NewCalfFragment extends Fragment {

    private FloatingActionButton fabRight;
    private FloatingActionButton fabLeft;
    private CalfViewModel mCalfViewModel;
    private View view;


    public NewCalfFragment(){
    }

    /**
     * called after onAttach() and when the fragment enters the
     * CREATED state in its lifecycle.
     *
     * @param savedInstanceState contains any previously saved state,
     * if there is none, it if null
     * **/
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // don't think I need this
    }

    /**
     * called after onCreate() and is used ONLY for inflating the view
     *
     * @param inflater the LayoutInflater object that can be used
     * to inflate any views in the fragment
     * @param container if non-null, this is the parent view that the
     * fragment's ui should be attached to
     * @param savedInstanceState if non-null, this contains any previously
     * saved state
     *
     * @return View, will be the inflated view that will be shown to the
     * user
     * **/
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //THE CONTAINER IS A FRAGMENTCONTAINERVIEW
        //since we provide false, FRAGMENTCONTAINERVIEW is used for proper LayoutParams
        View view = inflater.inflate(R.layout.new_calf_fragment,container,false);
        return view;
    }


    /**
     * called immediately after onCreateView has returned but before any
     * saved state has been restored into the view. it is used for any
     * logic pertaining to the set up of the fragment
     *
     * @param view the view returned by onCreateView()
     * @param savedInstanceState if non-null, this is any previously
     * saved state.
     * **/
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        fabRight = view.findViewById(R.id.new_calf_fab_right);
        fabLeft = view.findViewById(R.id.new_calf_fab_left);
        //THIS MIGHT BE A DEPENDENCY INJECTION
        CalfRepository repository = new CalfRepository(CalfRoomDatabase.getDatabase(getActivity().getApplicationContext()).getCalfDao());
        mCalfViewModel = new CalfViewModel(repository);

        this.view = view;


        //THIS MIGHT BE A DEPENDENCY INJECTION
        //ADD THE CLICKS EVENTS TO THE FABs
        fabRight.setOnClickListener(new ButtonNavigateHomeSaveCalf(view,mCalfViewModel));

        fabLeft.setOnClickListener(new NavigateHome(new BasicButton()));

        //TURNING THE VIEW INVISIBLE
        view.findViewById(R.id.indeterminateBar).setVisibility(View.INVISIBLE);




    }

}

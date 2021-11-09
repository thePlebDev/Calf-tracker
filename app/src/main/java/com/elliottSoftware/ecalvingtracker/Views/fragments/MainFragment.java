package com.elliottSoftware.ecalvingtracker.Views.fragments;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.elliottSoftware.ecalvingtracker.models.CalfRoomDatabase;
import com.elliottSoftware.ecalvingtracker.repositories.CalfRepository;
import com.elliottSoftware.ecalvingtracker.util.snackbarUtil.SnackBarBase;
import com.example.ecalvingtracker.R;
import com.elliottSoftware.ecalvingtracker.viewModels.CalfViewModel;
import com.elliottSoftware.ecalvingtracker.Views.adapters.CalfListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/**
 * MainFragment is a class for that holds all the contents related to the fragment
 * that gets initially viewed upon app launch
 *
 * @author thePlebDev
 * **/
public class MainFragment extends Fragment implements CalfListAdapter.OnCalfListener{
    private RecyclerView recyclerView;
    private CalfViewModel mCalfViewModel;

    private SnackBarBase snackBarCreation;
    private View Globalview;

    public MainFragment(){
        super(R.layout.main_fragmenr);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//THis fragment wants to recieve menu related callbacks
        getActivity().setTitle(R.string.app_name);
    }

    /**
     * TODO: DELETE OR MOVE TO SUPER CLASS. I DON'T WANT MENU METHODS IN THIS CLASS
     * **/
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater){
        inflater.inflate(R.menu.main_menu,menu);
    }

    /**
     * TODO: DELETE OR MOVE TO SUPER CLASS. I DON'T WANT MENU METHODS IN THIS CLASS
     * **/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case R.id.delete_all:{
                mCalfViewModel.deleteAll();
                snackBarCreation.createSnackbarDeleteAllCalves(Globalview);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Called before any saved state has been restored in to the view. It will hold all the
     * logic pertaining to the view
     *
     * @param view the View returned from onCreateView
     * @param savedInstanceState previously saved state
     * **/
    public void onViewCreated (View view, Bundle savedInstanceState){
        Globalview = view;
        final CalfListAdapter adapter = new CalfListAdapter(new CalfListAdapter.CalfDiff(),this);
        snackBarCreation = new SnackBarBase();
        CalfRepository repository = new CalfRepository(CalfRoomDatabase.getDatabase(getActivity().getApplicationContext()).getCalfDao());
        mCalfViewModel = new CalfViewModel(repository);

        mCalfViewModel.getAllCalves().observe(getViewLifecycleOwner(),calves -> {
            //update the cached copy of the words in the adapter
            //setting the data inside of our adapter
            adapter.submitList(calves);
        });

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //THE DELETE SWIPE SHOULD GO HERE
        //HAS TO BE BELOW THE RECYCLERVIEW SETTING
        touchHelper(adapter);



        /**
         * Used to handle the the FAB click logic and navigate to the next fragment
         * **/
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_newCalfFragment);
            }
        });

    }

    /**
     * Used to handle the navigation logic for the Navigation Component
     *
     * @param calfId the calf's Id which will be used for a sql query for retrieval.
     * **/
    @Override
    public void onCalfClick(int calfId) {
        //THIS IS WHERE THE NAVIGATION IS DONE.
        //WE ARE PASSING IT THE CALFID

        MainFragmentDirections.ActionMainFragmentToUpdateCalfFragment action =
                MainFragmentDirections.actionMainFragmentToUpdateCalfFragment(calfId);

        NavHostFragment.findNavController(this).navigate(action);

    }

    /**
     * Used to add the swipe to delete functionality
     * **/
    public void touchHelper(CalfListAdapter adapter){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull  RecyclerView.ViewHolder viewHolder, @NonNull  RecyclerView.ViewHolder target) {
                //THIS IS FOR DRAG AND DROP FUNCTIONALITY WHICH WE WILL NOT BE USING
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mCalfViewModel.delete(adapter.getCalfAt(viewHolder.getAdapterPosition()));
                snackBarCreation.createSnackbarCalfDeleted(Globalview);

            }
        }).attachToRecyclerView(recyclerView);
    }

}

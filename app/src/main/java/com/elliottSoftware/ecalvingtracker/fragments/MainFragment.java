package com.elliottSoftware.ecalvingtracker.fragments;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ecalvingtracker.R;
import com.elliottSoftware.ecalvingtracker.fragments.MainFragmentDirections;
import com.elliottSoftware.ecalvingtracker.models.CalfViewModel;
import com.elliottSoftware.ecalvingtracker.models.adapters.CalfListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends Fragment implements CalfListAdapter.OnCalfListener{
    private RecyclerView recyclerView;
    private CalfViewModel mCalfViewModel;

    public MainFragment(){
        super(R.layout.main_fragmenr);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//THis fragment wants to recieve menu related callbacks
        getActivity().setTitle(R.string.app_name);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater){
        inflater.inflate(R.menu.main_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case R.id.delete_all:{
                mCalfViewModel.deleteAll();
                Toast.makeText(getActivity(),"DELETED ALL CALVES", Toast.LENGTH_SHORT).show();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void onViewCreated (View view, Bundle savedInstanceState){
        final CalfListAdapter adapter = new CalfListAdapter(new CalfListAdapter.CalfDiff(),this);

        //getActivity() MIGHT HAVE TO BE CHANGED TO this
        mCalfViewModel = new ViewModelProvider(getActivity()).get(CalfViewModel.class);
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
            Toast.makeText(getActivity(),"Calf deleted",Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);



        //SETTING THE BUTTON
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_newCalfFragment);
            }
        });

    }

    @Override
    public void onCalfClick(int calfId) {
        //THIS IS WHERE THE NAVIGATION IS DONE.
        //WE ARE PASSING IT THE CALFID

        MainFragmentDirections.ActionMainFragmentToUpdateCalfFragment action =
                MainFragmentDirections.actionMainFragmentToUpdateCalfFragment(calfId);

        NavHostFragment.findNavController(this).navigate(action);

    }
}

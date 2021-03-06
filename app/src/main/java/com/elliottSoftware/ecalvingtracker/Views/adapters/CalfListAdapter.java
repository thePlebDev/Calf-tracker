package com.elliottSoftware.ecalvingtracker.Views.adapters;

import android.view.ViewGroup;

import com.elliottSoftware.ecalvingtracker.models.Calf;
import com.elliottSoftware.ecalvingtracker.Views.adapters.viewHolder.CalfViewHolder;

import java.util.Date;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class CalfListAdapter extends ListAdapter<Calf, CalfViewHolder> {
    private OnCalfListener onCalfListener;

    public CalfListAdapter( DiffUtil.ItemCallback<Calf> diffCallback,OnCalfListener onCalfListener) {
        super(diffCallback);
        this.onCalfListener = onCalfListener;
    }

    @Override
    public CalfViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        return CalfViewHolder.create(parent,this.onCalfListener);
    }

    @Override
    public void onBindViewHolder( CalfViewHolder holder, int position) {
        Calf current = getItem(position); // current calf data
//  NEED TO  SET THE Id
        int calfId = current.getId();
         String details = current.getDetails();
         String tagNumber = current.getTagNumber();
         String sex = current.getSex();
         Date date = current.getDate();


         holder.bind(tagNumber,details,date,calfId,sex); // binds that data

    }
    public interface OnCalfListener{
        void onCalfClick(int position);
    }

    public static class CalfDiff extends DiffUtil.ItemCallback<Calf>{

        @Override
        public boolean areItemsTheSame( Calf oldItem, Calf newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame( Calf oldItem, Calf newItem) {
            // only gets called when areItemsTheSame equals true
            return oldItem.getTagNumber().equals(newItem.getTagNumber());
        }
    }
    public Calf getCalfAt(int position){
        return getItem(position);
    }
}

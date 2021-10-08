package com.elliottSoftware.ecalvingtracker.Views.adapters.viewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elliottSoftware.ecalvingtracker.util.DateFormatFactory;
import com.elliottSoftware.ecalvingtracker.util.Format;
import com.example.ecalvingtracker.R;
import com.elliottSoftware.ecalvingtracker.Views.adapters.CalfListAdapter;

import java.util.Date;

import androidx.recyclerview.widget.RecyclerView;

public class CalfViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    //we need a date tagNumber and details
    private TextView tagNumberTextView;
    private TextView detailsTextView;
    private TextView dateOfBirthTextView;
    private TextView sexTextView;
    private Format dayMonthYear;
    private int calfId;
    private CalfListAdapter.OnCalfListener onCalfListener;

    public CalfViewHolder( View itemView,CalfListAdapter.OnCalfListener onCalfListener) {
        super(itemView);
        //FINDS ALL THE TEXTVIEWS

        this.tagNumberTextView = itemView.findViewById(R.id.text_view_title);
        this.detailsTextView = itemView.findViewById(R.id.text_view_description);
        this.dateOfBirthTextView = itemView.findViewById(R.id.text_view_priority);
        this.sexTextView = itemView.findViewById(R.id.text_view_sex);
        this.onCalfListener = onCalfListener;
        //SET THE ONCLICK LISTENER
        itemView.setOnClickListener(this);
    }

    public void bind(String tagNumber,String details,Date dateOfBirth,int calfId,String sex){
        //BINDS THE DATA TO THE TEXT VIEWS
        this.tagNumberTextView.setText(tagNumber);
        this.detailsTextView.setText(details);
        this.calfId = calfId;
        this.sexTextView.setText(sex);


        //NOW WE ARE PROGRAMMING TO AN INTERFACE,WHICH IS GOOD BUT NOT GREAT
        //IF THIS IS CONSTANTLY NEEDED TO BE CHANGED THEN WE SHOULD LOOK AT FACTORY PATTERN
        this.dayMonthYear = new DateFormatFactory(dateOfBirth);
        String date = this.dayMonthYear.format();

        this.dateOfBirthTextView.setText(date);

    }

    public static CalfViewHolder create(ViewGroup parent,CalfListAdapter.OnCalfListener onCalfListener){
        //USED TO INFLATE THE ACTUAL VIEW
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calf_item,parent,false);
        return new CalfViewHolder(view,onCalfListener);
    }

    @Override
    public void onClick(View v) {

        onCalfListener.onCalfClick(calfId);
        // so we just pass in all the informaion from the textViews

    }
}

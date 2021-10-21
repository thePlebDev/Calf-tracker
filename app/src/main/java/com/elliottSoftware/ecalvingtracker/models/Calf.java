package com.elliottSoftware.ecalvingtracker.models;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "calf_table")
public class Calf {
    @NonNull
    @ColumnInfo(name = "tagNumber")
    private String tagNumber;

    @ColumnInfo(name = "sex")
    private String sex;

    @ColumnInfo(name = "cciaNumber")
    private String cciaNumber;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "details")
    private String details;

    @PrimaryKey(autoGenerate = true)
    private int id; //MIGHT HAVE TO CHANGE THIS TO PRIVATE;

    public Calf(@NonNull String tagNumber, String details,Date date,String sex, String cciaNumber){
        this.tagNumber = tagNumber;
        this.details = details;
        this.date = date;
        this.sex = sex;
        this.cciaNumber = cciaNumber;
    }

    @Ignore
    public Calf(@NonNull int id,@NonNull String tagNumber, String details,Date date,String sex, String cciaNumber){
        this.id = id;
        this.tagNumber = tagNumber;
        this.details = details;
        this.sex = sex;
        this.cciaNumber = cciaNumber;
        this.date = date;

    }



    //GETTERS
    public String getTagNumber(){
        return this.tagNumber;
    }
    public Date getDate(){return this.date;}
    public String getDetails(){
        return this.details;
    }
    public String getSex(){return this.sex;}
    public String getCciaNumber(){return this.cciaNumber;}
    public int getId(){
        return this.id;
    }

    //SETTERS
    public void setTagNumber(String tagNumber){
        this.tagNumber = tagNumber;
    }
    public void setDate(Date date){this.date = date;}
    public void setDetails(String details){
        this.details = details;
    }
    public void setSex(String sex){this.sex = sex;}
    public void setCciaNumber(String cciaNumber){this.cciaNumber = cciaNumber;}

    public void setId(int id){
        //THIS SHOULD ONLY BE USED BY THE SYSTEM
        this.id = id;
    }
}

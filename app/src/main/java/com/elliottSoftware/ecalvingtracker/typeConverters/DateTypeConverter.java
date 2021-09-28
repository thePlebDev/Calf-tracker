package com.elliottSoftware.ecalvingtracker.typeConverters;

import java.util.Date;

import androidx.room.TypeConverter;

public class DateTypeConverter {

    @TypeConverter
    public static Date toDate(Long value){
        //VALUE == NULL. ? YES : NO
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value){
        //VALUE == NULL. ? YES : NO
        return value == null ? null : value.getTime();
    }
}

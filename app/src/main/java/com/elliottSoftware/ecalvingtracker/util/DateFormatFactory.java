package com.elliottSoftware.ecalvingtracker.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatFactory implements Format {

    private Date date;
    private String pattern = "yyyy-MM-dd";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


    public DateFormatFactory(Date date){
        this.date = date;
    }

    public String YearMonthDayFormat(){
        return simpleDateFormat.format(this.date);
    }

    @Override
    public String format() {
        return simpleDateFormat.format(this.date);
    }
}

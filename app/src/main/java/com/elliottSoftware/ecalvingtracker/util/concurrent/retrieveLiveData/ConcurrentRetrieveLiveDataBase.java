package com.elliottSoftware.ecalvingtracker.util.concurrent.retrieveLiveData;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;

public class ConcurrentRetrieveLiveDataBase {
    private final CalfDao calfDao;

    ConcurrentRetrieveLiveDataBase(CalfDao calfDao){
        this.calfDao = calfDao;
    }


}

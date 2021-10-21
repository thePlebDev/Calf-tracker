package com.example.ecalvingtracker;

import com.elliottSoftware.ecalvingtracker.models.Calf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TestUtil {


    public static final Calf TEST_NOTE_1 = new Calf("test-1", "TEST 1", new Date(),"Bull","test-1");

    public static final String TIMESTAMP_2 = "06-2019";
    public static final Calf TEST_NOTE_2 = new Calf("test-2", "TEST 2", new Date(),"Bull","test-2");

    public static final List<Calf> TEST_NOTES_LIST = Collections.unmodifiableList(
            new ArrayList<Calf>(){{
                add(new Calf(1,"test-1", "TEST 1", new Date(),"Bull","test-1"));
                add(new Calf(2,"test-2", "TEST 2", new Date(),"Bull","test-2"));
            }}
    );
}

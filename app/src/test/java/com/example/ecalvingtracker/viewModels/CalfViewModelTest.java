package com.example.ecalvingtracker.viewModels;

import com.elliottSoftware.ecalvingtracker.repositories.CalfRepository;
import com.elliottSoftware.ecalvingtracker.viewModels.CalfViewModel;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CalfViewModelTest {

    //SYSTEM UNDER TEST
    private CalfViewModel calfViewModel;

    @Mock
    private CalfRepository calfRepository;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
        calfViewModel = new CalfViewModel(calfRepository);
    }
}

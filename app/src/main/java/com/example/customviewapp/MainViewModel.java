package com.example.customviewapp;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public MainViewModel(Application application) {
        super(application);
    }

    public List<ViewPagerModel> getViewPagerList() {
        List<ViewPagerModel> list = new ArrayList<>();
        list.add(new ViewPagerModel("Home", new HomeFragment()));
        list.add(new ViewPagerModel("Search", new SearchFragment()));
        list.add(new ViewPagerModel("Edit", new EditFragment()));
        list.add(new ViewPagerModel("Delete", new DeleteFragment()));
        list.add(new ViewPagerModel("Utilities", new UtilitiesFragment()));
        return list;
    }
}
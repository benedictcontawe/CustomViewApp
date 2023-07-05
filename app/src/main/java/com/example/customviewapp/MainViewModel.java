package com.example.customviewapp;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private final List<FilterModel> list = new ArrayList<FilterModel>();

    public MainViewModel(Application application) {
        super(application);
    }

    private String getString(int id) {
        return String.format(getApplication().getString(id));
    }

    private String getString(int id, String value) {
        return String.format(getApplication().getString(id, value));
    }

    private String getString(int id, int value) {
        return String.format(getApplication().getResources().getString(id, getString(value)));
    }

    private String getString(int id, String firstValue, String secondValue) {
        return String.format(getApplication().getResources().getString(id, firstValue, secondValue));
    }

    public void initializeFilterList() {
        Log.d(TAG,"initializeFilterList()");
        List<FilterModel> list = new ArrayList<>();
        list.add(new FilterModel("Food 1", getString(R.string.fast_delivery)));
        list.add(new FilterModel("Food 2", getString(R.string.pickup)));
        list.add(new FilterModel("Food 3", getString(R.string.best_offer)));
        list.add(new FilterModel("Food 4", getString(R.string.fast_selling)));
        list.add(new FilterModel("Food 5", getString(R.string.fast_delivery)));
        list.add(new FilterModel("Food 6", getString(R.string.pickup)));
        list.add(new FilterModel("Food 7", getString(R.string.best_offer)));
        list.add(new FilterModel("Food 8", getString(R.string.fast_selling)));
        list.add(new FilterModel("Food 9", getString(R.string.fast_delivery)));
        list.add(new FilterModel("Food 10", getString(R.string.pickup)));
        list.add(new FilterModel("Food 11", getString(R.string.best_offer)));
        list.add(new FilterModel("Food 12", getString(R.string.fast_selling)));
        list.add(new FilterModel("Food 13", getString(R.string.fast_delivery)));
        this.list.clear();
        this.list.addAll(list);
    }

    public void filterList(String filter) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list.removeIf( model -> model.getType().contentEquals(filter) == false);
        } else if (filter.contentEquals(getString(R.string.fast_delivery))) {
            this.list.clear();
            list.add(new FilterModel("Food 1", getString(R.string.fast_delivery)));
            list.add(new FilterModel("Food 5", getString(R.string.fast_delivery)));
            list.add(new FilterModel("Food 9", getString(R.string.fast_delivery)));
            list.add(new FilterModel("Food 13", getString(R.string.fast_delivery)));
        } else if (filter.contentEquals(getString(R.string.pickup))) {
            this.list.clear();
            list.add(new FilterModel("Food 2", getString(R.string.pickup)));
            list.add(new FilterModel("Food 6", getString(R.string.pickup)));
            list.add(new FilterModel("Food 10", getString(R.string.pickup)));
        } else if (filter.contentEquals(getString(R.string.best_offer))) {
            this.list.clear();
            list.add(new FilterModel("Food 3", getString(R.string.best_offer)));
            list.add(new FilterModel("Food 7", getString(R.string.best_offer)));
            list.add(new FilterModel("Food 11", getString(R.string.best_offer)));
        } else if (filter.contentEquals(getString(R.string.fast_selling))) {
            this.list.clear();
            list.add(new FilterModel("Food 4", getString(R.string.fast_selling)));
            list.add(new FilterModel("Food 8", getString(R.string.fast_selling)));
            list.add(new FilterModel("Food 12", getString(R.string.fast_selling)));
        }
    }

    public List<FilterModel> getFilterModelList() {
        return list;
    }
}
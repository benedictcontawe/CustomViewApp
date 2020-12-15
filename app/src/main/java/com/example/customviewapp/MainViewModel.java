package com.example.customviewapp;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {


    public MainViewModel() {

    }

    private Boolean utilitiyIndicator;

    public void setUtilitiyIndicator(Boolean utilitiyIndicator) {
        this.utilitiyIndicator = utilitiyIndicator;
    }

    public Boolean getUtilitiyIndicator() {
        if (utilitiyIndicator == null) {
            return false;
        } else {
            return utilitiyIndicator;
        }
    }
}

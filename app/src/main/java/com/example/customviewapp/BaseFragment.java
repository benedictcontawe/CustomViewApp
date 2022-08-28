package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

abstract public class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();
    private MainActivity mainActivity;
    protected MainViewModel mainViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate()");
        mainActivity = (MainActivity) getActivity();
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    protected MainActivity getMainActivity() {
        return mainActivity;
    }
}
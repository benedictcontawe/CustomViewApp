package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

abstract public class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();
    private MainActivity mainActivity;
    protected MainViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate()");
        mainActivity = (MainActivity) getActivity();
        viewModel = new ViewModelProvider(getMainActivity()).get(MainViewModel.class);
    }

    protected MainActivity getMainActivity() {
        return mainActivity;
    }

    protected void showToast(String message, int duration) {
        Toast.makeText(requireContext(), message, duration).show();
    }

    protected void showSnackBar(String message, int duration) {
        Snackbar.make(requireView(), message, duration).show();
    }
}
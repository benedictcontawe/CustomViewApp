package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate()");
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        if (savedInstanceState == null) {
            launchButtons();
            viewModel.initializeFilterList();
        }
    }

    private void replaceFragment(int containerViewId, BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction()
            .replace(containerViewId, fragment, fragment.getClass().getSimpleName())
            .commitNow();
    }

    private void addToBackStackFragment(int containerViewId, BaseFragment fragment) {
        if (getSupportFragmentManager().findFragmentByTag(fragment.getClass().getSimpleName()) == null) {
            getSupportFragmentManager().beginTransaction()
            .add(containerViewId, fragment, fragment.getClass().getSimpleName())
            .addToBackStack(fragment.getClass().getSimpleName())
            .commit();
        }
    }

    private void showDialogFragment(DialogFragment fragment) {
        fragment.show(getSupportFragmentManager().beginTransaction(), fragment.getClass().getName());
    }

    private void launchButtons() {
        replaceFragment(R.id.frame_layout, ButtonsFragment.newInstance());
    }

    public void launchEntry() {
        addToBackStackFragment(R.id.frame_layout, EntryFragment.newInstance());
    }

    public void launchFilter() {
        addToBackStackFragment(R.id.frame_layout, FilterFragment.newInstance());
    }

    public void launchChoice() {
        addToBackStackFragment(R.id.frame_layout, ChoiceFragment.newInstance());
    }

    public void launchAction() {
        addToBackStackFragment(R.id.frame_layout, ActionFragment.newInstance());
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG,"onBackPressed()");
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}
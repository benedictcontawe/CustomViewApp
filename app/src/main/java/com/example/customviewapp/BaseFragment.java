package com.example.customviewapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


abstract public class BaseFragment extends Fragment {

    private static String TAG = BaseFragment.class.getSimpleName();
    protected MainActivity mainActivity;
    protected MainViewModel mainViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.d(TAG,"onCreate()");
        mainActivity = (MainActivity) getActivity(); //((AppCompatActivity)getActivity())
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    public void setToolbarVisibility(int visibility) {
        mainActivity.getTxtToolbar().setVisibility(visibility);
    }

    public void setToolbarText(@Nullable String text) {
        mainActivity.getTxtToolbar().setText(text);
    }

    public void setNavigationIcon(@Nullable Drawable icon) {
        mainActivity.getToolbar().setNavigationIcon(icon);
    }

    public void setNavigationIcon(Integer icon) {
        mainActivity.getToolbar().setNavigationIcon(icon);
    }

    public void setNavigationOnClickListener(@Nullable View.OnClickListener listener) {
        mainActivity.getToolbar().setNavigationOnClickListener(listener);
    }

    public void setTitle(@Nullable String title) {
        mainActivity.getSupportActionBar().setTitle(title);
    }

    public void setIcon(@Nullable Drawable icon) {
        mainActivity.getSupportActionBar().setIcon(icon);
    }

    public void setPlaceHolderVisibility(int visibility) {
        mainActivity.getPlaceHolder().setVisibility(visibility);
    }

    public void reCreateOptionsMenu() {
        setHasOptionsMenu(false);
        setHasOptionsMenu(true);
    }
}

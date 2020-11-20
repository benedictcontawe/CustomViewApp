package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

public class UtilitiesFragment extends BaseFragment {

    private static String TAG = UtilitiesFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(null);
        setIcon(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView()");
        return inflater.inflate(R.layout.fragment_utilities, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated()");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.utilities_menu, menu);
        setNavigationIcon(R.drawable.ic_arrow_back);
        setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPlaceHolderVisibility(View.VISIBLE);
                reCreateOptionsMenu();
            }
        });
        super.onCreateOptionsMenu(menu,inflater);
        Log.d(TAG,"onCreateOptionsMenu()");
    }

    @Override
    public void onPause() {
        super.onPause();
        setPlaceHolderVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

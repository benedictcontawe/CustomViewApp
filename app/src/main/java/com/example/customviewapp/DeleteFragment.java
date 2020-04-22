package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.*;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class DeleteFragment extends Fragment implements OnClickListener {

    private static String TAG = DeleteFragment.class.getSimpleName();
    private MainActivity mainActivity;
    private Button btnIncrease,btnDecrease;
    private int counter = -1;
    private MenuItem deleteSelected, deleteAll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.d(TAG,"onCreate()");
        mainActivity = (MainActivity) getActivity();
        mainActivity.getSupportActionBar().setTitle("Delete Fragment");
        mainActivity.getSupportActionBar().setIcon(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView()");
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        btnIncrease = (Button)view.findViewById(R.id.btnIncrease);
        btnDecrease = (Button)view.findViewById(R.id.btnDecrease);

        btnIncrease.setOnClickListener(this);
        btnDecrease.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        switch (counter) {
            case -1:
                inflater.inflate(R.menu.delete_menu, menu);
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Delete Fragment");
                mainActivity.getToolbar().setNavigationIcon(null);
                mainActivity.getToolbar().setNavigationOnClickListener(null);
                break;
            default:
                inflater.inflate(R.menu.delete_counter_menu, menu);
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(null);
                mainActivity.getToolbar().setNavigationIcon(R.drawable.ic_arrow_back);
                mainActivity.getToolbar().setNavigationOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resetToolBarState();
                    }
                });
                break;
        }
        super.onCreateOptionsMenu(menu, inflater);
        Log.d(TAG,"onCreateOptionsMenu()");
        deleteSelected = (MenuItem) menu.findItem(R.id.delete_selected);
        deleteAll = (MenuItem) menu.findItem(R.id.delete_all);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                Log.d(TAG,"onClick action_delete");
                btnIncrease.setVisibility(View.VISIBLE);
                btnDecrease.setVisibility(View.VISIBLE);
                counter = 0;
                reCreateOptionsMenu();
                mainActivity.getTxtToolbar().setVisibility(View.VISIBLE);
                mainActivity.getTxtToolbar().setText(String.valueOf(counter));
                return true;
            case R.id.delete_selected:
                Log.d(TAG,"onClick delete_selected");
                Toast.makeText(getContext(), "Delete Selected " + counter, Toast.LENGTH_SHORT).show();
                resetToolBarState();
                return true;
            case R.id.delete_all:
                Log.d(TAG,"onClick delete_all");
                Toast.makeText(getContext(), "Delete All", Toast.LENGTH_SHORT).show();
                resetToolBarState();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void reCreateOptionsMenu() {
        setHasOptionsMenu(false);
        setHasOptionsMenu(true);
    }

    private void resetToolBarState() {
        Log.d(TAG,"resetToolBarState()");
        btnIncrease.setVisibility(View.INVISIBLE);
        btnDecrease.setVisibility(View.INVISIBLE);
        counter = -1;
        mainActivity.getTxtToolbar().setVisibility(View.INVISIBLE);
        reCreateOptionsMenu();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnIncrease:
                Log.d(TAG,"onClick btnIncrease");
                counter++;
                setIcons();
                mainActivity.getTxtToolbar().setText(String.valueOf(counter));
                break;
            case R.id.btnDecrease:
                Log.d(TAG,"onClick btnDecrease");
                if (counter > 0) counter--;
                setIcons();
                mainActivity.getTxtToolbar().setText(String.valueOf(counter));
                break;
            default:
                Log.d(TAG,"onClick default");
                break;
        }
    }

    private void setIcons() {
        if (counter == 0) {
            deleteSelected.setVisible(false);
            deleteAll.setVisible(true);
        } else {
            deleteSelected.setVisible(true);
            deleteAll.setVisible(false);
        }
    }
}
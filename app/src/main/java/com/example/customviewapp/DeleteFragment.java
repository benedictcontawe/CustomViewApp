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
import org.jetbrains.annotations.NotNull;

public class DeleteFragment extends BaseFragment implements OnClickListener {

    private static final String TAG = DeleteFragment.class.getSimpleName();
    private Button btnIncrease,btnDecrease;
    private MenuItem deleteSelected, deleteAll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Delete Fragment");
        setIcon(null);
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
    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        Log.d(TAG,"onCreateOptionsMenu()");
        switch (mainViewModel.getCounterInteger()) {
            case -1:
                inflater.inflate(R.menu.delete_menu, menu);
                setTitle("Delete Fragment");
                setNavigationIcon(null);
                setNavigationOnClickListener(null);
                break;
            default:
                inflater.inflate(R.menu.delete_counter_menu, menu);
                setTitle(null);
                setNavigationIcon(R.drawable.ic_arrow_back);
                setNavigationOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resetToolBarState();
                    }
                });
                break;
        }
        super.onCreateOptionsMenu(menu, inflater);
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
                mainViewModel.setCounter(0);
                reCreateOptionsMenu();
                setToolbarVisibility(View.VISIBLE);
                setToolbarText(mainViewModel.getCounterString());
                return true;
            case R.id.delete_selected:
                Log.d(TAG,"onClick delete_selected");
                Toast.makeText(getContext(), "Delete Selected " + mainViewModel.getCounterString(), Toast.LENGTH_SHORT).show();
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

    protected void resetToolBarState() {
        Log.d(TAG,"resetToolBarState()");
        btnIncrease.setVisibility(View.INVISIBLE);
        btnDecrease.setVisibility(View.INVISIBLE);
        mainViewModel.decrementCounter();
        super.resetToolBarState();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnIncrease:
                Log.d(TAG,"onClick btnIncrease");
                mainViewModel.incrementCounter();
                setIcons();
                setToolbarText(mainViewModel.getCounterString());
                break;
            case R.id.btnDecrease:
                Log.d(TAG,"onClick btnDecrease");
                if (mainViewModel.isGreaterThanCounter(0)) mainViewModel.decrementCounter();
                setIcons();
                setToolbarText(mainViewModel.getCounterString());
                break;
            default:
                Log.d(TAG,"onClick default");
                break;
        }
    }

    private void setIcons() {
        if (mainViewModel.isEqualCounter(0)) {
            deleteSelected.setVisible(false);
            deleteAll.setVisible(true);
        } else {
            deleteSelected.setVisible(true);
            deleteAll.setVisible(false);
        }
    }
}
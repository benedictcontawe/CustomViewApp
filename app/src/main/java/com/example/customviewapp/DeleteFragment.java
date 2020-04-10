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
    private Button btnIncrease,btnDecrease;
    private int counter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.d(TAG,"onCreate()");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Delete Fragment");
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated()");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.delete_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        Log.d(TAG,"onCreateOptionsMenu()");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                Toast.makeText(getContext(), "Delete View selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnIncrease:
                Log.d(TAG,"onClick btnIncrease");
                Toast.makeText(getContext(), "btnIncrease", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnDecrease:
                Log.d(TAG,"onClick btnDecrease");
                Toast.makeText(getContext(), "btnDecrease", Toast.LENGTH_SHORT).show();
                break;
            default:
                Log.d(TAG,"onClick default");
                Toast.makeText(getContext(), "Default", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

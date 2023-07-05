package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ChoiceFragment extends BaseFragment implements ChipGroup.OnCheckedStateChangeListener {

    private static final String TAG = ChoiceFragment.class.getSimpleName();

    public static ChoiceFragment newInstance() {
        return new ChoiceFragment();
    }

    private ChipGroup chipGroup;

    public ChoiceFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView()");
        return inflater.inflate(R.layout.fragment_choice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chipGroup = view.findViewById(R.id.chip_group);
        chipGroup.setOnCheckedStateChangeListener(this::onCheckedChanged);
    }

    @Override
    public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
        if (chipGroup == group) {
            for (Integer checkedId : checkedIds) {
                Chip chip = group.findViewById(checkedId);
                Log.d(TAG,"Chip " + chip.getText() + " " + chip.isChecked());
                showSnackBar(chip.getText().toString(), Snackbar.LENGTH_SHORT);
            }
        }
    }
}
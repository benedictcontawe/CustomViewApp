package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;

public class ButtonsFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = ButtonsFragment.class.getSimpleName();

    private Button entryChipButton, filterChipButton, choiceChipButton, actionChipButton;

    public static ButtonsFragment newInstance() {
        return new ButtonsFragment();
    }

    public ButtonsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView()");
        return inflater.inflate(R.layout.fragment_buttons, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        entryChipButton = (MaterialButton) view.findViewById(R.id.entry_chip_button);
        filterChipButton = (MaterialButton) view.findViewById(R.id.filter_chip_button);
        choiceChipButton = (MaterialButton) view.findViewById(R.id.choice_chip_button);
        actionChipButton = (MaterialButton) view.findViewById(R.id.action_chip_button);
        entryChipButton.setOnClickListener(this::onClick);
        filterChipButton.setOnClickListener(this::onClick);
        choiceChipButton.setOnClickListener(this::onClick);
        actionChipButton.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        if (entryChipButton == view) {
            Log.d(TAG, "onClick entryChipButton");
            getMainActivity().launchEntry();
        } else if (filterChipButton == view) {
            Log.d(TAG, "onClick filterChipButton");
            getMainActivity().launchFilter();
        } else if (choiceChipButton == view) {
            Log.d(TAG, "onClick choiceChipButton");
            getMainActivity().launchChoice();
        } else if (actionChipButton == view) {
            Log.d(TAG, "onClick actionChipButton");
            getMainActivity().launchAction();
        }
    }
}

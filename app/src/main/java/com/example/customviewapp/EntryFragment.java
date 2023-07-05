package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class EntryFragment extends BaseFragment implements View.OnClickListener, TextView.OnEditorActionListener {

    private static final String TAG = EntryFragment.class.getSimpleName();

    public static EntryFragment newInstance() {
        return new EntryFragment();
    }

    private AutoCompleteTextView toTextView;

    private ChipGroup chipGroup;

    public EntryFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView()");
        return inflater.inflate(R.layout.fragment_entry, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toTextView = view.findViewById(R.id.to_text_view);
        chipGroup = view.findViewById(R.id.chip_group);
        chipGroup.removeAllViews();
        //chipGroup.setOnCheckedStateChangeListener(this::onCheckedChanged);
        toTextView.setOnEditorActionListener(this::onEditorAction);
    }

    @Override
    public void onClick(View view) {
        chipGroup.removeView(view);
    }

    @Override
    public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
        if(toTextView == view && EditorInfo.IME_ACTION_GO == actionId) {
            addChipToGroup(view.getText().toString());
            toTextView.getText().clear();
            return true;
        }
        return false;
    }

    private void addChipToGroup(String text) {
        Chip chip = new Chip(requireContext());
        chip.setText(text);
        chip.setChipIcon(ContextCompat.getDrawable(requireContext(), R.drawable.ic_launcher_foreground));
        chip.setChipIconVisible(false);
        chip.setCloseIconVisible(true);
        chip.setClickable(true);
        chip.setCheckable(false);
        chipGroup.addView(chip);
        chip.setOnCloseIconClickListener(this::onClick);
    }
}
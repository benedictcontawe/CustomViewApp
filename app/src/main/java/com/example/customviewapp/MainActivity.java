package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ChipGroup.OnCheckedStateChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainViewModel viewModel;
    private ChipGroup chipGroup;
    private Chip chipHome, chipSearch, chopEdit, chipDelete, chipUtilities, chipClose;
    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate()");
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        chipGroup = (ChipGroup)findViewById(R.id.chip_group);
        chipHome = (Chip)findViewById(R.id.chip_home);
        chipSearch = (Chip)findViewById(R.id.chip_search);
        chopEdit = (Chip)findViewById(R.id.chip_edit);
        chipDelete = (Chip)findViewById(R.id.chip_delete);
        chipUtilities = (Chip)findViewById(R.id.chip_utilities);
        chipClose = (Chip)findViewById(R.id.chip_close);
        viewPager = (ViewPager2)findViewById(R.id.view_pager);
        chipClose.setOnCloseIconClickListener(this);
        chipGroup.setOnCheckedStateChangeListener(this);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle(), viewModel.getViewPagerList());
        viewPager.setAdapter(adapter);
        viewPager.setUserInputEnabled(true);

        chipGroup.removeAllViews();
        chipGroup.addView(chipHome);
        chipGroup.addView(chipSearch);
        chipGroup.addView(chopEdit);
        chipGroup.addView(chipDelete);
        chipGroup.addView(chipUtilities);
        chipGroup.addView(chipClose);

        Chip chip = new Chip(new ContextThemeWrapper(this,R.style.CustomChipFilterStyle));
        chip.setId(ViewCompat.generateViewId());
        chip.setCheckable(true);
        chip.setChipBackgroundColorResource(R.color.selector_chip_background);
        chip.setCloseIconVisible(true);
        chip.setText("ABCDEF");
        chip.setTextAppearanceResource(R.style.CustomChipFilterStyle);
        chipGroup.addView(chip);
    }

    @Override
    public void onClick(View view) {
        if (chipClose == view) {
            Log.d(TAG,"Chip close");
            chipGroup.removeView(view);
        }
    }

    @Override
    public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
        for (Integer checkedId : checkedIds) {
            Chip chip = group.findViewById(checkedId);
            Log.d(TAG,"Chip " + chip.getText() + " " + chip.isChecked());
            if (chipGroup == group && chipHome == chip) {
                viewPager.setCurrentItem(0, true);
            } else if (chipGroup == group && chipSearch == chip) {
                viewPager.setCurrentItem(1, true);
            } else if (chipGroup == group && chopEdit == chip) {
                viewPager.setCurrentItem(2, true);
            } else if (chipGroup == group && chipDelete == chip) {
                viewPager.setCurrentItem(3, true);
            } else if (chipGroup == group && chipUtilities == chip) {
                viewPager.setCurrentItem(4, true);
            } else {
                Log.d(TAG,"Chip Unknown");
            }
        }
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
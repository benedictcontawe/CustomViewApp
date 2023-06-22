package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainViewModel viewModel;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate()");
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager = (ViewPager2)findViewById(R.id.view_pager);
        //tabLayout.setSelectedTabIndicatorColor();
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle(), viewModel.getViewPagerList());
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(adapter.getTitle(position));
                //tab.setCustomView(getRelativeLayout());
            }
        }).attach();
    }

    private RelativeLayout getRelativeLayout() {
        return (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tab_layout, tabLayout, false);

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
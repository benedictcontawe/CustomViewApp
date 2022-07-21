package com.example.customviewapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, List<ViewPagerModel> list) {
        super(fragmentManager, lifecycle);
        this.list = list;
    }

    private final List<ViewPagerModel> list;

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return list.get(position).getFragment();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public String getTitle(int position) {
        return list.get(position).getTitle();
    }

    public BaseFragment getFragment(int position) {
        return list.get(position).getFragment();
    }
}
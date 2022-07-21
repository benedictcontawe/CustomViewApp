package com.example.customviewapp;

public class ViewPagerModel {

    public ViewPagerModel(String title, BaseFragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    private final String title;
    private final BaseFragment fragment;

    public String getTitle() {
        return title;
    }

    public BaseFragment getFragment() {
        return fragment;
    }
}
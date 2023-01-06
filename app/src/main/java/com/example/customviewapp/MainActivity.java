package com.example.customviewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final LocaleListCompat appLocale = LocaleListCompat.forLanguageTags("xx-YY");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setApplicationLocales(appLocale);
        AppCompatDelegate.getApplicationLocales().get(0);

    }
}
package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.chip.Chip;

public class ActionFragment extends BaseFragment implements View.OnClickListener{

    private static final String TAG = ActionFragment.class.getSimpleName();

    public static ActionFragment newInstance() {
        return new ActionFragment();
    }

    private Chip alarmChip, musicChip, callChip , messageChip;

    public ActionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView()");
        return inflater.inflate(R.layout.fragment_action, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alarmChip = view.findViewById(R.id.alarm_chip);
        musicChip = view.findViewById(R.id.music_chip);
        callChip = view.findViewById(R.id.call_chip);
        messageChip = view.findViewById(R.id.message_chip);
        alarmChip.setOnClickListener(this::onClick);
        musicChip.setOnClickListener(this::onClick);
        callChip.setOnClickListener(this::onClick);
        messageChip.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        if (alarmChip == view) {
            showToast("Alarm", Toast.LENGTH_SHORT);
        } else if (musicChip == view) {
            showToast("Music", Toast.LENGTH_SHORT);
        } else if (callChip == view) {
            showToast("Call", Toast.LENGTH_SHORT);
        } else if (messageChip == view) {
            showToast("Message", Toast.LENGTH_SHORT);
        }
    }
}
package com.example.customappactionsheet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btnActionSheet;
    private ActionSheetFragment actionSheetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate()");

        btnActionSheet = (Button)findViewById(R.id.btnActionSheet);
        btnActionSheet.setOnClickListener(this);
        getOnBackPressedDispatcher().addCallback(this, getHandleOnBackPressed());
    }

    @Override
    public void onClick(View view) {
        /*
        switch (view.getId()) {
            case R.id.btnActionSheet:
                break;
            default:
                break;
        }
        */
        if (view.getId() == R.id.btnActionSheet) {
            Log.d(TAG,"onClick btnActionSheet");
            actionSheetFragment = new ActionSheetFragment();
            actionSheetFragment.setCancelable(true);
            actionSheetFragment.show(getSupportFragmentManager(), ActionSheetFragment.TAG);
        } else {
            Log.d(TAG,"onClick default");
            Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show();
        }
    }


    private OnBackPressedCallback getHandleOnBackPressed() {
        return new OnBackPressedCallback(true) {
            @Override public void handleOnBackPressed() { Log.d(TAG,"handleOnBackPressed()");
                if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    finish();
                } else getSupportFragmentManager().popBackStackImmediate();
            }
        };
    }
    /*
    @Override
    public void onBackPressed() {
        Log.d(TAG,"onBackPressed()");
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
    */
}
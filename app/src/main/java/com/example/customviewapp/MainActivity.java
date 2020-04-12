package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static String TAG = MainActivity.class.getSimpleName();
    private Toolbar toolbar;
    private ImageButton btnHome,btnSearch,btnEdit,btnDelete,btnUtilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate()");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Main Activity");
        //toolbar.setNavigationIcon();
        //toolbar.setNavigationOnClickListener();

        btnHome = (ImageButton)findViewById(R.id.btnHome);
        btnSearch = (ImageButton)findViewById(R.id.btnSearch);
        btnEdit = (ImageButton)findViewById(R.id.btnEdit);
        btnDelete = (ImageButton)findViewById(R.id.btnDelete);
        btnUtilities = (ImageButton)findViewById(R.id.btnUtilities);

        btnHome.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUtilities.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG,"onCreateOptionsMenu()");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnHome:
                Log.d(TAG,"onClick btnHome");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,new HomeFragment())
                        .commitNow();
                break;
            case R.id.btnSearch:
                Log.d(TAG,"onClick btnSearch");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,new SearchFragment())
                        .commitNow();
                break;
            case R.id.btnEdit:
                Log.d(TAG,"onClick btnEdit");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,new EditFragment())
                        .commitNow();
                break;
            case R.id.btnDelete:
                Log.d(TAG,"onClick btnDelete");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,new DeleteFragment())
                        .commitNow();
                break;
            case R.id.btnUtilities:
                Log.d(TAG,"onClick btnUtilities");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,new UtilitiesFragment())
                        .commitNow();
                break;
            default:
                Log.d(TAG,"onClick default");
                Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
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
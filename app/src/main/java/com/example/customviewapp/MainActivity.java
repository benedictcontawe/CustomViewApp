package com.example.customviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static String TAG = MainActivity.class.getSimpleName();
    private Toolbar toolbar;
    private TextView txtToolbar;
    private LinearLayout placeHolder;
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
        toolbar.getMenu().clear();
        //toolbar.inflateMenu(R.menu.delete_menu);

        txtToolbar = (TextView)findViewById(R.id.txtToolbar);
        placeHolder = (LinearLayout)findViewById(R.id.placeHolder);

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
        //getMenuInflater().inflate(R.menu.home_menu, menu);
        Log.d(TAG,"onCreateOptionsMenu()");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
        /*
        switch (item.getItemId()) {
            case R.id.item1:
                Log.d(TAG,"Item 1 selected");
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Log.d(TAG,"Item 2 selected");
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Log.d(TAG,"Item 3 selected");
                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem1:
                Log.d(TAG,"Sub Item 1 selected");
                Toast.makeText(this, "Sub Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem2:
                Log.d(TAG,"Sub Item 2 selected");
                Toast.makeText(this, "Sub Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem3:
                Log.d(TAG,"Sub Item 3 selected (Options Menu Disabled)");
                Toast.makeText(this, "Sub Item 3 selected (Options Menu Disabled)", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        */
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

    public TextView getTxtToolbar() {
        return txtToolbar;
    }

    public LinearLayout getPlaceHolder() {
        return placeHolder;
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
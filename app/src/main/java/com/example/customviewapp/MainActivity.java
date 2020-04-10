package com.example.customviewapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Toolbar toolbar;
    private MenuInflater inflater;
    private ImageButton btnHome,btnSearch,btnEdit,btnDelete,btnUtilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem1:
                Toast.makeText(this, "Sub Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem2:
                Toast.makeText(this, "Sub Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnHome:
                Toast.makeText(this, "btnHome", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnSearch:
                Toast.makeText(this, "btnSearch", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnEdit:
                Toast.makeText(this, "btnEdit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnDelete:
                Toast.makeText(this, "btnDelete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnUtilities:
                Toast.makeText(this, "btnUtilities", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

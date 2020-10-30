package com.example.customviewapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private val TAG : String = MainActivity::class.java.simpleName
    }

    private lateinit var actionSheetFragment : ActionSheetFragment;

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate()");

        btnActionSheet.setOnClickListener(this@MainActivity);
    }

    override fun onClick(view : View) {
        when(view) {
            btnActionSheet -> {
                Log.d(TAG,"onClick btnActionSheet");
                actionSheetFragment = ActionSheetFragment();
                actionSheetFragment.setCancelable(true);
                actionSheetFragment.show(getSupportFragmentManager(), TAG);
            }
            else -> {
                Log.d(TAG,"onClick default");
                Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show();
            }
        }
    }

    override fun onBackPressed() {
        Log.d(TAG,"onBackPressed()");
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}
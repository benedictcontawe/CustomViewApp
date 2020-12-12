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


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate()");

        btnWebView.setOnClickListener(this@MainActivity);
    }

    override fun onClick(view : View) {
        when(view) {
            btnWebView -> {
                Log.d(TAG,"onClick btnWebView");
                startActivity(
                    WebActivity.newIntent(this@MainActivity,
                        //"https://www.google.com"
                        txtURL.getText().toString()
                    )
                )
            }
            else -> {
                Log.d(TAG,"onClick default");
                Toast.makeText(this@MainActivity, "Default", Toast.LENGTH_SHORT).show();
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
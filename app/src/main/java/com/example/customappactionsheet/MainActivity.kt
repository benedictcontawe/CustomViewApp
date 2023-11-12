package com.example.customappactionsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import com.example.customappactionsheet.databinding.MainBinder

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private val TAG : String = MainActivity::class.java.simpleName
    }

    private var binder : MainBinder? = null
    private lateinit var actionSheetFragment : ActionSheetFragment

    override fun onCreate(savedInstanceState : Bundle?) {
        binder = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binder?.setLifecycleOwner(this@MainActivity)
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate()")

        binder?.btnActionSheet?.setOnClickListener(this@MainActivity)
        //getOnBackPressedDispatcher().addCallback(this@MainActivity, getHandleOnBackPressed())
    }

    override fun onClick(view : View) {
        when(view) {
            binder?.btnActionSheet -> {
                Log.d(TAG,"onClick btnActionSheet")
                actionSheetFragment = ActionSheetFragment()
                actionSheetFragment.setCancelable(true)
                actionSheetFragment.show(getSupportFragmentManager(), TAG)
            }
            else -> {
                Log.d(TAG,"onClick default")
                Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getHandleOnBackPressed() : OnBackPressedCallback {
        return object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() { Log.d(TAG,"handleOnBackPressed()")
                if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    finish()
                } else getSupportFragmentManager().popBackStackImmediate()
            }
        }
    }
    /*
    override fun onBackPressed() {
        Log.d(TAG,"onBackPressed()")
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed()
        } else {
            getSupportFragmentManager().popBackStack()
        }
    }
    */
}
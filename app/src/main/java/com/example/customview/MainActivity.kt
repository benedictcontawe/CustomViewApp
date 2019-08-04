package com.example.customview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureCustomEditText()
    }

    private fun configureCustomEditText() {
        customEditText1.setListener()

        customEditText2.setListener(
            onTextChanged = {
                Log.d(MainActivity::class.java.simpleName,"onTextChanged")
            },
            enterFocus = {
                Log.d(MainActivity::class.java.simpleName,"enterFocus")
            },
            leaveFocus = {
                Log.d(MainActivity::class.java.simpleName,"leaveFocus")
            }
        )

        customEditText2.setListener(
            onTextChanged = {
                Log.d(MainActivity::class.java.simpleName,"onTextChanged")
            },
            enterFocus = {
                Log.d(MainActivity::class.java.simpleName,"enterFocus")
            },
            leaveFocus = {
                Log.d(MainActivity::class.java.simpleName,"leaveFocus")
            }
        )
    }
}

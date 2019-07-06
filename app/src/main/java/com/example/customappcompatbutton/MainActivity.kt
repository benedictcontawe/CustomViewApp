package com.example.customappcompatbutton

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureCustomEditText()
    }

    private fun configureCustomEditText() {
        customEditText.setTextInputLayout(textInputLayoutCustomEditText)

        maskedEditText.setTextInputLayout(textInputLayoutMaskedEditText)
        maskedEditText.setText(true,"MaskedEditText")
        Log.e(MainActivity::class.java.simpleName,maskedEditText.getUnmaskedText())

        amountEditText.setTextInputLayout(textInputLayoutAmountEditText)
    }
}
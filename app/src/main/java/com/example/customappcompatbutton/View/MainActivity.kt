package com.example.customappcompatbutton.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.customappcompatbutton.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureCustomEditText()
    }

    private fun configureCustomEditText() {
        customEditText.setTextInputLayout(textInputLayoutCustomEditText)

        //TODO: Update Masked Edit Text with parameter characterMasked : String characterQuantity : Int
        maskedEditText.setTextInputLayout(textInputLayoutMaskedEditText)
        maskedEditText.setText(true,"MaskedEditText")
        Log.e(MainActivity::class.java.simpleName,maskedEditText.getUnmaskedText())

        //TODO: Finish Amount with Custom Numeric Keypad
        amountEditText.setTextInputLayout(textInputLayoutAmountEditText,false)
        amountEditText.setTextChangeEvent(amountEditText, "PHP")

        calendarDateEditText.setTextInputLayout(textInputLayoutCalendarDateEditText,false)
        calendarDateEditText.setListener(this,calendarDateEditText)

        //TODO: Finish Contact Edit Text
    }
}

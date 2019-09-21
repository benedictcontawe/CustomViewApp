package com.example.customtextinputeditext.CustomView

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.customtextinputeditext.Formatter.CurrencyFormatter

class AmountEditText : CustomEditText {

    constructor(context: Context?) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun getAmountText(currency : String) : String{
        //return this.getText().toString().replace(",","")
        var text = super.getText().toString().replace(",","")
        text = text.replace(" ","",true)
        val newValueChar = currency.toCharArray()
        for (i in 0 .. currency.length - 1){
            text = text.replace(newValueChar[i].toString(),"",true)
            Log.e("getAmountText","${newValueChar[i]}")
        }
        text.trim()
        return text
    }

    fun setTextChangeEvent(amountEditText : AmountEditText, currency : String) {
        this.setListener(this.getTextInputLayout(),
            enterFocus = {
                amountEditText.setText(getAmountText(currency))
            },
            leaveFocus = {
                val text = amountEditText.text.toString()
                if(!text.isEmpty()) {
                    amountEditText.setText(CurrencyFormatter.formatAmount(amountEditText.text.toString(), currency))
                }
            })
    }

    fun setTextChangeEvent(amountEditText: AmountEditText, currency: String,
                           setUpKeyboard: ()-> Unit, showCustomKeyboard: (v : View)-> Unit, hideCustomKeyboard: () -> Unit) {
        setUpKeyboard()

        // Disable standard keyboard hard way
        this.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View, event: MotionEvent?): Boolean {
                var edittext = v as CustomEditText
                val inType = edittext.inputType       // Backup the input type
                edittext.inputType = InputType.TYPE_NULL // Disable standard keyboard
                edittext.onTouchEvent(event)               // Call native handler
                edittext.inputType = inType              // Restore input type

                showCustomKeyboard(v)
                return true // Consume touch event
            }
        })

        this.setListener(this.getTextInputLayout(),
            enterFocus = {
                amountEditText.setText(getAmountText(currency))
                showCustomKeyboard(it)
                amountEditText.setSelection(amountEditText.length())
            },
            leaveFocus = {
                val text = amountEditText.text.toString()
                if(!text.isEmpty()) {
                    amountEditText.setText(CurrencyFormatter.formatAmount(amountEditText.text.toString(), currency))
                }
                hideCustomKeyboard()
                //amountEditText.clearFocus()
            }
        )
        // Disable spell check (hex strings look like words to Android)
        amountEditText.setInputType(amountEditText.getInputType() or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
    }
}
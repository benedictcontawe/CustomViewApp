package com.example.customappcompatbutton.CustomView

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.example.customappcompatbutton.Formatter.CurrencyFormatter

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

    fun setTextChangeEvent(amountEditText: AmountEditText, currency: String) {
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
}
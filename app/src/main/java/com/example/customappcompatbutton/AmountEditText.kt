package com.example.customappcompatbutton

import android.content.Context
import android.util.AttributeSet
import android.util.Log

class AmountEditText : CustomEditText{

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

    fun setTextChangeEvent(amountEditText: AmountEditText,currency: String) {
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
        /*this.onFocusChangeListener = object : OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                when(hasFocus) {
                    true -> {
                        amountEditText.setText(getAmountText(currency))
                    }
                    false -> {
                        if(!amountEditText.text.toString().isEmpty()) {
                            val isValidValue = DoubleValidator.isValidDecimal(amountEditText.text.toString())

                            if(!isValidValue) {
                                amountEditText.setText(CurrencyFormatter.formatCurrencyCode(0.0, currency))
                            } else {
                                amountEditText.setText(CurrencyFormatter.formatCurrencyCode(amountEditText.getText().toString(), currency))
                            }
                        }
                    }
                }
            }
        }*/
    }
}
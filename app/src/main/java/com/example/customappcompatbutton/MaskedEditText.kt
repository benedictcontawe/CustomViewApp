package com.example.customappcompatbutton

import android.content.Context
import android.util.AttributeSet

class MaskedEditText : CustomEditText {

    constructor(context: Context?) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var mUnmaskedText = ""
    private var mMasked = false

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)

        if(lengthBefore > lengthAfter && text.toString().contains("•")) {
            //deleting
            setText("")
        }
    }

    fun setText(masked: Boolean, text: CharSequence?) {
        mMasked = masked
        mUnmaskedText = text.toString()

        if(masked) {
            setText(TextMaskHelper.maskText(text.toString(), 3))
        } else {
            setText(text)
        }
    }

    fun getUnmaskedText(): String {
        val username = super.getText().toString()
        return if(username.contains("•")) {
            mUnmaskedText
        } else username
    }
}
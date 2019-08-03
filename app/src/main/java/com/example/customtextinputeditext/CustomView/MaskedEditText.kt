package com.example.customtextinputeditext.CustomView

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.example.customtextinputeditext.Formatter.TextMaskFormatter
import com.example.customtextinputeditext.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class MaskedEditText : CustomEditText {

    constructor(context: Context) : super(context)
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

        if (text!!.isNotEmpty()){
            Observable.timer(50, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe {
                ViewCompat.setBackgroundTintList(this@MaskedEditText, ColorStateList.valueOf(
                    ContextCompat.getColor(context, R.color.blue)))
                setUpperHintColor(getTextInputLayout(),this@MaskedEditText)
            }
        }

        if(masked) {
            setText(TextMaskFormatter.maskText(text.toString(), 3))
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
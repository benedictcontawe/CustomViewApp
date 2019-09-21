package com.example.customtextinputeditext.CustomView

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.example.customtextinputeditext.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


open class CustomEditText : TextInputEditText {

    protected var mTextInputLayout: TextInputLayout? = null

    constructor(context : Context?) : super(context)
    constructor(context : Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(context, attrs)
    }
    constructor(context : Context, attrs : AttributeSet?, defStyleAttr : Int) : super(context, attrs, defStyleAttr) {
        initAttrs(context, attrs)
    }

    init{

    }

    fun initAttrs(context : Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val attributeArray = context.obtainStyledAttributes(attrs,
                R.styleable.CustomTextView
            )

            var drawableStart: Drawable? = null
            var drawableEnd: Drawable? = null
            var drawableBottom: Drawable? = null
            var drawableTop: Drawable? = null

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawableStart = attributeArray.getDrawable(R.styleable.CustomTextView_drawableStartCompat)
                drawableEnd = attributeArray.getDrawable(R.styleable.CustomTextView_drawableEndCompat)
                drawableBottom = attributeArray.getDrawable(R.styleable.CustomTextView_drawableBottomCompat)
                drawableTop = attributeArray.getDrawable(R.styleable.CustomTextView_drawableTopCompat)
            }
            else {
                val drawableStartId = attributeArray.getResourceId(R.styleable.CustomTextView_drawableStartCompat, -1)
                val drawableEndId = attributeArray.getResourceId(R.styleable.CustomTextView_drawableEndCompat, -1)
                val drawableBottomId = attributeArray.getResourceId(R.styleable.CustomTextView_drawableBottomCompat, -1)
                val drawableTopId = attributeArray.getResourceId(R.styleable.CustomTextView_drawableTopCompat, -1)

                if (drawableStartId != -1)
                    drawableStart = AppCompatResources.getDrawable(context, drawableStartId)
                if (drawableEndId != -1)
                    drawableEnd = AppCompatResources.getDrawable(context, drawableEndId)
                if (drawableBottomId != -1)
                    drawableBottom = AppCompatResources.getDrawable(context, drawableBottomId)
                if (drawableTopId != -1)
                    drawableTop = AppCompatResources.getDrawable(context, drawableTopId)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                // to support rtl
                setCompoundDrawablesRelativeWithIntrinsicBounds(drawableStart, drawableTop, drawableEnd, drawableBottom)
            }

            attributeArray.recycle()
        }
    }

    fun setTextInputLayout(textInputLayout : TextInputLayout, useSelfListener : Boolean = true) {
        Log.e("setTextInputLayout","${this.isEnabled} ${this.text}")
        mTextInputLayout = textInputLayout
        when(this.isEnabled) {
            true -> {
                setUpperHintColorFocusBlue(textInputLayout)
                if (useSelfListener) this.setListener(textInputLayout, enterFocus = {}, leaveFocus = {})
            }
            false -> {
                setUpperHintColorFocusGray(textInputLayout)
            }
        }
    }

    fun getTextInputLayout() : TextInputLayout{
        return mTextInputLayout!!
    }

    fun setListener(textInputLayout : TextInputLayout,enterFocus: (v : View) -> Unit,leaveFocus : () -> Unit){
        this.onFocusChangeListener = object : OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                when(hasFocus) {
                    true -> {
                        enterFocus(view)
                        Log.e("setListener","hasFocus-true")
                        Log.e("setListener","text-${this@CustomEditText}")
                    }
                    false -> {
                        leaveFocus()
                        Log.e("setListener","hasFocus-false")
                        if(this@CustomEditText.text.toString().isNullOrBlank()) {
                            Log.e("setListener","isNullOrBlank")
                            Observable.timer(50, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                ViewCompat.setBackgroundTintList(this@CustomEditText, ColorStateList.valueOf(
                                    ContextCompat.getColor(context, R.color.gray
                                )))
                                setUpperHintColor(textInputLayout,this@CustomEditText)
                            }
                        }
                        else{
                            Log.e("setListener","Not isNullOrBlank")
                            Observable.timer(50, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                ViewCompat.setBackgroundTintList(this@CustomEditText, ColorStateList.valueOf(
                                    ContextCompat.getColor(context, R.color.blue
                                )))
                                setUpperHintColor(textInputLayout,this@CustomEditText)
                            }

                        }
                    }
                }
            }
        }
    }

    fun setUpperHintColor(textInputLayout : TextInputLayout,textInputEditText : TextInputEditText) {
        try {
            val field = textInputLayout.javaClass.getDeclaredField("defaultHintTextColor")
            //val field = textInputLayout.javaClass.getDeclaredField("focusedTextColor")
            field.isAccessible = true
            val states = arrayOf(intArrayOf())
            val colors : Any
            if(textInputEditText.text!!.isNotEmpty()) {
                colors = intArrayOf(ContextCompat.getColor(context, R.color.blue))
            }
            else{
                colors = intArrayOf(ContextCompat.getColor(context, R.color.gray))
            }

            val myList = ColorStateList(states, colors)
            field.set(textInputLayout, myList)

            val method = textInputLayout.javaClass.getDeclaredMethod("updateLabelState", Boolean::class.javaPrimitiveType)
            method.isAccessible = true
            method.invoke(textInputLayout, true)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setUpperHintColorFocusGray(textInputLayout : TextInputLayout){
        try {
            //val field = textInputLayout.javaClass.getDeclaredField("defaultHintTextColor")
            val field = textInputLayout.javaClass.getDeclaredField("focusedTextColor")
            field.isAccessible = true
            val states = arrayOf(intArrayOf())
            val colors : Any
            colors = intArrayOf(ContextCompat.getColor(context, R.color.gray))

            val myList = ColorStateList(states, colors)
            field.set(textInputLayout, myList)

            val method = textInputLayout.javaClass.getDeclaredMethod("updateLabelState", Boolean::class.javaPrimitiveType)
            method.isAccessible = true
            method.invoke(textInputLayout, true)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setUpperHintColorFocusBlue(textInputLayout : TextInputLayout){
        try {
            //val field = textInputLayout.javaClass.getDeclaredField("defaultHintTextColor")
            val field = textInputLayout.javaClass.getDeclaredField("focusedTextColor")
            field.isAccessible = true
            val states = arrayOf(intArrayOf())
            val colors : Any
            colors = intArrayOf(ContextCompat.getColor(context, R.color.blue))

            val myList = ColorStateList(states, colors)
            field.set(textInputLayout, myList)

            val method = textInputLayout.javaClass.getDeclaredMethod("updateLabelState", Boolean::class.javaPrimitiveType)
            method.isAccessible = true
            method.invoke(textInputLayout, true)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
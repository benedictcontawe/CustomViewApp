package com.example.customview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.custom_edit_text.view.*

open class CustomEditText : ConstraintLayout {

    //protected var mTextInputLayout: TextInputLayout? = null

    constructor(context : Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        initAttrs(context, attrs)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr : Int) : super(context, attrs, defStyleAttr){
        initAttrs(context, attrs)
    }

    init {

    }

    private fun initAttrs(context: Context, attrs: AttributeSet) {
        View.inflate(context, R.layout.custom_edit_text,this)

        if(attrs != null){
            val attributeArray : TypedArray = context.obtainStyledAttributes(attrs,R.styleable.CustomTextView)

            var drawableStart: Drawable? = null
            var drawableEnd: Drawable? = null
            var drawableBottom: Drawable? = null
            var drawableTop: Drawable? = null

            tv_hint.setText(attributeArray.getString(R.styleable.CustomTextView_hint))

            et_text.setHint(attributeArray.getString(R.styleable.CustomTextView_hint))
            et_text.setText(attributeArray.getString(R.styleable.CustomTextView_text))

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                drawableTop = attributeArray.getDrawable(R.styleable.CustomTextView_drawableTopCompat)
                drawableStart = attributeArray.getDrawable(R.styleable.CustomTextView_drawableStartCompat)
                drawableEnd = attributeArray.getDrawable(R.styleable.CustomTextView_drawableEndCompat)
                drawableBottom = attributeArray.getDrawable(R.styleable.CustomTextView_drawableBottomCompat)

                et_text.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableStart,drawableTop,drawableEnd,drawableBottom)
            }
            else{
                val drawableTopId: Int = attributeArray.getResourceId(R.styleable.CustomTextView_drawableTopCompat,-1)
                val drawableStartId: Int = attributeArray.getResourceId(R.styleable.CustomTextView_drawableStartCompat, -1)
                val drawableEndId: Int = attributeArray.getResourceId(R.styleable.CustomTextView_drawableEndCompat, -1)
                val drawableBottomId: Int = attributeArray.getResourceId(R.styleable.CustomTextView_drawableBottomCompat, -1)

                if (drawableTopId != -1) drawableTop = AppCompatResources.getDrawable(context, drawableTopId)
                if (drawableStartId != -1) drawableStart = AppCompatResources.getDrawable(context, drawableStartId)
                if (drawableEndId != -1) drawableEnd = AppCompatResources.getDrawable(context, drawableEndId)
                if (drawableBottomId != -1) drawableEnd = AppCompatResources.getDrawable(context, drawableBottomId)

                et_text.setCompoundDrawablesWithIntrinsicBounds(drawableStart,drawableTop,drawableEnd,drawableBottom);
            }

            et_text.imeOptions = attributeArray.getInt(R.styleable.CustomTextView_android_imeOptions, 0)
            et_text.inputType = attributeArray.getInt(R.styleable.CustomTextView_android_inputType, 0)
            et_text.maxLines = attributeArray.getInt(R.styleable.CustomTextView_android_maxLines,1)
            et_text.nextFocusForwardId = attributeArray.getResourceId(R.styleable.CustomTextView_android_nextFocusForward,0)
            et_text.nextFocusUpId = attributeArray.getResourceId(R.styleable.CustomTextView_android_nextFocusUp,0)
            et_text.nextFocusLeftId = attributeArray.getResourceId(R.styleable.CustomTextView_android_nextFocusLeft,0)

            //v_line.setBackgroundColor(ContextCompat.getColor(context,R.color.text_gray))

            tv_error.setText(attributeArray.getString(R.styleable.CustomTextView_error))


            if(attributeArray.getBoolean(R.styleable.CustomTextView_android_enabled,true)){
                //tv_hint.visibility = View.INVISIBLE
                //et_text.isEnabled
                //v_line
                //tv_error
            }
            else{
                et_text.isEnabled = false
            }

            initView(attributeArray.getString(R.styleable.CustomTextView_hint),attributeArray.getString(R.styleable.CustomTextView_text))

            attributeArray.recycle()
        }
    }

    private fun initView(hint : String?,text : String?) {
        Log.d("initView","${hint}-${text}")
        if(TextUtils.isEmpty(text)){
            tv_hint.setVisibility(INVISIBLE)
            et_text.setHintTextColor(ContextCompat.getColor(context,R.color.text_gray))
            v_line.setBackgroundColor(ContextCompat.getColor(context,R.color.text_gray))
            Log.d("initView","if")
        }
        else {
            tv_hint.setVisibility(VISIBLE)
            et_text.setHintTextColor(ContextCompat.getColor(context,R.color.blue))
            v_line.setBackgroundColor(ContextCompat.getColor(context,R.color.blue))
            Log.d("initView","else")
        }
    }

    //region setter and getter for Hint, Error, Text and Line
    fun setHint(hint: String) {
        tv_hint.text = hint
        et_text.hint = hint
        initView(tv_hint.text.toString(),et_text.text.toString())
    }

    fun getHint(): String {
        return tv_hint.text.toString()
    }

    fun setText(text: String?) {
        et_text.setText(text)
        et_text.setSelection(et_text.text.length)
        initView(tv_hint.text.toString(),et_text.text.toString())
    }

    fun getText(): String {
        return et_text.text.toString()
    }

    fun setLineStateColour(color: Int) {
        //tv_hint.setTextColor(color)
        v_line.setBackgroundColor(color)
    }

    fun getLineStateColour() : Int{
        var color = Color.TRANSPARENT
        val background = v_line.background
        return if(background is ColorDrawable) background.color else color
    }

    fun setTextError(error: String) {
        v_line.setBackgroundColor(ContextCompat.getColor(context,R.color.red))
        tv_error.text = error
    }

    fun getTextError(error: String): String {
        return tv_error.text.toString()
    }
    //endregion
    //region Normal Method
    open fun clearErrors() {
        setLineStateColour(ContextCompat.getColor(context,R.color.text_gray))
        tv_error.setText(null)
    }

    open fun clearText(){
        et_text.getText().clear()
        et_text.setSelection(0)
        initView(tv_hint.text.toString(),et_text.text.toString())
    }
    //endregion

    fun setListener(onTextChanged: ()-> Unit = {}, enterFocus: ()-> Unit = {}, leaveFocus: ()-> Unit = {}){
        et_text.setOnFocusChangeListener(object  : OnFocusChangeListener{
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                when(hasFocus) {
                    true -> {
                        et_text.setSelection(et_text.text.length)
                        if(getLineStateColour() != ContextCompat.getColor(context,R.color.red))
                            setLineStateColour(ContextCompat.getColor(context,R.color.blue))

                        et_text.setHintTextColor(Color.TRANSPARENT)
                        if(TextUtils.isEmpty(et_text.getText())){
                            tv_hint.setVisibility(VISIBLE)
                            tv_hint.setTextColor(ContextCompat.getColor(context,R.color.blue))
                        }
                        else{
                            tv_hint.setVisibility(VISIBLE)
                            tv_hint.setTextColor(ContextCompat.getColor(context,R.color.blue))
                            et_text.setHintTextColor(ContextCompat.getColor(context,R.color.blue))
                        }
                        enterFocus()
                    }
                    false -> {
                        et_text.setHintTextColor(ContextCompat.getColor(context,R.color.text_gray))
                        if(TextUtils.isEmpty(et_text.getText())){
                            tv_hint.setVisibility(INVISIBLE)
                            tv_hint.setTextColor(ContextCompat.getColor(context,R.color.text_gray))

                            if(getLineStateColour() != ContextCompat.getColor(context,R.color.red))
                                setLineStateColour(ContextCompat.getColor(context,R.color.text_gray))
                        }
                        else{
                            tv_hint.setVisibility(VISIBLE)
                            tv_hint.setTextColor(ContextCompat.getColor(context,R.color.blue))

                            if(getLineStateColour() != ContextCompat.getColor(context,R.color.red))
                                setLineStateColour(ContextCompat.getColor(context,R.color.blue))
                        }
                        leaveFocus()
                    }
                }
            }
        })

        et_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if((count < before)){
                    onTextChanged()
                }
            }
            override fun afterTextChanged(s: Editable?) {
                when(TextUtils.isEmpty(et_text.getText())){
                    true -> {
                        et_text.setHintTextColor(Color.TRANSPARENT)

                        //tv_hint.setVisibility(INVISIBLE)
                        //v_line.setBackgroundColor(ContextCompat.getColor(context,R.color.text_gray))
                    }
                    false -> {
                        et_text.setHintTextColor(ContextCompat.getColor(context,R.color.blue))

                        //tv_hint.setVisibility(VISIBLE)
                        //tv_hint.setTextColor(ContextCompat.getColor(context,R.color.blue))
                    }
                }
            }
        })
    }
}
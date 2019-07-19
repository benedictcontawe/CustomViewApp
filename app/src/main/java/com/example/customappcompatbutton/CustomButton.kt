package com.example.customappcompatbutton

import android.app.ActionBar
import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

class  CustomButton : AppCompatButton {

    constructor(context : Context?) : super(context)

    constructor(context : Context?, attrs : AttributeSet?) : super(context,attrs)

    constructor(context: Context?, attrs : AttributeSet, defStyleAttr : Int) : super(context,attrs,defStyleAttr)

    init {
        setBackgroundResource(R.drawable.custom_button)
        setTypeface(typeface, Typeface.BOLD)
        setAllCaps(false) //isAllCaps = false
        setTextColor(ContextCompat.getColor(context,R.color.white))

        this.layoutParams = LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, 100)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(object : DebouncedOnClickListener() {
            override fun onDebouncedClick(v: View) {
                //l!!.onClick(v)
                l?.onClick(v)
            }
        })
    }
}
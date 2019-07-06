package com.example.customappcompatbutton.CustomView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.example.customappcompatbutton.Formatter.ScrollableCalendarPickerDialog

class CalendarDateEditText : CustomEditText {

    constructor(context: Context?) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    fun setListener(context: Context, getText: ()-> Unit){
        this.setOnClickListener {
            this.setText(ScrollableCalendarPickerDialog.showCalendar(context))
        }

        this.setOnFocusChangeListener{
                view: View, hasFocus: Boolean ->
            if (hasFocus){
                this.setText(ScrollableCalendarPickerDialog.showCalendar(context))
            }
        }
    }
}
package com.example.customappcompatbutton.CustomView

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.View
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CalendarDateEditText : CustomEditText {

    constructor(context: Context?) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    fun setListener(context : Context,calendarDateEditText : CalendarDateEditText){
        this.isFocusable = false

        this.setOnClickListener {
            showScrollableCalendar(context, calendarDateEditText)
        }

        this.setOnFocusChangeListener{
                view: View, hasFocus: Boolean ->
            if (hasFocus){
                showScrollableCalendar(context, calendarDateEditText)
            }
        }
    }

    private fun showScrollableCalendar(context : Context,calendarDateEditText : CalendarDateEditText) {
        var stringToBeReturn : String? = null
        val currentDate: Calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(context, /*android.R.style.Theme_Holo_Light_Panel*/
            AlertDialog.THEME_HOLO_LIGHT, DatePickerDialog.OnDateSetListener { datePicker, setYear, setMonth, setDay ->
                var setmonth = setMonth
                setmonth += 1

                val datetoformat = setmonth.toString() + " " + setDay.toString() + ", " + setYear.toString()

                val apiFormat = SimpleDateFormat("MMM/dd/yyyy")
                val format = SimpleDateFormat("M d, yyyy")
                val displayFormat = SimpleDateFormat("MMM dd, yyyy")
                try {
                    var date = format.parse(datetoformat)
                    stringToBeReturn = displayFormat.format(date)
                    this.setText(stringToBeReturn)
                    setUpperHintColor(getTextInputLayout(), calendarDateEditText)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

            }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH)) //TODO: <- this (convert into mmmm)

        datePickerDialog.datePicker.calendarViewShown = false
        datePickerDialog.show()
    }
}
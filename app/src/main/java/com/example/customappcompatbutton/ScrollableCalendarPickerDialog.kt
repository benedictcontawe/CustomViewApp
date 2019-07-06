package com.example.customappcompatbutton

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ScrollableCalendarPickerDialog {

    lateinit var context : Context
    lateinit var customEditText : CustomEditText

    constructor(context : Context, customEditText : CustomEditText){
        this.context = context;
    }

    fun showCalendar(){
        val currentDate: Calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(context, /*android.R.style.Theme_Holo_Light_Panel*/
            AlertDialog.THEME_HOLO_LIGHT, DatePickerDialog.OnDateSetListener { datePicker, setYear, setMonth, setDay ->
                var setmonth = setMonth
                setmonth += 1

                val datetoformat = setmonth.toString() + " " + setDay.toString() + ", " + setYear.toString()

                val apiFormat = SimpleDateFormat("MMM/dd/yyyy")
                val format = SimpleDateFormat("M d, yyyy")
                //val displayFormat = SimpleDateFormat("MMM dd, yyyy")
                try {
                    var date = format.parse(datetoformat)
                    //et_reg_per_info_bday.setText(displayFormat.format(date))
                    customEditText.setText(apiFormat.format(date))
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

            }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH)) //TODO: <- this (convert into mmmm)

        datePickerDialog.datePicker.calendarViewShown = false
        datePickerDialog.show()
    }
}
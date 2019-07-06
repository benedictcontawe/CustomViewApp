package com.example.customappcompatbutton.Formatter

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import com.example.customappcompatbutton.CustomView.CustomEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ScrollableCalendarPickerDialog {

    companion object{
        fun showCalendar(context : Context) : String{
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
                    } catch (e: ParseException) {
                        e.printStackTrace()
                    }

                }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH)) //TODO: <- this (convert into mmmm)

            datePickerDialog.datePicker.calendarViewShown = false
            datePickerDialog.show()

            return stringToBeReturn?:""
        }
    }
}
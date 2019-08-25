package com.example.customscrollablecalendar

interface CalendarListener {

    fun onClick(item : CalendarViewModel, position : Int, daySelected : Int)
}
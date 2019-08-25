package com.example.customscrollablecalendar

class CalendarModel {

    var calendarMonth : Int? = null
    var calendarFirstWeekDay : Int? = null
    var calendarMaximumDay: Int? = null
    var calendarYear : Int? = null
    var calendarEvent : Int? = null

    constructor(setMonth : Int, setFirstWeekDay : Int, setMaxDay : Int, setYear : Int?, setEvent : Int?) {
        calendarMonth = setMonth
        calendarFirstWeekDay = setFirstWeekDay
        calendarMaximumDay = setMaxDay
        calendarYear = setYear
        calendarEvent = setEvent
    }
}
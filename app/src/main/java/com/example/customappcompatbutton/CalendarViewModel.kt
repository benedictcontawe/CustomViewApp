package com.example.customappcompatbutton

class CalendarViewModel {

    var calendarMonth : Int? = null
    var calendarYear : Int? = null

    var calendarDay1 : Int? = null
    var calendarDay2 : Int? = null
    var calendarDay3 : Int? = null
    var calendarDay4 : Int? = null
    var calendarDay5 : Int? = null
    var calendarDay6 : Int? = null
    var calendarDay7 : Int? = null
    var calendarDay8 : Int? = null
    var calendarDay9 : Int? = null
    var calendarDay10 : Int? = null
    var calendarDay11 : Int? = null
    var calendarDay12 : Int? = null
    var calendarDay13 : Int? = null
    var calendarDay14 : Int? = null
    var calendarDay15 : Int? = null
    var calendarDay16 : Int? = null
    var calendarDay17 : Int? = null
    var calendarDay18 : Int? = null
    var calendarDay19 : Int? = null
    var calendarDay20 : Int? = null
    var calendarDay21 : Int? = null
    var calendarDay22 : Int? = null
    var calendarDay23 : Int? = null
    var calendarDay24 : Int? = null
    var calendarDay25 : Int? = null
    var calendarDay26 : Int? = null
    var calendarDay27 : Int? = null
    var calendarDay28 : Int? = null
    var calendarDay29 : Int? = null
    var calendarDay30 : Int? = null
    var calendarDay31 : Int? = null
    var calendarDay32 : Int? = null
    var calendarDay33 : Int? = null
    var calendarDay34 : Int? = null
    var calendarDay35 : Int? = null
    var calendarDay36 : Int? = null
    var calendarDay37 : Int? = null
    var calendarDay38 : Int? = null
    var calendarDay39 : Int? = null
    var calendarDay40 : Int? = null
    var calendarDay41 : Int? = null
    var calendarDay42 : Int? = null

    var event : Int? = null

    var selectedFromDay : Int? = null
    var selectedToDay : Int? = null
    var extendSelectedStartDay : Boolean? = null
    var extendSelectedEndDay : Boolean? = null

    companion object {
        const val Null = -1
        const val Past = 0
        const val Present = 1
        const val Future = 2

        fun getEvent(event : Int) : String {
            return when(event) {
                Past -> { "Past" }
                Present -> { "Present" }
                Future -> { "Future" }
                else -> { "Unknown" }
            }
        }
    }

    constructor(setMonth : Int, setYear : Int?, setEvent : Int?){
        calendarMonth = setMonth
        calendarYear = setYear

        event = setEvent

        calendarDay1 = 0
        calendarDay2 = 0
        calendarDay3 = 0
        calendarDay4 = 0
        calendarDay5 = 0
        calendarDay6 = 0
        calendarDay7 = 0
        calendarDay8 = 0
        calendarDay9 = 0
        calendarDay10 = 0
        calendarDay11 = 0
        calendarDay12 = 0
        calendarDay13 = 0
        calendarDay14 = 0
        calendarDay15 = 0
        calendarDay16 = 0
        calendarDay17 = 0
        calendarDay18 = 0
        calendarDay19 = 0
        calendarDay20 = 0
        calendarDay21 = 0
        calendarDay22 = 0
        calendarDay23 = 0
        calendarDay24 = 0
        calendarDay25 = 0
        calendarDay26 = 0
        calendarDay27 = 0
        calendarDay28 = 0
        calendarDay29 = 0
        calendarDay30 = 0
        calendarDay31 = 0
        calendarDay32 = 0
        calendarDay33 = 0
        calendarDay34 = 0
        calendarDay35 = 0
        calendarDay36 = 0
        calendarDay37 = 0
        calendarDay38 = 0
        calendarDay39 = 0
        calendarDay40 = 0
        calendarDay41 = 0
        calendarDay42 = 0

        selectedFromDay = 0
        selectedToDay = 0

        extendSelectedStartDay = false
        extendSelectedEndDay = false
    }

}

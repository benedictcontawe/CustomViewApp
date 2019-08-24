package com.example.customappcompatbutton

import java.util.*

class CalendarDateFormatter {

    companion object {
        val arrayNumberMonth = arrayOf("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
        val arrayMonth = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        val arrayFullMonth = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        val arrayDay = arrayOf("01", "02", "03", "04", "05", "06", "07", "08", "09")
        val dateWeek = arrayOf("", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

        fun setMonth(month: Int?, isWord: Boolean, isFullWord: Boolean) : String {
            if (month == null) {
                return ""
            }
            return when (isFullWord) {
                true -> {
                    if (isWord) arrayFullMonth[month.minus(1)] else arrayNumberMonth[month]
                }
                false -> {
                    if (isWord) arrayMonth[month.minus(1)] else arrayNumberMonth[month.minus(1)]
                }
            }
        }

        fun setDay(day: Int, isTwoDigit: Boolean): String {
            if (day <= 9 && isTwoDigit) {
                return arrayDay[day - 1]
            } else {
                return day.toString()
            }
        }

        private fun CheckMaxDay(currentDay: Int, MaximumDay: Int): Int {
            return if (currentDay <= MaximumDay) currentDay else 0
        }

        /**getDate : Int return YYYYMMDD*/
        fun getDate(month : Int, day : Int, year : Int) : Int {
            val subMonth = setMonth(month - 1, false, false)
            val subDay = setDay(day, true)
            return ("$year$subMonth$subDay").toInt()
        }

        fun setScrollableCalendar(setMaxPastMonth: Int, setDayOfMonth : Int): MutableList<CalendarViewModel> {
            //region First Set-Up
            val previousDate: Calendar = Calendar.getInstance()
            val currentDate: Calendar = Calendar.getInstance()
            val futureDate: Calendar = Calendar.getInstance()
            val listInit = mutableListOf<CalendarModel>()

            previousDate.set(Calendar.DAY_OF_MONTH, setDayOfMonth)
            previousDate.add(Calendar.MONTH, -1) //Minus 1 month

            currentDate.set(Calendar.DAY_OF_MONTH, setDayOfMonth)
            //currentDate.add(Calendar.DATE, 1) //Add 1 day

            futureDate.set(Calendar.DAY_OF_MONTH, setDayOfMonth)
            futureDate.add(Calendar.MONTH, 1) //Add 1 month

            listInit.clear()

            for (i in 0.. setMaxPastMonth + 1) {
                if (i == 0) {
                    listInit.add(CalendarModel
                        (
                        setMonth(futureDate.get(Calendar.MONTH), false, false).toInt(),
                        futureDate.get(Calendar.DAY_OF_WEEK),
                        futureDate.getActualMaximum(Calendar.DATE),
                        futureDate.get(Calendar.YEAR),
                        CalendarViewModel.Future
                        )
                    )
                } else if (i == 1) {
                    listInit.add(CalendarModel
                        (
                        setMonth(currentDate.get(Calendar.MONTH), false, false).toInt(),
                        currentDate.get(Calendar.DAY_OF_WEEK),
                        currentDate.getActualMaximum(Calendar.DATE),
                        currentDate.get(Calendar.YEAR),
                        CalendarViewModel.Present
                        )
                    )
                } else {
                    listInit.add(CalendarModel
                        (
                        setMonth(previousDate.get(Calendar.MONTH), false, false).toInt(),
                        previousDate.get(Calendar.DAY_OF_WEEK),
                        previousDate.getActualMaximum(Calendar.DATE),
                        previousDate.get(Calendar.YEAR),
                        CalendarViewModel.Past
                        )
                    )
                    previousDate.add(Calendar.MONTH, -1) //Minus 1 month
                }

            }
            //endregion

            val list = mutableListOf<CalendarViewModel>()
            list.clear()
            listInit.map {
                list.add(CalendarViewModel(it.calendarMonth?:0, it.calendarYear?:0, it.calendarEvent))
            }

            for (i in 0 until list.size) {
                when (listInit[i].calendarFirstWeekDay) {
                    1 -> {
                        list[i].calendarDay1 = 1
                        list[i].calendarDay2 = 2
                        list[i].calendarDay3 = 3
                        list[i].calendarDay4 = 4
                        list[i].calendarDay5 = 5
                        list[i].calendarDay6 = 6
                        list[i].calendarDay7 = 7

                        list[i].calendarDay8 = CheckMaxDay(8, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay9 = CheckMaxDay(9, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay10 = CheckMaxDay(10, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay11 = CheckMaxDay(11, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay12 = CheckMaxDay(12, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay13 = CheckMaxDay(13, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay14 = CheckMaxDay(14, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay15 = CheckMaxDay(15, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay16 = CheckMaxDay(16, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay17 = CheckMaxDay(17, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay18 = CheckMaxDay(18, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay19 = CheckMaxDay(19, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay20 = CheckMaxDay(20, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay21 = CheckMaxDay(21, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay22 = CheckMaxDay(22, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay23 = CheckMaxDay(23, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay24 = CheckMaxDay(24, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay25 = CheckMaxDay(25, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay26 = CheckMaxDay(26, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay27 = CheckMaxDay(27, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay28 = CheckMaxDay(28, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay29 = CheckMaxDay(29, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay30 = CheckMaxDay(30, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay31 = CheckMaxDay(31, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay32 = CheckMaxDay(32, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay33 = CheckMaxDay(33, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay34 = CheckMaxDay(34, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay35 = CheckMaxDay(35, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay36 = CheckMaxDay(36, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay37 = CheckMaxDay(37, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay38 = CheckMaxDay(38, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay39 = CheckMaxDay(39, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay40 = CheckMaxDay(40, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay41 = CheckMaxDay(41, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay42 = CheckMaxDay(42, listInit[i].calendarMaximumDay!!)
                    }
                    2 -> {
                        list[i].calendarDay1 = 0
                        list[i].calendarDay2 = 1
                        list[i].calendarDay3 = 2
                        list[i].calendarDay4 = 3
                        list[i].calendarDay5 = 4
                        list[i].calendarDay6 = 5
                        list[i].calendarDay7 = 6

                        list[i].calendarDay8 = CheckMaxDay(7, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay9 = CheckMaxDay(8, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay10 = CheckMaxDay(9, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay11 = CheckMaxDay(10, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay12 = CheckMaxDay(11, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay13 = CheckMaxDay(12, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay14 = CheckMaxDay(13, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay15 = CheckMaxDay(14, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay16 = CheckMaxDay(15, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay17 = CheckMaxDay(16, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay18 = CheckMaxDay(17, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay19 = CheckMaxDay(18, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay20 = CheckMaxDay(19, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay21 = CheckMaxDay(20, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay22 = CheckMaxDay(21, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay23 = CheckMaxDay(22, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay24 = CheckMaxDay(23, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay25 = CheckMaxDay(24, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay26 = CheckMaxDay(25, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay27 = CheckMaxDay(26, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay28 = CheckMaxDay(27, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay29 = CheckMaxDay(28, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay30 = CheckMaxDay(29, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay31 = CheckMaxDay(30, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay32 = CheckMaxDay(31, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay33 = CheckMaxDay(32, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay34 = CheckMaxDay(33, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay35 = CheckMaxDay(34, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay36 = CheckMaxDay(35, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay37 = CheckMaxDay(36, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay38 = CheckMaxDay(37, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay39 = CheckMaxDay(38, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay40 = CheckMaxDay(39, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay41 = CheckMaxDay(40, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay42 = CheckMaxDay(41, listInit[i].calendarMaximumDay!!)
                    }
                    3 -> {
                        list[i].calendarDay1 = 0
                        list[i].calendarDay2 = 0
                        list[i].calendarDay3 = 1
                        list[i].calendarDay4 = 2
                        list[i].calendarDay5 = 3
                        list[i].calendarDay6 = 4
                        list[i].calendarDay7 = 5

                        list[i].calendarDay8 = CheckMaxDay(6, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay9 = CheckMaxDay(7, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay10 = CheckMaxDay(8, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay11 = CheckMaxDay(9, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay12 = CheckMaxDay(10, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay13 = CheckMaxDay(11, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay14 = CheckMaxDay(12, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay15 = CheckMaxDay(13, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay16 = CheckMaxDay(14, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay17 = CheckMaxDay(15, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay18 = CheckMaxDay(16, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay19 = CheckMaxDay(17, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay20 = CheckMaxDay(18, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay21 = CheckMaxDay(19, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay22 = CheckMaxDay(20, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay23 = CheckMaxDay(21, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay24 = CheckMaxDay(22, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay25 = CheckMaxDay(23, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay26 = CheckMaxDay(24, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay27 = CheckMaxDay(25, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay28 = CheckMaxDay(26, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay29 = CheckMaxDay(27, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay30 = CheckMaxDay(28, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay31 = CheckMaxDay(29, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay32 = CheckMaxDay(30, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay33 = CheckMaxDay(31, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay34 = CheckMaxDay(32, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay35 = CheckMaxDay(33, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay36 = CheckMaxDay(34, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay37 = CheckMaxDay(35, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay38 = CheckMaxDay(36, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay39 = CheckMaxDay(37, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay40 = CheckMaxDay(38, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay41 = CheckMaxDay(39, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay42 = CheckMaxDay(40, listInit[i].calendarMaximumDay!!)
                    }
                    4 -> {
                        list[i].calendarDay1 = 0
                        list[i].calendarDay2 = 0
                        list[i].calendarDay3 = 0
                        list[i].calendarDay4 = 1
                        list[i].calendarDay5 = 2
                        list[i].calendarDay6 = 3
                        list[i].calendarDay7 = 4

                        list[i].calendarDay8 = CheckMaxDay(5, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay9 = CheckMaxDay(6, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay10 = CheckMaxDay(7, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay11 = CheckMaxDay(8, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay12 = CheckMaxDay(9, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay13 = CheckMaxDay(10, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay14 = CheckMaxDay(11, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay15 = CheckMaxDay(12, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay16 = CheckMaxDay(13, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay17 = CheckMaxDay(14, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay18 = CheckMaxDay(15, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay19 = CheckMaxDay(16, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay20 = CheckMaxDay(17, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay21 = CheckMaxDay(18, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay22 = CheckMaxDay(19, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay23 = CheckMaxDay(20, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay24 = CheckMaxDay(21, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay25 = CheckMaxDay(22, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay26 = CheckMaxDay(23, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay27 = CheckMaxDay(24, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay28 = CheckMaxDay(25, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay29 = CheckMaxDay(26, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay30 = CheckMaxDay(27, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay31 = CheckMaxDay(28, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay32 = CheckMaxDay(29, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay33 = CheckMaxDay(30, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay34 = CheckMaxDay(31, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay35 = CheckMaxDay(32, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay36 = CheckMaxDay(33, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay37 = CheckMaxDay(34, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay38 = CheckMaxDay(35, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay39 = CheckMaxDay(36, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay40 = CheckMaxDay(37, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay41 = CheckMaxDay(38, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay42 = CheckMaxDay(39, listInit[i].calendarMaximumDay!!)
                    }
                    5 -> {
                        list[i].calendarDay1 = 0
                        list[i].calendarDay2 = 0
                        list[i].calendarDay3 = 0
                        list[i].calendarDay4 = 0
                        list[i].calendarDay5 = 1
                        list[i].calendarDay6 = 2
                        list[i].calendarDay7 = 3

                        list[i].calendarDay8 = CheckMaxDay(4, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay9 = CheckMaxDay(5, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay10 = CheckMaxDay(6, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay11 = CheckMaxDay(7, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay12 = CheckMaxDay(8, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay13 = CheckMaxDay(9, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay14 = CheckMaxDay(10, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay15 = CheckMaxDay(11, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay16 = CheckMaxDay(12, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay17 = CheckMaxDay(13, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay18 = CheckMaxDay(14, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay19 = CheckMaxDay(15, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay20 = CheckMaxDay(16, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay21 = CheckMaxDay(17, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay22 = CheckMaxDay(18, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay23 = CheckMaxDay(19, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay24 = CheckMaxDay(20, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay25 = CheckMaxDay(21, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay26 = CheckMaxDay(22, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay27 = CheckMaxDay(23, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay28 = CheckMaxDay(24, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay29 = CheckMaxDay(25, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay30 = CheckMaxDay(26, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay31 = CheckMaxDay(27, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay32 = CheckMaxDay(28, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay33 = CheckMaxDay(29, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay34 = CheckMaxDay(30, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay35 = CheckMaxDay(31, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay36 = CheckMaxDay(32, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay37 = CheckMaxDay(33, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay38 = CheckMaxDay(34, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay39 = CheckMaxDay(35, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay40 = CheckMaxDay(36, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay41 = CheckMaxDay(37, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay42 = CheckMaxDay(38, listInit[i].calendarMaximumDay!!)
                    }
                    6 -> {
                        list[i].calendarDay1 = 0
                        list[i].calendarDay2 = 0
                        list[i].calendarDay3 = 0
                        list[i].calendarDay4 = 0
                        list[i].calendarDay5 = 0
                        list[i].calendarDay6 = 1
                        list[i].calendarDay7 = 2

                        list[i].calendarDay8 = CheckMaxDay(3, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay9 = CheckMaxDay(4, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay10 = CheckMaxDay(5, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay11 = CheckMaxDay(6, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay12 = CheckMaxDay(7, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay13 = CheckMaxDay(8, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay14 = CheckMaxDay(9, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay15 = CheckMaxDay(10, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay16 = CheckMaxDay(11, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay17 = CheckMaxDay(12, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay18 = CheckMaxDay(13, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay19 = CheckMaxDay(14, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay20 = CheckMaxDay(15, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay21 = CheckMaxDay(16, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay22 = CheckMaxDay(17, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay23 = CheckMaxDay(18, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay24 = CheckMaxDay(19, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay25 = CheckMaxDay(20, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay26 = CheckMaxDay(21, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay27 = CheckMaxDay(22, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay28 = CheckMaxDay(23, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay29 = CheckMaxDay(24, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay30 = CheckMaxDay(25, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay31 = CheckMaxDay(26, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay32 = CheckMaxDay(27, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay33 = CheckMaxDay(28, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay34 = CheckMaxDay(29, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay35 = CheckMaxDay(30, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay36 = CheckMaxDay(31, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay37 = CheckMaxDay(32, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay38 = CheckMaxDay(33, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay39 = CheckMaxDay(34, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay40 = CheckMaxDay(35, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay41 = CheckMaxDay(36, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay42 = CheckMaxDay(37, listInit[i].calendarMaximumDay!!)
                    }
                    7 -> {
                        list[i].calendarDay1 = 0
                        list[i].calendarDay2 = 0
                        list[i].calendarDay3 = 0
                        list[i].calendarDay4 = 0
                        list[i].calendarDay5 = 0
                        list[i].calendarDay6 = 0
                        list[i].calendarDay7 = 1

                        list[i].calendarDay8 = CheckMaxDay(2, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay9 = CheckMaxDay(3, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay10 = CheckMaxDay(4, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay11 = CheckMaxDay(5, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay12 = CheckMaxDay(6, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay13 = CheckMaxDay(7, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay14 = CheckMaxDay(8, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay15 = CheckMaxDay(9, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay16 = CheckMaxDay(10, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay17 = CheckMaxDay(11, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay18 = CheckMaxDay(12, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay19 = CheckMaxDay(13, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay20 = CheckMaxDay(14, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay21 = CheckMaxDay(15, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay22 = CheckMaxDay(16, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay23 = CheckMaxDay(17, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay24 = CheckMaxDay(18, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay25 = CheckMaxDay(19, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay26 = CheckMaxDay(20, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay27 = CheckMaxDay(21, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay28 = CheckMaxDay(22, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay29 = CheckMaxDay(23, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay30 = CheckMaxDay(24, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay31 = CheckMaxDay(25, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay32 = CheckMaxDay(26, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay33 = CheckMaxDay(27, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay34 = CheckMaxDay(28, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay35 = CheckMaxDay(29, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay36 = CheckMaxDay(30, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay37 = CheckMaxDay(31, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay38 = CheckMaxDay(32, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay39 = CheckMaxDay(33, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay40 = CheckMaxDay(34, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay41 = CheckMaxDay(35, listInit[i].calendarMaximumDay!!)
                        list[i].calendarDay42 = CheckMaxDay(36, listInit[i].calendarMaximumDay!!)
                    }
                }
            }

            list.reverse()

            return list
        }
    }
}
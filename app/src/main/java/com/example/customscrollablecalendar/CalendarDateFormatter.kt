package com.example.customscrollablecalendar

import java.util.*

class CalendarDateFormatter {

    companion object {
        private val arrayNumberMonth = arrayOf("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
        private val arrayMonth = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        private val arrayFullMonth = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        private val arrayDay = arrayOf("01", "02", "03", "04", "05", "06", "07", "08", "09")
        private val arrayWeek = arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        private val arrayFullWeek = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

        fun setMonth(month : Int?, isWord : Boolean, isFullWord : Boolean) : String {
            val selectedMonth : Int
            when (month) {
                null -> {
                    return ""
                }
                -1 -> {
                    selectedMonth = 11
                }
                12 -> {
                    selectedMonth = 0
                }
                else -> {
                    selectedMonth = month
                }
            }

            return when (isFullWord) {
                true -> {
                    if (isWord) arrayFullMonth[selectedMonth] else arrayNumberMonth[selectedMonth]
                }
                false -> {
                    if (isWord) arrayMonth[selectedMonth] else arrayNumberMonth[selectedMonth]
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

        private fun checkMaxDay(currentDay : Int, MaximumDay : Int) : Int {
            return if (currentDay <= MaximumDay) currentDay else 0
        }

        /** getDate : Int return YYYYMMDD */
        fun getDate(month : Int, day : Int, year : Int) : Int {
            val subMonth = setMonth(month - 1, false, false)
            val subDay = setDay(day, true)
            return ("$year$subMonth$subDay").toInt()
        }

        private lateinit var listInit : MutableList<CalendarModel>
        private lateinit var list : MutableList<CalendarViewModel>
        fun setScrollableCalendar(setMaxPastMonth : Int, setDayOfMonth : Int) : MutableList<CalendarViewModel> {
            //region First Set-Up for Calendar Model
            val previousDate: Calendar = Calendar.getInstance()
            val currentDate: Calendar = Calendar.getInstance()
            val futureDate: Calendar = Calendar.getInstance()
            listInit = mutableListOf<CalendarModel>()

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
                            setMonth(futureDate.get(Calendar.MONTH) - 1, false, false).toInt(),
                            futureDate.get(Calendar.DAY_OF_WEEK),
                            futureDate.getActualMaximum(Calendar.DATE),
                            futureDate.get(Calendar.YEAR),
                            CalendarViewModel.Future
                        )
                    )
                } else if (i == 1) {
                    listInit.add(CalendarModel
                        (
                            setMonth(currentDate.get(Calendar.MONTH) - 1, false, false).toInt(),
                            currentDate.get(Calendar.DAY_OF_WEEK),
                            currentDate.getActualMaximum(Calendar.DATE),
                            currentDate.get(Calendar.YEAR),
                            CalendarViewModel.Present
                        )
                    )
                } else {
                    listInit.add(CalendarModel
                        (
                            setMonth(previousDate.get(Calendar.MONTH) - 1, false, false).toInt(),
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
            //region Set-up for Calendar View Model
            list = mutableListOf<CalendarViewModel>()
            list.clear()
            listInit.map {
                list.add(CalendarViewModel(it.calendarMonth?:0, it.calendarYear?:0, it.calendarEvent))
            }

            for (i in 0 until list.size) {
                when (setDayOfMonth) {
                    Calendar.SUNDAY -> {
                        when (listInit[i].calendarFirstWeekDay) {
                            1 -> { calendarDayFirst(i) } //Sunday
                            2 -> { calendarDaySecond(i) } //Monday
                            3 -> { calendarDayThird(i) } //Tuesday
                            4 -> { calendarDayFourth(i) } //Wednesday
                            5 -> { calendarDayFifth(i) } //Thursday
                            6 -> { calendarDaySixth(i) } //Friday
                            7 -> { calendarDaySeventh(i) } //Saturday
                        }

                        list[i].calendarWeekDay1 = arrayWeek[0]
                        list[i].calendarWeekDay2 = arrayWeek[1]
                        list[i].calendarWeekDay3 = arrayWeek[2]
                        list[i].calendarWeekDay4 = arrayWeek[3]
                        list[i].calendarWeekDay5 = arrayWeek[4]
                        list[i].calendarWeekDay6 = arrayWeek[5]
                        list[i].calendarWeekDay7 = arrayWeek[6]
                    }
                    Calendar.MONDAY -> {
                        when (listInit[i].calendarFirstWeekDay) {
                            1 -> { calendarDaySixth(i) } //Saturday
                            2 -> { calendarDaySeventh(i) } //Sunday
                            3 -> { calendarDayFirst(i) } //Monday
                            4 -> { calendarDaySecond(i) } //Tuesday
                            5 -> { calendarDayThird(i) } //Wednesday
                            6 -> { calendarDayFourth(i) } //Thursday
                            7 -> { calendarDayFifth(i) } //Friday
                        }

                        list[i].calendarWeekDay1 = arrayWeek[1]
                        list[i].calendarWeekDay2 = arrayWeek[2]
                        list[i].calendarWeekDay3 = arrayWeek[3]
                        list[i].calendarWeekDay4 = arrayWeek[4]
                        list[i].calendarWeekDay5 = arrayWeek[5]
                        list[i].calendarWeekDay6 = arrayWeek[6]
                        list[i].calendarWeekDay7 = arrayWeek[0]
                    }
                    Calendar.TUESDAY -> {
                        when (listInit[i].calendarFirstWeekDay) {
                            1 -> { calendarDayFourth(i) } //Friday
                            2 -> { calendarDayFifth(i) } //Saturday
                            3 -> { calendarDaySixth(i) } //Sunday
                            4 -> { calendarDaySeventh(i) } //Monday
                            5 -> { calendarDayFirst(i) } //Tuesday
                            6 -> { calendarDaySecond(i) } //Wednesday
                            7 -> { calendarDayThird(i) } //Thursday
                        }

                        list[i].calendarWeekDay1 = arrayWeek[2]
                        list[i].calendarWeekDay2 = arrayWeek[3]
                        list[i].calendarWeekDay3 = arrayWeek[4]
                        list[i].calendarWeekDay4 = arrayWeek[5]
                        list[i].calendarWeekDay5 = arrayWeek[6]
                        list[i].calendarWeekDay6 = arrayWeek[0]
                        list[i].calendarWeekDay7 = arrayWeek[1]
                    }
                    Calendar.WEDNESDAY -> {
                        when (listInit[i].calendarFirstWeekDay) {
                            1 -> { calendarDaySecond(i) } //Thursday
                            2 -> { calendarDayThird(i) } //Friday
                            3 -> { calendarDayFourth(i) } //Saturday
                            4 -> { calendarDayFifth(i) } //Sunday
                            5 -> { calendarDaySixth(i) } //Monday
                            6 -> { calendarDaySeventh(i) } //Tuesday
                            7 -> { calendarDayFirst(i) } //Wednesday
                        }

                        list[i].calendarWeekDay1 = arrayWeek[3]
                        list[i].calendarWeekDay2 = arrayWeek[4]
                        list[i].calendarWeekDay3 = arrayWeek[5]
                        list[i].calendarWeekDay4 = arrayWeek[6]
                        list[i].calendarWeekDay5 = arrayWeek[0]
                        list[i].calendarWeekDay6 = arrayWeek[1]
                        list[i].calendarWeekDay7 = arrayWeek[2]
                    }
                    Calendar.THURSDAY -> {
                        when (listInit[i].calendarFirstWeekDay) {
                            1 -> { calendarDaySeventh(i) } //Wednesday
                            2 -> { calendarDayFirst(i) } //Thursday
                            3 -> { calendarDaySecond(i) } //Friday
                            4 -> { calendarDayThird(i) } //Saturday
                            5 -> { calendarDayFourth(i) } //Sunday
                            6 -> { calendarDayFifth(i) } //Monday
                            7 -> { calendarDaySixth(i) } //Tuesday
                        }

                        list[i].calendarWeekDay1 = arrayWeek[4]
                        list[i].calendarWeekDay2 = arrayWeek[5]
                        list[i].calendarWeekDay3 = arrayWeek[6]
                        list[i].calendarWeekDay4 = arrayWeek[0]
                        list[i].calendarWeekDay5 = arrayWeek[1]
                        list[i].calendarWeekDay6 = arrayWeek[2]
                        list[i].calendarWeekDay7 = arrayWeek[3]
                    }
                    Calendar.FRIDAY -> {
                        when (listInit[i].calendarFirstWeekDay) {
                            1 -> { calendarDayFifth(i) } //Tuesday
                            2 -> { calendarDaySixth(i) } //Wednesday
                            3 -> { calendarDaySeventh(i) } //Thursday
                            4 -> { calendarDayFirst(i) } //Friday
                            5 -> { calendarDaySecond(i) } //Saturday
                            6 -> { calendarDayThird(i) } //Sunday
                            7 -> { calendarDayFourth(i) } //Monday
                        }

                        list[i].calendarWeekDay1 = arrayWeek[5]
                        list[i].calendarWeekDay2 = arrayWeek[6]
                        list[i].calendarWeekDay3 = arrayWeek[0]
                        list[i].calendarWeekDay4 = arrayWeek[1]
                        list[i].calendarWeekDay5 = arrayWeek[2]
                        list[i].calendarWeekDay6 = arrayWeek[3]
                        list[i].calendarWeekDay7 = arrayWeek[4]
                    }
                    Calendar.SATURDAY -> {
                        when (listInit[i].calendarFirstWeekDay) {
                            1 -> { calendarDayThird(i) } //Monday
                            2 -> { calendarDayFourth(i) } //Tuesday
                            3 -> { calendarDayFifth(i) } //Wednesday
                            4 -> { calendarDaySixth(i) } //Thursday
                            5 -> { calendarDaySeventh(i) } //Friday
                            6 -> { calendarDayFirst(i) } //Saturday
                            7 -> { calendarDaySecond(i) } //Sunday
                        }

                        list[i].calendarWeekDay1 = arrayWeek[6]
                        list[i].calendarWeekDay2 = arrayWeek[0]
                        list[i].calendarWeekDay3 = arrayWeek[1]
                        list[i].calendarWeekDay4 = arrayWeek[2]
                        list[i].calendarWeekDay5 = arrayWeek[3]
                        list[i].calendarWeekDay6 = arrayWeek[4]
                        list[i].calendarWeekDay7 = arrayWeek[5]
                    }
                }
            }
            //endregion
            list.reverse()
            return list
        }

        private fun calendarDayFirst(position : Int) {
            list[position].calendarDay1 = 1
            list[position].calendarDay2 = 2
            list[position].calendarDay3 = 3
            list[position].calendarDay4 = 4
            list[position].calendarDay5 = 5
            list[position].calendarDay6 = 6
            list[position].calendarDay7 = 7

            list[position].calendarDay8 = checkMaxDay(8, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay9 = checkMaxDay(9, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay10 = checkMaxDay(10, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay11 = checkMaxDay(11, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay12 = checkMaxDay(12, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay13 = checkMaxDay(13, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay14 = checkMaxDay(14, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay15 = checkMaxDay(15, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay16 = checkMaxDay(16, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay17 = checkMaxDay(17, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay18 = checkMaxDay(18, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay19 = checkMaxDay(19, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay20 = checkMaxDay(20, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay21 = checkMaxDay(21, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay22 = checkMaxDay(22, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay23 = checkMaxDay(23, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay24 = checkMaxDay(24, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay25 = checkMaxDay(25, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay26 = checkMaxDay(26, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay27 = checkMaxDay(27, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay28 = checkMaxDay(28, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay29 = checkMaxDay(29, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay30 = checkMaxDay(30, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay31 = checkMaxDay(31, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay32 = checkMaxDay(32, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay33 = checkMaxDay(33, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay34 = checkMaxDay(34, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay35 = checkMaxDay(35, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay36 = checkMaxDay(36, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay37 = checkMaxDay(37, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay38 = checkMaxDay(38, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay39 = checkMaxDay(39, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay40 = checkMaxDay(40, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay41 = checkMaxDay(41, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay42 = checkMaxDay(42, listInit[position].calendarMaximumDay!!)
        }

        private fun calendarDaySecond(position : Int) {
            list[position].calendarDay1 = 0
            list[position].calendarDay2 = 1
            list[position].calendarDay3 = 2
            list[position].calendarDay4 = 3
            list[position].calendarDay5 = 4
            list[position].calendarDay6 = 5
            list[position].calendarDay7 = 6

            list[position].calendarDay8 = checkMaxDay(7, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay9 = checkMaxDay(8, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay10 = checkMaxDay(9, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay11 = checkMaxDay(10, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay12 = checkMaxDay(11, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay13 = checkMaxDay(12, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay14 = checkMaxDay(13, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay15 = checkMaxDay(14, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay16 = checkMaxDay(15, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay17 = checkMaxDay(16, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay18 = checkMaxDay(17, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay19 = checkMaxDay(18, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay20 = checkMaxDay(19, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay21 = checkMaxDay(20, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay22 = checkMaxDay(21, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay23 = checkMaxDay(22, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay24 = checkMaxDay(23, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay25 = checkMaxDay(24, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay26 = checkMaxDay(25, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay27 = checkMaxDay(26, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay28 = checkMaxDay(27, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay29 = checkMaxDay(28, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay30 = checkMaxDay(29, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay31 = checkMaxDay(30, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay32 = checkMaxDay(31, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay33 = checkMaxDay(32, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay34 = checkMaxDay(33, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay35 = checkMaxDay(34, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay36 = checkMaxDay(35, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay37 = checkMaxDay(36, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay38 = checkMaxDay(37, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay39 = checkMaxDay(38, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay40 = checkMaxDay(39, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay41 = checkMaxDay(40, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay42 = checkMaxDay(41, listInit[position].calendarMaximumDay!!)
        }

        private fun calendarDayThird(position : Int) {
            list[position].calendarDay1 = 0
            list[position].calendarDay2 = 0
            list[position].calendarDay3 = 1
            list[position].calendarDay4 = 2
            list[position].calendarDay5 = 3
            list[position].calendarDay6 = 4
            list[position].calendarDay7 = 5

            list[position].calendarDay8 = checkMaxDay(6, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay9 = checkMaxDay(7, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay10 = checkMaxDay(8, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay11 = checkMaxDay(9, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay12 = checkMaxDay(10, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay13 = checkMaxDay(11, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay14 = checkMaxDay(12, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay15 = checkMaxDay(13, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay16 = checkMaxDay(14, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay17 = checkMaxDay(15, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay18 = checkMaxDay(16, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay19 = checkMaxDay(17, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay20 = checkMaxDay(18, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay21 = checkMaxDay(19, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay22 = checkMaxDay(20, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay23 = checkMaxDay(21, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay24 = checkMaxDay(22, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay25 = checkMaxDay(23, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay26 = checkMaxDay(24, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay27 = checkMaxDay(25, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay28 = checkMaxDay(26, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay29 = checkMaxDay(27, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay30 = checkMaxDay(28, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay31 = checkMaxDay(29, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay32 = checkMaxDay(30, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay33 = checkMaxDay(31, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay34 = checkMaxDay(32, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay35 = checkMaxDay(33, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay36 = checkMaxDay(34, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay37 = checkMaxDay(35, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay38 = checkMaxDay(36, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay39 = checkMaxDay(37, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay40 = checkMaxDay(38, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay41 = checkMaxDay(39, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay42 = checkMaxDay(40, listInit[position].calendarMaximumDay!!)
        }

        private fun calendarDayFourth(position : Int) {
            list[position].calendarDay1 = 0
            list[position].calendarDay2 = 0
            list[position].calendarDay3 = 0
            list[position].calendarDay4 = 1
            list[position].calendarDay5 = 2
            list[position].calendarDay6 = 3
            list[position].calendarDay7 = 4

            list[position].calendarDay8 = checkMaxDay(5, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay9 = checkMaxDay(6, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay10 = checkMaxDay(7, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay11 = checkMaxDay(8, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay12 = checkMaxDay(9, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay13 = checkMaxDay(10, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay14 = checkMaxDay(11, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay15 = checkMaxDay(12, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay16 = checkMaxDay(13, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay17 = checkMaxDay(14, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay18 = checkMaxDay(15, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay19 = checkMaxDay(16, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay20 = checkMaxDay(17, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay21 = checkMaxDay(18, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay22 = checkMaxDay(19, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay23 = checkMaxDay(20, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay24 = checkMaxDay(21, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay25 = checkMaxDay(22, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay26 = checkMaxDay(23, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay27 = checkMaxDay(24, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay28 = checkMaxDay(25, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay29 = checkMaxDay(26, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay30 = checkMaxDay(27, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay31 = checkMaxDay(28, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay32 = checkMaxDay(29, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay33 = checkMaxDay(30, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay34 = checkMaxDay(31, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay35 = checkMaxDay(32, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay36 = checkMaxDay(33, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay37 = checkMaxDay(34, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay38 = checkMaxDay(35, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay39 = checkMaxDay(36, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay40 = checkMaxDay(37, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay41 = checkMaxDay(38, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay42 = checkMaxDay(39, listInit[position].calendarMaximumDay!!)
        }

        private fun calendarDayFifth(position : Int) {
            list[position].calendarDay1 = 0
            list[position].calendarDay2 = 0
            list[position].calendarDay3 = 0
            list[position].calendarDay4 = 0
            list[position].calendarDay5 = 1
            list[position].calendarDay6 = 2
            list[position].calendarDay7 = 3

            list[position].calendarDay8 = checkMaxDay(4, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay9 = checkMaxDay(5, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay10 = checkMaxDay(6, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay11 = checkMaxDay(7, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay12 = checkMaxDay(8, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay13 = checkMaxDay(9, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay14 = checkMaxDay(10, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay15 = checkMaxDay(11, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay16 = checkMaxDay(12, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay17 = checkMaxDay(13, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay18 = checkMaxDay(14, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay19 = checkMaxDay(15, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay20 = checkMaxDay(16, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay21 = checkMaxDay(17, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay22 = checkMaxDay(18, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay23 = checkMaxDay(19, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay24 = checkMaxDay(20, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay25 = checkMaxDay(21, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay26 = checkMaxDay(22, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay27 = checkMaxDay(23, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay28 = checkMaxDay(24, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay29 = checkMaxDay(25, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay30 = checkMaxDay(26, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay31 = checkMaxDay(27, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay32 = checkMaxDay(28, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay33 = checkMaxDay(29, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay34 = checkMaxDay(30, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay35 = checkMaxDay(31, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay36 = checkMaxDay(32, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay37 = checkMaxDay(33, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay38 = checkMaxDay(34, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay39 = checkMaxDay(35, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay40 = checkMaxDay(36, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay41 = checkMaxDay(37, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay42 = checkMaxDay(38, listInit[position].calendarMaximumDay!!)
        }

        private fun calendarDaySixth(position: Int) {
            list[position].calendarDay1 = 0
            list[position].calendarDay2 = 0
            list[position].calendarDay3 = 0
            list[position].calendarDay4 = 0
            list[position].calendarDay5 = 0
            list[position].calendarDay6 = 1
            list[position].calendarDay7 = 2

            list[position].calendarDay8 = checkMaxDay(3, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay9 = checkMaxDay(4, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay10 = checkMaxDay(5, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay11 = checkMaxDay(6, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay12 = checkMaxDay(7, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay13 = checkMaxDay(8, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay14 = checkMaxDay(9, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay15 = checkMaxDay(10, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay16 = checkMaxDay(11, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay17 = checkMaxDay(12, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay18 = checkMaxDay(13, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay19 = checkMaxDay(14, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay20 = checkMaxDay(15, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay21 = checkMaxDay(16, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay22 = checkMaxDay(17, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay23 = checkMaxDay(18, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay24 = checkMaxDay(19, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay25 = checkMaxDay(20, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay26 = checkMaxDay(21, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay27 = checkMaxDay(22, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay28 = checkMaxDay(23, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay29 = checkMaxDay(24, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay30 = checkMaxDay(25, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay31 = checkMaxDay(26, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay32 = checkMaxDay(27, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay33 = checkMaxDay(28, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay34 = checkMaxDay(29, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay35 = checkMaxDay(30, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay36 = checkMaxDay(31, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay37 = checkMaxDay(32, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay38 = checkMaxDay(33, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay39 = checkMaxDay(34, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay40 = checkMaxDay(35, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay41 = checkMaxDay(36, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay42 = checkMaxDay(37, listInit[position].calendarMaximumDay!!)
        }

        private fun calendarDaySeventh(position: Int) {
            list[position].calendarDay1 = 0
            list[position].calendarDay2 = 0
            list[position].calendarDay3 = 0
            list[position].calendarDay4 = 0
            list[position].calendarDay5 = 0
            list[position].calendarDay6 = 0
            list[position].calendarDay7 = 1

            list[position].calendarDay8 = checkMaxDay(2, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay9 = checkMaxDay(3, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay10 = checkMaxDay(4, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay11 = checkMaxDay(5, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay12 = checkMaxDay(6, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay13 = checkMaxDay(7, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay14 = checkMaxDay(8, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay15 = checkMaxDay(9, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay16 = checkMaxDay(10, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay17 = checkMaxDay(11, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay18 = checkMaxDay(12, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay19 = checkMaxDay(13, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay20 = checkMaxDay(14, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay21 = checkMaxDay(15, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay22 = checkMaxDay(16, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay23 = checkMaxDay(17, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay24 = checkMaxDay(18, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay25 = checkMaxDay(19, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay26 = checkMaxDay(20, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay27 = checkMaxDay(21, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay28 = checkMaxDay(22, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay29 = checkMaxDay(23, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay30 = checkMaxDay(24, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay31 = checkMaxDay(25, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay32 = checkMaxDay(26, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay33 = checkMaxDay(27, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay34 = checkMaxDay(28, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay35 = checkMaxDay(29, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay36 = checkMaxDay(30, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay37 = checkMaxDay(31, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay38 = checkMaxDay(32, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay39 = checkMaxDay(33, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay40 = checkMaxDay(34, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay41 = checkMaxDay(35, listInit[position].calendarMaximumDay!!)
            list[position].calendarDay42 = checkMaxDay(36, listInit[position].calendarMaximumDay!!)
        }
    }
}
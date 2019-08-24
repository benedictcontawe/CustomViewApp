package com.example.customappcompatbutton

import org.junit.*

import org.junit.Assert.*

class CalendarUnitTest {

    private var maxPastMonth : Int? = null

    @Before
    fun before() {
        maxPastMonth = 12
    }

    @BeforeClass
    fun beforeClass() {

    }

    @Test
    fun TestCalendarList() {
        val list = CalendarDateFormatter.setScrollableCalendar(maxPastMonth?:0)

        for (i in 0..list.size - 1) {
            System.out.println("For-list-reverse " +
                    "${list[i].calendarMonth} ${list[i].calendarYear}..." +
                    "${list[i].selectedFromDay}-${list[i].selectedToDay}..." +
                    "${list[i].event}..." +
                    "${list[i].extendSelectedStartDay}-${list[i].extendSelectedEndDay}..." +
                    "${list[i].calendarDay1}," +
                    "${list[i].calendarDay2}," +
                    "${list[i].calendarDay3}," +
                    "${list[i].calendarDay4}," +
                    "${list[i].calendarDay5}," +
                    "${list[i].calendarDay6}," +
                    "${list[i].calendarDay7}," +
                    "${list[i].calendarDay8}," +
                    "${list[i].calendarDay9}," +
                    "${list[i].calendarDay10}," +
                    "${list[i].calendarDay11}," +
                    "${list[i].calendarDay12}," +
                    "${list[i].calendarDay13}," +
                    "${list[i].calendarDay14}," +
                    "${list[i].calendarDay15}," +
                    "${list[i].calendarDay16}," +
                    "${list[i].calendarDay17}," +
                    "${list[i].calendarDay18}," +
                    "${list[i].calendarDay19}," +
                    "${list[i].calendarDay20}," +
                    "${list[i].calendarDay21}," +
                    "${list[i].calendarDay22}," +
                    "${list[i].calendarDay23}," +
                    "${list[i].calendarDay24}," +
                    "${list[i].calendarDay25}," +
                    "${list[i].calendarDay26}," +
                    "${list[i].calendarDay27}," +
                    "${list[i].calendarDay28}," +
                    "${list[i].calendarDay29}," +
                    "${list[i].calendarDay30}," +
                    "${list[i].calendarDay31}," +
                    "${list[i].calendarDay32}," +
                    "${list[i].calendarDay33}," +
                    "${list[i].calendarDay34}," +
                    "${list[i].calendarDay35}," +
                    "${list[i].calendarDay36}," +
                    "${list[i].calendarDay37}," +
                    "${list[i].calendarDay38}," +
                    "${list[i].calendarDay39}," +
                    "${list[i].calendarDay40}," +
                    "${list[i].calendarDay41}," +
                    "${list[i].calendarDay42}")
        }

        assertEquals(maxPastMonth,list.filter { it.event == 0 }.size)
    }

    @AfterClass
    fun afterClass() {

    }

    @After
    fun after() {

    }
}
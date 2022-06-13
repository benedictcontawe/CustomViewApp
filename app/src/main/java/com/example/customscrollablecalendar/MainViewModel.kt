package com.example.customscrollablecalendar

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var itemlist = mutableListOf<CalendarViewModel>()
    private lateinit var itemDecorationHelper : CustomBottomOffsetDecoration

    fun setCalendarList(setMaxPastMonth : Int, setDayOfMonth : Int) {
        itemlist = CalendarDateFormatter.setScrollableCalendar(setMaxPastMonth, setDayOfMonth) ?: arrayListOf<CalendarViewModel>()
    }

    public fun getCalendarList() : List<CalendarViewModel> {
        return itemlist ?: arrayListOf<CalendarViewModel>()
    }

    fun setBottomOffsetDecoration(bottomItemOffset : Int) {
        itemDecorationHelper = CustomBottomOffsetDecoration(bottomItemOffset)
    }

    fun getBottomOffsetDecoration() : CustomBottomOffsetDecoration {
        return itemDecorationHelper;
    }
}
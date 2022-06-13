package com.example.customscrollablecalendar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter : RecyclerView.Adapter<CalendarViewHolder> {

    /**Main */
    private lateinit var calendarListener : CalendarListener
    private lateinit var list : MutableList<CalendarViewModel>

    constructor(calendarListener : CalendarListener) : super() {
        this.calendarListener = calendarListener
    }

    init {
        //list = listOf<CalendarViewModel>()
        list = mutableListOf<CalendarViewModel>()
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : CalendarViewHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.getContext())
        val view : View = layoutInflater.inflate(R.layout.row_recycler_view_calendar, parent, false)
        return CalendarViewHolder(parent.getContext(), view, calendarListener)
    }

    override fun onBindViewHolder(holder : CalendarViewHolder, position : Int) {
        holder.bindDataToViewHolder(list[position], position)
    }

    override fun getItemCount() : Int {
        return list.size
    }

    fun setItems(items : List<CalendarViewModel>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

}
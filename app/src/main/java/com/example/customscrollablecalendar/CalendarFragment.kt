package com.example.customscrollablecalendar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_calendar.view.*
import java.util.*

class CalendarFragment : Fragment(), CalendarListener, View.OnClickListener {

    private lateinit var myView : View

    private lateinit var adapter : CalendarAdapter
    private lateinit var itemDecorationHelper : CustomBottomOffsetDecoration
    private var itemlist = mutableListOf<CalendarViewModel>()

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View {
        myView = inflater.inflate(R.layout.layout_calendar, container, false)

        onSetEvents()

        return myView
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onSetEvents() {
        myView.ll_close.setOnClickListener(this)

        val linearLayoutManager : LinearLayoutManager = LinearLayoutManager(getContext())
        adapter = CalendarAdapter(getContext(), this)
        itemDecorationHelper = CustomBottomOffsetDecoration(100)
        itemlist = CalendarDateFormatter.setScrollableCalendar(12, Calendar.SATURDAY)

        myView.recyclerView_calendar.removeItemDecoration(itemDecorationHelper)
        myView.recyclerView_calendar.setLayoutManager(linearLayoutManager)
        myView.recyclerView_calendar.setAdapter(adapter)
        myView.recyclerView_calendar.addItemDecoration(itemDecorationHelper)
        myView.recyclerView_calendar.scrollToPosition(adapter.itemCount)
        myView.recyclerView_calendar.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    Toast.makeText(context,"bottom",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        adapter.setItems(itemlist)
    }

    override fun onClick(v : View) {
        when(v.getId()) {
            myView.ll_close.getId() -> {
                getFragmentManager()!!.beginTransaction().remove(this).commit()
            }
        }
    }

    override fun onClick(item : CalendarViewModel, position : Int, daySelected : Int) {
        Log.d(CalendarFragment::class.java.simpleName,"onClick ${item.calendarMonth} ${daySelected} ${item.calendarYear} ${item.event} ${position}")
        myView.tv_event.text = "${CalendarViewModel.getEvent(item.event?:CalendarViewModel.Null)} Month"
        myView.tv_calendar_date.text = "${CalendarDateFormatter.setMonth(item.calendarMonth,true,true)} ${daySelected} ${item.calendarYear}"
    }
}
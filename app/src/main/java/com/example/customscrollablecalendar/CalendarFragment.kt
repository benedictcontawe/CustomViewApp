package com.example.customscrollablecalendar

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_calendar.view.*
import java.util.*

class CalendarFragment : Fragment(), CalendarListener, View.OnClickListener {

    private lateinit var myView : View
    private lateinit var viewModel : MainViewModel
    private lateinit var adapter : CalendarAdapter

    override fun onAttach(context : Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View {
        myView = inflater.inflate(R.layout.layout_calendar, container, false)
        onSetEvents()
        return myView
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onSetEvents() {
        myView.ll_close.setOnClickListener(this@CalendarFragment)

        val linearLayoutManager : LinearLayoutManager = LinearLayoutManager(getContext())
        adapter = CalendarAdapter(this@CalendarFragment)
        viewModel.setBottomOffsetDecoration(100)
        viewModel.setCalendarList(12, Calendar.SATURDAY)

        myView.recyclerView_calendar.removeItemDecoration(viewModel.getBottomOffsetDecoration())
        myView.recyclerView_calendar.setLayoutManager(linearLayoutManager)
        myView.recyclerView_calendar.setAdapter(adapter)
        myView.recyclerView_calendar.addItemDecoration(viewModel.getBottomOffsetDecoration())
        myView.recyclerView_calendar.scrollToPosition(adapter.getItemCount())
        myView.recyclerView_calendar.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    Toast.makeText(context, "bottom", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        adapter.setItems(viewModel.getCalendarList())
    }

    override fun onClick(view : View) {
        when(view.getId()) {
            myView.ll_close.getId() -> {
                getParentFragmentManager().beginTransaction().remove(this@CalendarFragment).commit();
            }
        }
    }

    override fun onClick(item : CalendarViewModel, position : Int, daySelected : Int) {
        Log.d(CalendarFragment::class.java.getSimpleName(),"onClick ${item.calendarMonth} ${daySelected} ${item.calendarYear} ${item.event} ${position}")
        myView.tv_event.text = "${CalendarViewModel.getEvent(item.event?:CalendarViewModel.Null)} Month"
        myView.tv_calendar_date.text = "${CalendarDateFormatter.setMonth(item.calendarMonth,true,true)} ${daySelected} ${item.calendarYear}"
    }
}
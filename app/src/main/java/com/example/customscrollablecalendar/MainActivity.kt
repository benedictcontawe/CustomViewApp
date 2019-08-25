package com.example.customscrollablecalendar

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var calendarFragment : CalendarFragment

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onSetEvents()
    }

    private fun onSetEvents() {
        btn_show.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.getId()) {
            btn_show.getId() -> {
                showCalendar()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showCalendar()
    }

    override fun onPause() {
        super.onPause()
        getSupportFragmentManager().beginTransaction().remove(calendarFragment).commit()
    }

    private fun showCalendar() {
        calendarFragment = CalendarFragment()
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, calendarFragment).commit()
    }
}
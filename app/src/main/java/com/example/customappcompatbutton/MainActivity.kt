package com.example.customappcompatbutton

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var calendarFragment : CalendarFragment

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        getSupportFragmentManager().beginTransaction().add(R.id.main_layout, calendarFragment).commit()
    }
}

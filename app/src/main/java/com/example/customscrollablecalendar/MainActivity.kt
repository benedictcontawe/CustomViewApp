package com.example.customscrollablecalendar

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel : MainViewModel
    private lateinit var calendarFragment : CalendarFragment

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this@MainActivity).get(MainViewModel::class.java)
    }

    override fun onClick(view : View) {
        when(view.getId()) {
            btn_show.getId() -> {
                showCalendar()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showCalendar()
        btn_show.setOnClickListener(this@MainActivity)
    }

    override fun onPause() {
        super.onPause()
        getSupportFragmentManager().beginTransaction().remove(calendarFragment).commit()
        btn_show.setOnClickListener(null)
    }

    private fun showCalendar() {
        calendarFragment = CalendarFragment()
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, calendarFragment).commit()
    }
}
package com.example.customappcompatbutton

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customappcompatbutton.CalendarDateFormatter.Companion.setMonth
import kotlinx.android.synthetic.main.row_recycler_view_calendar.view.*

class CalendarViewHolder : RecyclerView.ViewHolder {

    /**Main */
    private lateinit var context: Context
    private lateinit var calendarListener: CalendarListener
    /**Data */
    private var itemTextTypeMonth: TextView
    private var itemTextTypeYear: TextView
    /**With Events and Others */
    private var itemTextType1stDay: TextView
    private var itemTextType2ndDay: TextView
    private var itemTextType3rdDay: TextView
    private var itemTextType4thDay: TextView
    private var itemTextType5thDay: TextView
    private var itemTextType6thDay: TextView
    private var itemTextType7thDay: TextView
    private var itemTextType8thDay: TextView
    private var itemTextType9thDay: TextView
    private var itemTextType10thDay: TextView
    private var itemTextType11thDay: TextView
    private var itemTextType12thDay: TextView
    private var itemTextType13thDay: TextView
    private var itemTextType14thDay: TextView
    private var itemTextType15thDay: TextView
    private var itemTextType16thDay: TextView
    private var itemTextType17thDay: TextView
    private var itemTextType18thDay: TextView
    private var itemTextType19thDay: TextView
    private var itemTextType20thDay: TextView
    private var itemTextType21stDay: TextView
    private var itemTextType22ndDay: TextView
    private var itemTextType23rdDay: TextView
    private var itemTextType24thDay: TextView
    private var itemTextType25thDay: TextView
    private var itemTextType26thDay: TextView
    private var itemTextType27thDay: TextView
    private var itemTextType28thDay: TextView
    private var itemTextType29thDay: TextView
    private var itemTextType30thDay: TextView
    private var itemTextType31stDay: TextView
    private var itemTextType32ndDay: TextView
    private var itemTextType33ndDay: TextView
    private var itemTextType34ndDay: TextView
    private var itemTextType35ndDay: TextView
    private var itemTextType36ndDay: TextView
    private var itemTextType37ndDay: TextView
    private var itemTextType38ndDay: TextView
    private var itemTextType39ndDay: TextView
    private var itemTextType40ndDay: TextView
    private var itemTextType41ndDay: TextView
    private var itemTextType42ndDay: TextView

    private var itemDayList : MutableList<TextView>

    constructor(context : Context, itemView : View, calendarListener: CalendarListener) : super(itemView) {
        this.context = context
        this.calendarListener = calendarListener
    }

    init {
        itemDayList = mutableListOf<TextView>()
        //region Connecting variables of month and year to the text view
        itemTextTypeMonth = itemView.tv_month
        itemTextTypeYear = itemView.tv_year
        //endregion
        //region  Connecting variables of day to the text view
        itemTextType1stDay = itemView.tv_1
        itemTextType2ndDay = itemView.tv_2
        itemTextType3rdDay = itemView.tv_3
        itemTextType4thDay = itemView.tv_4
        itemTextType5thDay = itemView.tv_5
        itemTextType6thDay = itemView.tv_6
        itemTextType7thDay = itemView.tv_7
        itemTextType8thDay = itemView.tv_8
        itemTextType9thDay = itemView.tv_9
        itemTextType10thDay = itemView.tv_10
        itemTextType11thDay = itemView.tv_11
        itemTextType12thDay = itemView.tv_12
        itemTextType13thDay = itemView.tv_13
        itemTextType14thDay = itemView.tv_14
        itemTextType15thDay = itemView.tv_15
        itemTextType16thDay = itemView.tv_16
        itemTextType17thDay = itemView.tv_17
        itemTextType18thDay = itemView.tv_18
        itemTextType19thDay = itemView.tv_19
        itemTextType20thDay = itemView.tv_20
        itemTextType21stDay = itemView.tv_21
        itemTextType22ndDay = itemView.tv_22
        itemTextType23rdDay = itemView.tv_23
        itemTextType24thDay = itemView.tv_24
        itemTextType25thDay = itemView.tv_25
        itemTextType26thDay = itemView.tv_26
        itemTextType27thDay = itemView.tv_27
        itemTextType28thDay = itemView.tv_28
        itemTextType29thDay = itemView.tv_29
        itemTextType30thDay = itemView.tv_30
        itemTextType31stDay = itemView.tv_31
        itemTextType32ndDay = itemView.tv_32
        itemTextType33ndDay = itemView.tv_33
        itemTextType34ndDay = itemView.tv_34
        itemTextType35ndDay = itemView.tv_35
        itemTextType36ndDay = itemView.tv_36
        itemTextType37ndDay = itemView.tv_37
        itemTextType38ndDay = itemView.tv_38
        itemTextType39ndDay = itemView.tv_39
        itemTextType40ndDay = itemView.tv_40
        itemTextType41ndDay = itemView.tv_41
        itemTextType42ndDay = itemView.tv_42
        //endregion
        //region List of TextView
        itemDayList.clear()
        itemDayList.add(itemTextType1stDay)
        itemDayList.add(itemTextType2ndDay)
        itemDayList.add(itemTextType3rdDay)
        itemDayList.add(itemTextType4thDay)
        itemDayList.add(itemTextType5thDay)
        itemDayList.add(itemTextType6thDay)
        itemDayList.add(itemTextType7thDay)
        itemDayList.add(itemTextType8thDay)
        itemDayList.add(itemTextType9thDay)
        itemDayList.add(itemTextType10thDay)
        itemDayList.add(itemTextType11thDay)
        itemDayList.add(itemTextType12thDay)
        itemDayList.add(itemTextType13thDay)
        itemDayList.add(itemTextType14thDay)
        itemDayList.add(itemTextType15thDay)
        itemDayList.add(itemTextType16thDay)
        itemDayList.add(itemTextType17thDay)
        itemDayList.add(itemTextType18thDay)
        itemDayList.add(itemTextType19thDay)
        itemDayList.add(itemTextType20thDay)
        itemDayList.add(itemTextType21stDay)
        itemDayList.add(itemTextType22ndDay)
        itemDayList.add(itemTextType23rdDay)
        itemDayList.add(itemTextType24thDay)
        itemDayList.add(itemTextType25thDay)
        itemDayList.add(itemTextType26thDay)
        itemDayList.add(itemTextType27thDay)
        itemDayList.add(itemTextType28thDay)
        itemDayList.add(itemTextType29thDay)
        itemDayList.add(itemTextType30thDay)
        itemDayList.add(itemTextType31stDay)
        itemDayList.add(itemTextType32ndDay)
        itemDayList.add(itemTextType33ndDay)
        itemDayList.add(itemTextType34ndDay)
        itemDayList.add(itemTextType35ndDay)
        itemDayList.add(itemTextType36ndDay)
        itemDayList.add(itemTextType37ndDay)
        itemDayList.add(itemTextType38ndDay)
        itemDayList.add(itemTextType39ndDay)
        itemDayList.add(itemTextType40ndDay)
        itemDayList.add(itemTextType41ndDay)
        itemDayList.add(itemTextType42ndDay)
        //endregion
    }

    fun bindDataToViewHolder(item: CalendarViewModel, position: Int) {
        //region Input Data
        itemTextTypeMonth.text = setMonth(item.calendarMonth!!-1,true,true)
        itemTextTypeYear.text =  item.calendarYear.toString()
        itemTextType1stDay.text  = item.calendarDay1.toString()
        itemTextType2ndDay.text  = item.calendarDay2.toString()
        itemTextType3rdDay.text  = item.calendarDay3.toString()
        itemTextType4thDay.text  = item.calendarDay4.toString()
        itemTextType5thDay.text  = item.calendarDay5.toString()
        itemTextType6thDay.text  = item.calendarDay6.toString()
        itemTextType7thDay.text  = item.calendarDay7.toString()
        itemTextType8thDay.text  = item.calendarDay8.toString()
        itemTextType9thDay.text  = item.calendarDay9.toString()
        itemTextType10thDay.text  = item.calendarDay10.toString()
        itemTextType11thDay.text  = item.calendarDay11.toString()
        itemTextType12thDay.text  = item.calendarDay12.toString()
        itemTextType13thDay.text  = item.calendarDay13.toString()
        itemTextType14thDay.text  = item.calendarDay14.toString()
        itemTextType15thDay.text  = item.calendarDay15.toString()
        itemTextType16thDay.text  = item.calendarDay16.toString()
        itemTextType17thDay.text  = item.calendarDay17.toString()
        itemTextType18thDay.text  = item.calendarDay18.toString()
        itemTextType19thDay.text  = item.calendarDay19.toString()
        itemTextType20thDay.text  = item.calendarDay20.toString()
        itemTextType21stDay.text  = item.calendarDay21.toString()
        itemTextType22ndDay.text  = item.calendarDay22.toString()
        itemTextType23rdDay.text  = item.calendarDay23.toString()
        itemTextType24thDay.text  = item.calendarDay24.toString()
        itemTextType25thDay.text  = item.calendarDay25.toString()
        itemTextType26thDay.text  = item.calendarDay26.toString()
        itemTextType27thDay.text  = item.calendarDay27.toString()
        itemTextType28thDay.text  = item.calendarDay28.toString()
        itemTextType29thDay.text  = item.calendarDay29.toString()
        itemTextType30thDay.text  = item.calendarDay30.toString()
        itemTextType31stDay.text  = item.calendarDay31.toString()
        itemTextType32ndDay.text  = item.calendarDay32.toString()
        itemTextType33ndDay.text  = item.calendarDay33.toString()
        itemTextType34ndDay.text  = item.calendarDay34.toString()
        itemTextType35ndDay.text  = item.calendarDay35.toString()
        itemTextType36ndDay.text  = item.calendarDay36.toString()
        itemTextType37ndDay.text  = item.calendarDay37.toString()
        itemTextType38ndDay.text  = item.calendarDay38.toString()
        itemTextType39ndDay.text  = item.calendarDay39.toString()
        itemTextType40ndDay.text  = item.calendarDay40.toString()
        itemTextType41ndDay.text  = item.calendarDay41.toString()
        itemTextType42ndDay.text  = item.calendarDay42.toString()
        //endregion
        //region Set Listener
        setEvents(item, position)
        //endregion
    }

    private fun setEvents(item : CalendarViewModel, position : Int){
        itemDayList.map {
            /* On Click */
            it.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    calendarListener.onClick(item,position)
                }
            })
        }
    }
}

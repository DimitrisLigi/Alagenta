package com.dimitrisligi.alagenta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimitrisligi.alagenta.databinding.ActivityMainCalendarBinding
import models.Event

class MainCalendarActivity : AppCompatActivity() {
    //Binding
    private lateinit var mBinding: ActivityMainCalendarBinding
    //The list that recyclerView uses.
    private lateinit var mEventList: MutableList<Event>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mEventList = mutableListOf()
        mBinding = ActivityMainCalendarBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.calendarView
        setupCalendar()
        mEventList.add(Event("LKfalaf","AKSDFKASDKF","01:00"))
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.adapter = CalendarAppointmentListAdapter(mEventList)
        addNewEvent()
    }


    //Add Event BUTTON
    private fun addNewEvent() {
        mBinding.btnAddEvent.setOnClickListener {

        }
    }

    private fun setupCalendar() {
        mBinding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val mYear = year
            val mMonth = month
            val MDom = dayOfMonth
            //Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
            val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
            Toast.makeText(this@MainCalendarActivity, msg, Toast.LENGTH_SHORT).show()

        }
    }

}
package com.dimitrisligi.alagenta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dimitrisligi.alagenta.databinding.ActivityMainCalendarBinding

class MainCalendarActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainCalendarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainCalendarBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.calendarView
        setupCalendar()

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
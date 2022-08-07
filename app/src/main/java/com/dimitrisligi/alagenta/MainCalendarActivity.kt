package com.dimitrisligi.alagenta

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimitrisligi.alagenta.databinding.ActivityMainCalendarBinding
import db.ClientDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import models.Client
import models.Event
import java.sql.Date
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class MainCalendarActivity : AppCompatActivity() {
    //Binding
    private lateinit var mBinding: ActivityMainCalendarBinding

    //The list that recyclerView uses.
    private lateinit var mEventList: MutableList<Event>


    private lateinit var mClientList: List<Client>


    private lateinit var clientDB: ClientDatabase

    private var choosenDate: String? = null

    //TODO: DELETE THIS
    private var temp = 0



    @OptIn(InternalCoroutinesApi::class, DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Initializing the recyclerview with dummy data
//        mEventList = dumbData()
        updateToCurrentDate()
        clientDB = ClientDatabase.getClientDatabase(this)
        GlobalScope.launch {
            mClientList = clientDB.clientDao().getAllClients()
        }
        mBinding = ActivityMainCalendarBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.calendarView
        //Toasting the pressing dates
        setupCalendar()
        getTheClients()
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.adapter = ClientListAdapter(mClientList,this)
        addNewEvent()
    }

    private fun updateToCurrentDate() {
        var simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        choosenDate = simpleDateFormat.format(Date())
    }


    @InternalCoroutinesApi
    private fun getTheClients() {

    }

    //Add Event
    private fun addNewEvent() {
        mBinding.btnAddEvent.setOnClickListener {
            val toRegisterNewClientIntent = Intent(this,ClientRegisterActivity::class.java).also {
                if (choosenDate == null){
                    updateToCurrentDate()
                }else{
                    it.putExtra("choosenDate",choosenDate)
                }
                startActivity(it)
            }
        }
    }


    private fun setupCalendar() {
        mBinding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val mYear = year
            val mMonth = month
            val MDom = dayOfMonth
            choosenDate = mYear.toString() + mMonth.toString() + MDom.toString()
            //Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
            val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
            Toast.makeText(this@MainCalendarActivity, choosenDate, Toast.LENGTH_SHORT).show()

        }
    }

}
package com.dimitrisligi.alagenta

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimitrisligi.alagenta.databinding.ActivityMainCalendarBinding
import db.ClientDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import models.Client
import models.Event

class MainCalendarActivity : AppCompatActivity() {
    //Binding
    private lateinit var mBinding: ActivityMainCalendarBinding
    //The list that recyclerView uses.
    private lateinit var mEventList: MutableList<Event>

    private lateinit var mClientList: List<Client>

    private lateinit var clientDB: ClientDatabase
    //TODO: DELETE THIS
    private var temp = 0


    @OptIn(InternalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Initializing the recyclerview with dummy data
//        mEventList = dumbData()
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
        mBinding.recyclerView.adapter = ClientListAdapter(mClientList)
        addNewEvent()
    }

    @OptIn(InternalCoroutinesApi::class)
    private fun getTheClients() {


    }


    //Add Event
    private fun addNewEvent() {
        mBinding.btnAddEvent.setOnClickListener {
            val toRegisterNewClientIntent = Intent(this,ClientRegisterActivity::class.java)
            startActivity(toRegisterNewClientIntent)
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


    private fun dumbData():MutableList<Event>{
        val list = mutableListOf<Event>()
        list.add(Event("Kalavritwn 12","Episkeui","12:00"))
        list.add(Event("Axeloou 132","Sita","12:00"))
        list.add(Event("Pelopida 112","Episkeui","12:00"))
        list.add(Event("Axeloou 312","Prosfora","12:00"))
        list.add(Event("Kalavritwn 112","Sita","12:00"))
        list.add(Event("Pelopida 124","Metra","12:00"))
        list.add(Event("Mixalidi 11","Prosfora","12:00"))
        list.add(Event("Kalavritwn 1","Episkeui","12:00"))
        list.add(Event("Pelopida 2","Metra","12:00"))
        return list
    }
}
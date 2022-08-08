package com.dimitrisligi.alagenta

import adapters.ClientListAdapter
import adapters.EventAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimitrisligi.alagenta.databinding.ActivityMainCalendarBinding
import db.ClientDatabase
import db.EventDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import models.Client
import models.Event
import java.text.SimpleDateFormat
import java.util.*

class MainCalendarActivity : AppCompatActivity() {
    //Binding
    private lateinit var mBinding: ActivityMainCalendarBinding

    //The list that recyclerView uses.
    private lateinit var mEventList: List<Event>

    //Client list
//    private lateinit var mClientList: List<Client>

    //Client database
    private lateinit var clientDB: ClientDatabase

    //Event database
    private lateinit var eventDB: EventDatabase

    //Chosen date on calendar
    private var chosenDate: String? = null

    //TODO: DELETE THIS TEMP
    private var temp = 0



    @OptIn(InternalCoroutinesApi::class, DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Initializing the recyclerview with dummy data
//        mEventList = dumbData()
        eventDB = EventDatabase.getEventDatabase(this)
        updateToCurrentDate()
//        clientDB = ClientDatabase.getClientDatabase(this)
        GlobalScope.launch {
//            mClientList = clientDB.clientDao().getAllClients()
            mEventList = eventDB.eventDao().getAllEvents()
        }
        mBinding = ActivityMainCalendarBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.calendarView
        //Toasting the pressing dates
        onChangeCalendarDate()
//        getTheClients()
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
//        mBinding.recyclerView.adapter = ClientListAdapter(mClientList,this)
        mBinding.recyclerView.adapter = EventAdapter(mEventList)
        addNewEvent()
    }

    private fun updateToCurrentDate() {
        val simpleDateFormat = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
        chosenDate = simpleDateFormat.format(Date())
    }


//    @InternalCoroutinesApi
//    private fun getTheClients() {
//
//    }

    //Add Event
    private fun addNewEvent() {
        mBinding.btnAddEvent.setOnClickListener {
            Intent(this,ClientRegisterActivity::class.java).also {
                if (chosenDate == null){
                    updateToCurrentDate()
                }else{
                    it.putExtra("chosenDate",chosenDate)
                }
                startActivity(it)
            }
        }
    }


    private fun onChangeCalendarDate() {
        mBinding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            chosenDate = year.toString() + (month + 1).toString() + dayOfMonth.toString()
            //Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
            Toast.makeText(this@MainCalendarActivity, chosenDate, Toast.LENGTH_SHORT).show()
        }
    }

}
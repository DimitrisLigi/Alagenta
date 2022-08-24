package com.dimitrisligi.alagenta

import adapters.ClientListAdapter
import adapters.EventAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimitrisligi.alagenta.databinding.ActivityMainCalendarBinding
import db.ClientDatabase
import db.EventDatabase
import kotlinx.coroutines.*
import models.Client
import models.Event
import java.text.SimpleDateFormat
import java.util.*

class MainCalendarActivity : AppCompatActivity() {

    //Binding
    private lateinit var mBinding: ActivityMainCalendarBinding

    //The list that recyclerView uses.
    private lateinit var mEventList: List<Event>

    //Client database
    private lateinit var clientDB: ClientDatabase

    //Event database
    private lateinit var eventDB: EventDatabase

    //RecyclerView Adapter
    private lateinit var eventAdapter: EventAdapter

    //Chosen date on calendar
    private var chosenDate: String? = null

    //Progress bar
    private var progressBar: ProgressBar? = null



    //------------ON CREATE ACTIVITY--------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getTheDB()
        miasunartisi()
        mBinding = ActivityMainCalendarBinding.inflate(layoutInflater)
        progressBar = mBinding.pbLoading
        progressBar?.visibility = View.INVISIBLE
        setContentView(mBinding.root)
        mBinding.calendarView
        //Toasting the pressing dates
        onChangeCalendarDate()
//        getTheClients()
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
//        mBinding.recyclerView.adapter = ClientListAdapter(mClientList,this)
        eventAdapter = EventAdapter(mEventList,this)
        mBinding.recyclerView.adapter = eventAdapter
        addNewEvent()
    }

    @OptIn(InternalCoroutinesApi::class)
    private fun getTheDB(){
        var xxx: Job? = null
        xxx = GlobalScope.launch {
            eventDB = EventDatabase.getEventDatabase(this@MainCalendarActivity)
        }
        while (xxx.isActive){
            progressBar?.visibility = View.VISIBLE
        }
        progressBar?.visibility = View.INVISIBLE
    }
    override fun onResume() {
        super.onResume()
        getTheDB()
    }

    private fun miasunartisi(){
      val xxxs =  GlobalScope.launch {
           mEventList = eventDB.eventDao().getAllEvents()
       }
        while (xxxs.isActive){
            GlobalScope.launch {
                mEventList = eventDB.eventDao().findByDate(updateToCurrentDate())
            }
        }


    }

    private fun updateToCurrentDate(): String {
        val simpleDateFormat = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
        return simpleDateFormat.format(Date())
    }

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
            changeRecyclerViewAdapter(chosenDate!!)
        }
    }

    @OptIn(InternalCoroutinesApi::class)
    private fun changeRecyclerViewAdapter(chosenDate: String){
        var eventJob1: Job? = null
        var eventJob2: Job? = null
        eventJob1 = GlobalScope.launch {
            eventDB = EventDatabase.getEventDatabase(this@MainCalendarActivity)
        }
       while (eventJob1.isActive){
           eventJob2 =   GlobalScope.launch{
               mEventList = eventDB.eventDao().findByDate(chosenDate)
           }
           while (eventJob2.isActive){
               progressBar?.visibility = View.VISIBLE
           }
       }


        progressBar?.visibility = View.INVISIBLE
        mBinding.recyclerView.adapter = EventAdapter(mEventList,this)
        mBinding.recyclerView.requestLayout()


    }
}
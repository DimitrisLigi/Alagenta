package com.dimitrisligi.alagenta

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dimitrisligi.alagenta.databinding.ActivityClientRegisterBinding
import db.ClientDatabase
import db.EventDatabase
import kotlinx.coroutines.*
import models.AppointmentType
import models.Client
import models.Event
import java.text.SimpleDateFormat
import java.util.*

class ClientRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClientRegisterBinding
    private lateinit var clientDB: ClientDatabase
    private lateinit var eventDB: EventDatabase
    private var chosenDate: String? =  null
    private var chosenTime: String? = null
    private val cal: Calendar = Calendar.getInstance()

    @OptIn(InternalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chosenDate = intent.getStringExtra("chosenDate")

        clientDB = ClientDatabase.getClientDatabase(this)
        eventDB = EventDatabase.getEventDatabase(this)

        binding.btnRegisterNewClient.setOnClickListener {
            createClient()
        }
        binding.btnReadData.setOnClickListener {
            readData()
        }

        binding.btnEventTimePicker.setOnClickListener {

            val timeSetListener = TimePickerDialog.OnTimeSetListener { timepicker, hourOfDay, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                cal.set(Calendar.MINUTE,minute)
               chosenTime = hourOfDay.toString() + minute.toString()
                binding.btnEventTimePicker.text = hourOfDay.toString()+ ":" + minute.toString()
            }
//            Toast.makeText(this,chosenTime,Toast.LENGTH_LONG).show()
            TimePickerDialog(this,timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),false).show()
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun createClient(){

        val address = binding.etClientAddress.text.trim().toString()
        val area = binding.etClientArea.text.trim().toString()
        val name = binding.etClientName.text.trim().toString()
        val ring = binding.etClientRing.text.trim().toString()
        val phone = binding.etClientPhoneNumber.text.trim().toString()

        if (address.isNotEmpty() && area.isNotEmpty() && name.isNotEmpty() && ring.isNotEmpty()
            && phone.isNotEmpty() && chosenTime.isNullOrEmpty()){
            val client = Client(null,address,area,name,ring,phone)
            val event = Event(null,address,AppointmentType.MEASUREMENT,chosenTime,chosenDate)
            GlobalScope.launch(Dispatchers.IO){
                clientDB.clientDao().insertClient(client)
                eventDB.eventDao().insertEvent(event)
            }
            binding.etClientAddress.text.clear()
            binding.etClientArea.text.clear()
            binding.etClientRing.text.clear()
            binding.etClientName.text.clear()
            binding.etClientPhoneNumber.text.clear()

            Toast.makeText(this@ClientRegisterActivity,
                "Ο πελάτης δημιουργήθηκε με επιτυχία!",Toast.LENGTH_SHORT).show()
            val toCalendarActivityIntent = Intent(this,MainCalendarActivity::class.java)
            startActivity(toCalendarActivityIntent)

        }else{
            Toast.makeText(this@ClientRegisterActivity,
                "Παρακαλώ συμπληρώστε όλα τα στοιχεία!",Toast.LENGTH_SHORT).show()
        }
    }

    private fun readData(){

        val queryPhoneNumber = binding.etQueryNumber.text.toString()
        if (queryPhoneNumber.isNotEmpty()){
            lateinit var client: Client

            GlobalScope.launch {
                client = clientDB.clientDao().findByPhone(queryPhoneNumber)
                displayClient(client)
            }
        }

    }

    private suspend fun displayClient(client: Client) {
        withContext(Dispatchers.Main){
            binding.etClientAddress.setText(client.address)
            binding.etClientName.setText(client.name)
            binding.etClientRing.setText(client.ringName)
            binding.etClientPhoneNumber.setText(client.phone)
            binding.etClientArea.setText(client.area)
        }
    }


}
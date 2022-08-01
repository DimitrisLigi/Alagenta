package com.dimitrisligi.alagenta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dimitrisligi.alagenta.databinding.ActivityClientRegisterBinding
import db.ClientDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import models.Client

class ClientRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClientRegisterBinding
    private lateinit var clientDB: ClientDatabase
    @OptIn(InternalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clientDB = ClientDatabase.getClientDatabase(this)

        binding.btnRegisterNewClient.setOnClickListener {
            createClient()
        }

    }

    private fun createClient(){

        val address = binding.etClientAddress.text.trim().toString()
        val area = binding.etClientArea.text.trim().toString()
        val name = binding.etClientName.text.trim().toString()
        val ring = binding.etClientRing.text.trim().toString()
        val phone = binding.etClientPhoneNumber.text.trim().toString()

        if (address.isNotEmpty() && area.isNotEmpty() && name.isNotEmpty() && ring.isNotEmpty()
            && phone.isNotEmpty()){
            val client = Client(null,address,area,name,ring,phone)
            GlobalScope.launch(Dispatchers.IO){
                clientDB.clientDao().insertClient(client)
            }
            Toast.makeText(this@ClientRegisterActivity,"Ο πελάτης δημιουργήθηκε με επιτυχία!",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this@ClientRegisterActivity,"Παρακαλώ συμπληρώστε όλα τα στοιχεία!",Toast.LENGTH_SHORT).show()

        }
    }
}
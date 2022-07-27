package com.dimitrisligi.alagenta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dimitrisligi.alagenta.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        // the onCreate function
        //AMXXMXMXMXMXMXMXMX
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()


            if(email.isEmpty()){
                Toast.makeText(this,"Παρακαλώ συμπληρώστε το email σας",Toast.LENGTH_LONG).show()
            }
            else if (pass.isEmpty()){
                Toast.makeText(this,"Παρακαλώ συμπληρώστε τον κωδικό σας",Toast.LENGTH_LONG).show()
            }
            firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                when{
                    it.isSuccessful -> {Toast.makeText(this,"Login Successful!",Toast.LENGTH_SHORT).show()}
                    it.isComplete ->{
                        //TODO FETCH USERS DATA
                        val mainCalendarIntent = Intent(this,MainCalendarActivity::class.java)
                        startActivity(mainCalendarIntent)
                    }
                }
            }.addOnFailureListener {
                Toast.makeText(this,it.toString(),Toast.LENGTH_LONG).show()
            }
        }

        binding.tvCreateAccount.setOnClickListener {
            val myIntent = Intent(this,RegisterActivity::class.java)
            startActivity(myIntent)
        }
    }
}
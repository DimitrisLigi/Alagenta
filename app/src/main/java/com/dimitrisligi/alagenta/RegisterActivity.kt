package com.dimitrisligi.alagenta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dimitrisligi.alagenta.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnCreateAccount.setOnClickListener {
            val email = binding.etCreateEmail.text.toString().trim()
            val pass = binding.etCreatePassword.text.toString().trim()
            val confirmationPassword = binding.etCreatePasswordConfirmation.text.toString().trim()

            if (checkTheFields(email,pass,confirmationPassword)){
                firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                    Toast.makeText(this,
                        "Συγχαρητήρια, ο λογαριασμός σου δημιουργήθηκε με επιτυχία",
                        Toast.LENGTH_LONG).show()
                    val myIntent = Intent(this,MainCalendarActivity::class.java)
                    startActivity(myIntent)
                }.addOnFailureListener {
                    Toast.makeText(this,
                        "Υπήρξε πρόβλημα στην δημιουργία του λογαριασμού σου!",
                        Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(this,"Παρακαλώ συμπληρώστε όλα τα πεδία",Toast.LENGTH_LONG).show()
            }
        }
        binding.tvAlreadyAUser.setOnClickListener {
            val loginIntent = Intent(this,SignUpActivity::class.java)
            startActivity(loginIntent)
        }
    }

    private fun checkTheFields(email: String, pass: String, confirmationPassword: String): Boolean {
        if (email.isEmpty())
            return false
        else if(pass.isEmpty())
            return false
        else if(confirmationPassword.isEmpty())
            return false
        return pass == confirmationPassword
    }
}
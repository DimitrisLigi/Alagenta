package com.dimitrisligi.alagenta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dimitrisligi.alagenta.databinding.ActivityMainBinding
import com.dimitrisligi.alagenta.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
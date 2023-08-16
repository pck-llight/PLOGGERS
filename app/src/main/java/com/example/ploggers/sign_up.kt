package com.example.ploggers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ploggers.databinding.ActivityLoginBinding
import com.example.ploggers.databinding.ActivitySignUpBinding
import com.example.ploggers.databinding.ActivityStartBinding

class sign_up : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
package com.example.ploggers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ploggers.databinding.ActivityInputKgBinding

class input_kg : AppCompatActivity() {
    private lateinit var binding : ActivityInputKgBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputKgBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
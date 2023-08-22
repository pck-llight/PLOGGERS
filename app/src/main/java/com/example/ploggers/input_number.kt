package com.example.ploggers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ploggers.databinding.ActivityInputNumberBinding

class input_number : AppCompatActivity() {
    private lateinit var binding : ActivityInputNumberBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
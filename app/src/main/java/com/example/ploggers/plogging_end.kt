package com.example.ploggers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ploggers.databinding.ActivityInputKgBinding
import com.example.ploggers.databinding.ActivityPloggingEndBinding

class plogging_end : AppCompatActivity() {
    private lateinit var binding: ActivityPloggingEndBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPloggingEndBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kgintent = Intent(this, input_kg::class.java)
        val numintnet = Intent(this, input_number::class.java)


        binding.inputKg.setOnClickListener() {
            startActivity(kgintent)
        }
        binding.inputNumber.setOnClickListener() {
            startActivity(numintnet)
        }

    }
}
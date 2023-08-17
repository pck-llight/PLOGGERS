package com.example.ploggers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ploggers.databinding.ActivityPloggingTimerBinding
import com.example.ploggers.databinding.ActivityStartBinding

class start : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startbtn.setOnClickListener() {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }
    }
}
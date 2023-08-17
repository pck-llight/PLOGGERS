package com.example.ploggers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ploggers.databinding.ActivityMainBinding
import com.example.ploggers.databinding.ActivityPloggingTimerBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(this, ploggingtimer::class.java)


        binding.timerStartButton.setOnClickListener() {
            startActivity(intent)
            Log.d("Df", "Sdf")
        }



    }
}
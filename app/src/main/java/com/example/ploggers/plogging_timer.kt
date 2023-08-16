package com.example.ploggers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ploggers.databinding.ActivityCreatePostBinding
import com.example.ploggers.databinding.ActivityMainBinding
import com.example.ploggers.databinding.ActivityPloggingTimerBinding

class plogging_timer : AppCompatActivity() {

    private lateinit var binding: ActivityPloggingTimerBinding
    private var isRunning = false
    private var runTime = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plogging_timer)

        binding = ActivityPloggingTimerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.timerButton.setOnClickListener {
            if(isRunning){
            }
            else{

            }
        }

    }

    private fun startTimer(){

    }


}
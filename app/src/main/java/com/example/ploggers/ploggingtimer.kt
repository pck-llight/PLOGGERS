package com.example.ploggers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.ploggers.databinding.ActivityPloggingTimerBinding
import kotlinx.coroutines.delay
import java.util.*
import kotlin.concurrent.timer

class ploggingtimer : AppCompatActivity() {
    private lateinit var binding: ActivityPloggingTimerBinding
    private var time = 0
    private var timerTask : Timer? = null
    private var isRunning : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPloggingTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startbtn.setOnClickListener {
            if (isRunning == 1) {
                binding.startbtn.text = "중단"
                binding.startbtn.setBackgroundResource(R.drawable.endbtn)
                startTimer()

            }
            else {
                stopTimer()
                binding.startbtn.text = "시작"
                binding.startbtn.setBackgroundResource(R.drawable.startbtn)
            }

        }

        binding.endbtn.setOnClickListener {
            resetTimer()
        }

        binding.nextbutton.setOnClickListener() {
            val intent = Intent(this, plogging_end::class.java)
            startActivity(intent)
        }
    }



    private fun startTimer() {
        time--
        timerTask = timer(period = 1000) {
            time++
            isRunning = 0
            val sec = time
            val min = time / 60
            val hour = time / 3600
            var n : Int = 2
            var tempsec = sec % 60
            var tempmin = min % 60

            runOnUiThread() {
                binding.timer1?.text = "${hour.toString().padStart(n, '0')} : ${tempmin.toString().padStart(n, '0')} : ${tempsec.toString().padStart(n, '0')}"
            }

        }
    }
    private fun stopTimer() {
        timerTask?.cancel()
        isRunning = 1
    }

    private fun resetTimer() {
        timerTask?.cancel()
        time = 0
        binding.timer1?.text = "00 : 00 : 00"
    }
}
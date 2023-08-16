package com.example.ploggers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ploggers.databinding.ActivityCreatePostBinding
import com.example.ploggers.databinding.ActivityMainBinding
import com.example.ploggers.databinding.ActivityPloggingTimerBinding
import java.util.Timer

class plogging_timer : AppCompatActivity() {

    private lateinit var binding: ActivityPloggingTimerBinding
    private var isRunning = false
    private var timerTask: Timer? = null
    private var time = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPloggingTimerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.timerButton.setOnClickListener() {
            if(!isRunning){
                start()
            }
            else{
                pause()
            }
        }
        binding.resetButton.setOnClickListener() {
            reset()
        }


    }


    private fun start() {
        timerTask = kotlin.concurrent.timer(period = 1000) {	// timer() 호출
            time++	// period=1000, 1초마다 time를 1씩 증가
            isRunning = true

            var sec = time
            var min = sec/60
            var hour = min/60

            runOnUiThread {
                binding.timer1?.text = "${hour} : ${min%60} : ${sec%60}"
            }
        }
    }

    private fun pause() {
        timerTask?.cancel()	// 안전한 호출(?.)로 timerTask가 null이 아니면 cancel() 호출
        isRunning = false
    }

    private fun reset() {
        time = 0		// 시간저장 변수 초기화
        isRunning = false	// 현재 진행중인지 판별하기 위한 Boolean변수 false 세팅

        runOnUiThread {
            binding.timer1?.text = "0 : 0 : 0"
        }
    }

}


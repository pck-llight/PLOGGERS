package com.example.ploggers

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.ploggers.databinding.ActivityMainBinding
import com.example.ploggers.databinding.ActivityPloggingTimerBinding
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class MainActivity : AppCompatActivity() {
    private val fbStorage = FirebaseStorage.getInstance()
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

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_MEDIA_IMAGES),101)

    }
}
package com.example.ploggers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ploggers.databinding.ActivityLoginBinding
import com.example.ploggers.databinding.ActivityStartBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var id: String
    private lateinit var pw: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        val intentmain = Intent(this, MainActivity::class.java)
        val intentsignup = Intent(this, sign_up::class.java)

        binding.signInBtn.setOnClickListener() {
            startActivity(intentsignup)
        }

        binding.loginBtn.setOnClickListener() {
            id = binding.inputId.text.toString()
            pw = binding.inputPw.text.toString()
            register(id, pw)
            startActivity(intentmain)
        }

        }
    fun register(id : String, pw : String) {
        val user = hashMapOf<String, String>(
            "id" to id,
            "pw" to pw
        )

        db.collection("user").document("docId")
            .set(user)
    }


}








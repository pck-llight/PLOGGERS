package com.example.ploggers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ploggers.databinding.ActivityLoginBinding
import com.example.ploggers.databinding.ActivitySignUpBinding
import com.example.ploggers.databinding.ActivityStartBinding
import com.google.firebase.firestore.FirebaseFirestore

class sign_up : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var pwagain: String
    private lateinit var hometown : String
    private lateinit var name : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        binding.nextbtn.setOnClickListener() {
            id = binding.inputId.text.toString()
            pw = binding.inputPw.text.toString()
            pwagain = binding.inputPwAgain.text.toString()
            hometown = binding.inputHometown.text.toString()
            name = binding.inputName.text.toString()
            register(id, pw, hometown, name)

            if (pw == pwagain) {
                val intent = Intent(this, login::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                binding.inputPw.text = null
                binding.inputPwAgain.text = null
            }

        }

    }
    fun register(id : String, pw : String, hometown : String, name : String) {
        val user = hashMapOf<String, String>(
            "id" to id,
            "pw" to pw,
            "hometown" to hometown,
            "name" to name
        )

        db.collection("user").document("userinfo")
            .set(user)
    }


}

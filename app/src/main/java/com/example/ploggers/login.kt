package com.example.ploggers

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
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

        val intentmain = Intent(this, MainActivity::class.java)
        val intentsignup = Intent(this, sign_up::class.java)
        db = FirebaseFirestore.getInstance()


        fun register(id: String, pw: String) {
            val user = hashMapOf<String, String>(
                "id" to id,
                "pw" to pw
            )

            db.collection("user").document("docId")
                .set(user)
        }




        binding.loginBtn.setOnClickListener() {
            var idlenth = binding.inputId.text.toString().length
            var pwlenth = binding.inputPw.text.toString().length

            if (idlenth == 0 || pwlenth == 0) {
                Toast.makeText(this, "아이디와 비밀번호를 다시 입력해주세요", Toast.LENGTH_SHORT).show()
                binding.inputId.setBackgroundResource(R.drawable.error_input)
                binding.inputPw.setBackgroundResource(R.drawable.error_input)
                binding.inputId.text = null
                binding.inputId.text = null
            }
            else {
                id = binding.inputId.text.toString()
                pw = binding.inputPw.text.toString()
                register(id, pw)
                startActivity(intentmain)
            }
        }
        binding.inputId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.inputId.text.toString().length == 0) {
                    binding.inputId.setBackgroundResource(R.drawable.error_input)
                }
                else {
                    binding.inputId.setBackgroundResource(R.drawable.login_input)
                }
            }

        })
        binding.inputPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.inputPw.text.toString().length == 0) {
                    binding.inputPw.setBackgroundResource(R.drawable.error_input)
                }
                else {
                    binding.inputPw.setBackgroundResource(R.drawable.login_input)
                }
            }

        })




        binding.signInBtn.setOnClickListener() {
            startActivity(intentsignup)
        }


    }
}








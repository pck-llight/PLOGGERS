package com.example.ploggers

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
            id = binding.idinput.text.toString()
            pw = binding.pwinput.text.toString()
            pwagain = binding.inputPwAgain.text.toString()
            hometown = binding.inputHometown.text.toString()
            name = binding.inputName.text.toString()
            register(id, pw, hometown, name)

            if (pw != pwagain) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                binding.pwinput.text = null
                binding.inputPwAgain.text = null
            }
            else if (pw == pwagain && pw.isNotEmpty() && pwagain.isNotEmpty() && hometown.isNotEmpty() && name.isNotEmpty() && id.isNotEmpty()) {
                val intent = Intent(this, login::class.java)
                startActivity(intent)
            }

            if (id.length == 0) {
                binding.idinput.setBackgroundResource(R.drawable.error_input)
            }
            if (pw.length == 0) {
                binding.pwinput.setBackgroundResource(R.drawable.error_input)
            }
            if (pwagain.length == 0) {
                binding.inputPwAgain.setBackgroundResource(R.drawable.error_input)
            }
            if (hometown.length == 0) {
                binding.inputHometown.setBackgroundResource(R.drawable.error_input)
            }
            if (name.length == 0) {
                binding.inputName.setBackgroundResource(R.drawable.error_input)
            }
        }

        binding.idinput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.idinput.text.toString().isEmpty()) {
                    binding.idinput.setBackgroundResource(R.drawable.error_input)
                }
                else {
                    binding.idinput.setBackgroundResource(R.drawable.login_input)
                }
            }

        })

        binding.pwinput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.pwinput.text.toString().isEmpty()) {
                    binding.pwinput.setBackgroundResource(R.drawable.error_input)
                }
                else {
                    binding.pwinput.setBackgroundResource(R.drawable.login_input)
                }
            }

        })

        binding.inputPwAgain.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.inputPwAgain.text.toString().isEmpty()) {
                    binding.inputPwAgain.setBackgroundResource(R.drawable.error_input)
                }
                else {
                    binding.inputPwAgain.setBackgroundResource(R.drawable.login_input)
                }
            }

        })

        binding.inputHometown.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.inputHometown.text.toString().isEmpty()) {
                    binding.inputHometown.setBackgroundResource(R.drawable.error_input)
                }
                else {
                    binding.inputHometown.setBackgroundResource(R.drawable.login_input)
                }
            }

        })

        binding.inputName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.inputName.text.toString().isEmpty()) {
                    binding.inputName.setBackgroundResource(R.drawable.error_input)
                }
                else {
                    binding.inputName.setBackgroundResource(R.drawable.login_input)
                }
            }

        })

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

package com.example.ploggers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ploggers.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        val intentTimer = Intent(this, ploggingtimer::class.java)
        val intentPost = Intent(this, create_post::class.java)

        binding.timerStartButton.setOnClickListener {
            startActivity(intentTimer)
        }

        binding.writeButton.setOnClickListener {
            startActivity(intentPost)
        }

//        db.collection("post").get().addOnSuccessListener {
//            val list = it.documents.map { doc ->
//                doc.toObject<Post>()!!
//            }
//            binding.post.layoutManager = LinearLayoutManager(applicationContext)
//            binding.post.adapter = PostAdapter(list)
//        }
        db.collection("post").get().addOnSuccessListener { querySnapshot ->
            val postList = mutableListOf<Post>()

            for (document in querySnapshot.documents) {
                val post = document.toObject<Post>()
                if (post != null) {
                    postList.add(post)
                }
            }

            binding.post.layoutManager = LinearLayoutManager(applicationContext)
            binding.post.adapter = PostAdapter(postList)
        }

        initializeViews()
    }

    fun initializeViews() {
        binding.post.layoutManager = LinearLayoutManager(this)
        binding.post.adapter
    }


}
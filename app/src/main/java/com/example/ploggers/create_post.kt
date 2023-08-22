package com.example.ploggers

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts.Photo
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import com.example.ploggers.databinding.ActivityCreatePostBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.zip.Inflater

class create_post : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePostBinding

    lateinit var imageIv: ImageView
    lateinit var textEt: EditText

    var selectImage: Uri? = null

    lateinit var storage: FirebaseStorage
    lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storage = FirebaseStorage.getInstance()
        firestore = FirebaseFirestore.getInstance()

        binding.img1.setOnClickListener() {
            if (selectImage != null) {
                var fileName =
                    SimpleDateFormat("yyyyMMddHHmmss").format(Date()) // 파일명이 겹치면 안되기 떄문에 시년월일분초 지정
                storage.getReference().child("image").child(fileName)
                    .putFile(selectImage!!)//어디에 업로드할지 지정
                    .addOnSuccessListener { taskSnapshot -> // 업로드 정보를 담는다
                        taskSnapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener { it ->
                            var imageUrl = it.toString()
                            val photo = Photo(textEt.text.toString(), imageUrl)
                            firestore.collection("photo")
                                .document().set(photo)
                                .addOnSuccessListener {
                                    finish()
                                }
                        }
                    }
            }

        }

    }
}
package com.example.ploggers

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts.Photo
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ploggers.databinding.ActivityCreatePostBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.zip.Inflater

class create_post : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePostBinding
    private lateinit var db: FirebaseFirestore
    lateinit var imageIv: ImageView
    lateinit var textEt: EditText

    var selectImage: Uri? = null

    lateinit var storage: FirebaseStorage
    lateinit var firestore: FirebaseFirestore

    val REQ_GALLERY = 1
    val pickMultipleMedia =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
            if (uris.isNotEmpty()) {
                Log.d("PhotoPicker", "Number of items selected: ${uris.size}")
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = FirebaseFirestore.getInstance()

        storage = FirebaseStorage.getInstance()
        firestore = FirebaseFirestore.getInstance()

        binding.img1.setOnClickListener() {
            pickMultipleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }

        val input : String = binding.text1.toString()

        binding.btn.setOnClickListener() {
            upload(input)
        }

    }

    fun upload(input: String){
        var story = hashMapOf <String, String> (
            "input" to input
        )
        db.collection("story").document("userstory")
            .set(story)
    }

//    private fun selectGallery(){
//        val readPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
//
//        //권한 확인
//        if(readPermission == PackageManager.PERMISSION_DENIED){
//            // 권한 요청
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_MEDIA_IMAGES), REQ_GALLERY)
//
//        }else{
//            // 권한이 있는 경우 갤러리 실행
//            val intent = Intent(Intent.ACTION_PICK)
//            // intent의 data와 type을 동시에 설정하는 메서드
//            intent.setDataAndType(
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                "image/*"
//            )
//
//            imageResult.launch(intent)
//        }
//    }

}
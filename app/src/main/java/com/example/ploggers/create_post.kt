package com.example.ploggers

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.ploggers.databinding.ActivityCreatePostBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.delay as delay

class create_post : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePostBinding

    private lateinit var db: FirebaseFirestore
    lateinit var storage: FirebaseStorage

    var urilst: MutableList<String> = mutableListOf()

    val pickMultipleMedia =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
            if (uris.isNotEmpty()) {
                Log.d("PhotoPicker", "Number of items selected: ${uris.size}")
                for ( i in 0 .. uris.size-1) {
                    Log.d("urischeck", "${uris[i]}")
                    urilst.add("${uris[i]}")
                }

            }else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storage = FirebaseStorage.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.img1.setOnClickListener() {//이미지 추가 버튼 클릭
            pickMultipleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }


        binding.btn.setOnClickListener() {//
            val images = mutableListOf<String>()// 공유 버튼 클릭
            urilst.forEachIndexed { index, url ->
                var timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
                var fileName = "IMAGE_${timestamp+index}.jpg"
                var storageRef = storage.reference.child("images").child(fileName)
                var uploadTask = storageRef.putFile(Uri.parse(url))
                uploadTask.addOnSuccessListener { taskSnapshot ->
                    // 업로드 성공 시
                    storageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                        var imageUrl = downloadUri.toString()
                        // 이미지 URL을 활용하여 추가 작업 수행
                        // 예: 데이터베이스에 이미지 URL 저장 등
                        Log.d("UploadSuccess", "Image uploaded. URL: $imageUrl")
                        images.add(imageUrl)
                        if(images.size == urilst.size) {
                            val post = Post(binding.text1.text.toString(), images)
                            postUpload(post)
                        }
                    }
                }.addOnFailureListener { e ->
                    // 업로드 실패 시
                    Log.e("UploadError", "Image upload failed. Error: ${e.message}")
                }
            }

//            var story:String = binding.text1.text.toString()
//            Log.d("storycheck", "$story")
//            storyupload(story)

            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }
    }


    /*    fun storyupload(story: String) {
            var timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            var fileName = "post${timestamp}"
            val storypost = hashMapOf<String, Any>(
                "$fileName" to story
            )

            db.collection("post").document("userpost")
                .update(storypost)
        }
        */
    fun postUpload(post:Post) {
        db.collection("post").add(post)
    }
}
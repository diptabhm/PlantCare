package com.example.gdsc2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class TestingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing)

        val getimage = findViewById<ImageView>(R.id.imageView)
        val bundle: Bundle? = intent.extras

        val uri: Uri = intent.getParcelableExtra("imageUri")!!
        getimage.setImageURI(uri)

    }
}
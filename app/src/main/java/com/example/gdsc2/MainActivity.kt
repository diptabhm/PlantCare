package com.example.gdsc2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.cast.framework.media.ImagePicker
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<ExtendedFloatingActionButton>(R.id.add_button)
        val imagesent: ImageView

        fab?.setOnClickListener {

            //________________________________________________________________________________
//                Previous Codes
//val iGallery = Intent(Intent.ACTION_PICK)
//                iGallery.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//                    startActivityForResult(iGallery, 1000)
            //_______________________________________________________________________________
            com.github.dhaval2404.imagepicker.ImagePicker.with(this).crop().compress(250)
                .maxResultSize(600, 600)
                .start(1000)

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            val intent = Intent(this@MainActivity, TestingActivity::class.java)
            intent.putExtra("imageUri", uri);
            startActivity(intent)

        }
    }
}

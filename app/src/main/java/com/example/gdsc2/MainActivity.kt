package com.example.gdsc2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.cast.framework.media.ImagePicker
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<ExtendedFloatingActionButton>(R.id.add_button)

        fab?.setOnClickListener {

            //________________________________________________________________________________
//                Previous Codes
//val iGallery = Intent(Intent.ACTION_PICK)
//                iGallery.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//                    startActivityForResult(iGallery, 1000)
            //_______________________________________________________________________________
            com.github.dhaval2404.imagepicker.ImagePicker.with(this).crop().
            compress(250).
            maxResultSize(600,600)
                .start(1000)

        }
    }
}
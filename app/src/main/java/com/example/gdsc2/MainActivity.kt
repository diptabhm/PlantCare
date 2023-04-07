package com.example.gdsc2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc2.datahub.UserData
import com.example.gdsc2.datahub.adapter
import com.google.android.gms.cast.framework.media.ImagePicker
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var recylerView: RecyclerView
    private lateinit var datalist: ArrayList<UserData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<ExtendedFloatingActionButton>(R.id.add_button)
        recylerView = findViewById(R.id.recyclerView)
        recylerView.layoutManager = LinearLayoutManager(this)

         datalist = arrayListOf<UserData>()
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
            datalist.add(UserData(uri,"test","test"))

            val intent = Intent(this@MainActivity, TestingActivity::class.java)
            intent.putExtra("imageUri", uri)
            startActivity(intent)
            recylerView.adapter = adapter(datalist)

        }
    }
}

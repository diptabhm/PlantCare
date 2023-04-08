package com.example.gdsc2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class TestingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing)

        val getimage = findViewById<ImageView>(R.id.imageView)
        val bundle: Bundle? = intent.extras

        val text = findViewById<TextView>(R.id.disease_name)

        val uri: Uri = intent.getParcelableExtra("imageUri")!!
        val textss: String? = intent.getIntExtra("disease",0).toString()
        getimage.setImageURI(uri)

        if(textss=="0")
        text.text = "Early Blight"
        else if(textss == "1")
            text.text = "Late Blight"
        else
            text.text = "Healthy"

    }
}
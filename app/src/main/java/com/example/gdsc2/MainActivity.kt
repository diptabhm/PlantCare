package com.example.gdsc2

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Adapter
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc2.datahub.UserData
import com.example.gdsc2.datahub.adapter
import com.example.gdsc2.ml.GdscModel
import com.google.android.gms.cast.framework.media.ImagePicker
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.coroutines.delay
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.image.ops.TransformToGrayscaleOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var recylerView: RecyclerView
    private lateinit var datalist: ArrayList<UserData>
    private var imageProcessor = ImageProcessor.Builder().
        add(ResizeOp(256, 256,ResizeOp.ResizeMethod.BILINEAR)).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //image processor

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


            var bitmap : Bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,uri)
            var tensorImage = TensorImage(DataType.FLOAT32)
            tensorImage.load(bitmap)

            tensorImage = imageProcessor.process(tensorImage)

            val model = GdscModel.newInstance(this)


// Creates inputs for reference.
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 256, 256, 3), DataType.FLOAT32)

            inputFeature0.loadBuffer(tensorImage.buffer)

// Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray

            var maxId = 0
            outputFeature0.forEachIndexed{
                index, fl ->
                if(outputFeature0[maxId]<fl){
                    maxId = index
                }
            }

            var text : String
            if(maxId==0)
                text = "Early Blight"
            else if(maxId == 1)
                text = "Late Blight"
            else
                text = "Healthy"


// Releases model resources if no longer used.
            model.close()

            datalist.add(UserData(uri,"Potato leaf",text))
            val intent = Intent(this@MainActivity, TestingActivity::class.java)
            intent.putExtra("imageUri", uri)
            intent.putExtra("disease",maxId)
            startActivity(intent)
            recylerView.adapter = adapter(datalist)

        }
    }
}

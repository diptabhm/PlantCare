package com.example.gdsc2.datahub

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc2.R
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.NonDisposableHandle.parent

class adapter(private val dataList: ArrayList<UserData>) :
    RecyclerView.Adapter<adapter.Myviewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items,
            parent, false)
        return adapter.Myviewholder(itemView)
    }

    override fun onBindViewHolder(holder: Myviewholder, position: Int) {
        val currentItem = dataList[position]
        holder.itemName.text = currentItem.item_name
        holder.photo.setImageURI(currentItem.imageId)
        holder.disease_name.text = currentItem.disease
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class Myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemName: TextView = itemView.findViewById(R.id.itemname)
        val photo: ImageView = itemView.findViewById(R.id.item_image)
        val disease_name: TextView = itemView.findViewById(R.id.disease_name)

    }
}
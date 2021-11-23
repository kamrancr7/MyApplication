package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import data_model.Data

class MyAdapter(private val dataList: MutableList<Data>) : RecyclerView.Adapter<MyHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val data = dataList[position]
        val userFullNameTextView = holder.itemView.findViewById<TextView>(R.id.product_full_name)
        val userAvatar = holder.itemView.findViewById<ImageView>(R.id.product_image)
        val price = holder.itemView.findViewById<TextView>(R.id.price)


        val fullName = "${data.name}"
        userFullNameTextView.text = fullName
        Picasso.get()
            .load(data.image_urls_thumbnails[0])
            .into(userAvatar)

        price.text = data.price

        holder.itemView.setOnClickListener{
            NewScreen(context,data.name,data.price,data.image_urls[0],data.created_at)

        }
    }

    override fun getItemCount(): Int = dataList.size

    fun NewScreen(context: Context, name: String, price: String, image_urls: String, created_at: String) {
        val intent = Intent(context, ProductDetail::class.java).apply {
            putExtra("productName",name)
            putExtra("productPrice",price)
            putExtra("productImage",image_urls)
            putExtra("productCreate",created_at)
        }
        context.startActivity(intent)
    }
}
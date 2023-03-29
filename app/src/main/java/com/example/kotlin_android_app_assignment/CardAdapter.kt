package com.example.kotlin_android_app_assignment


import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class CardAdapter(private val infoList: ArrayList<User>) : RecyclerView.Adapter<CardAdapter.ViewHolder>(){
    private lateinit var context: Context

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val img = itemView.findViewById<ImageView>(R.id.infoImage)
        val title = itemView.findViewById<TextView>(R.id.infoTitle)
        val des = itemView.findViewById<TextView>(R.id.infoDes)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.des.text = "ASD"
//        val info = infoList[position]
//
//        Glide.with(context).load(info.image).into(holder.img)
//
//        val title = StringBuilder()
//        title.append(info.infoTitle)
//
//        val des = StringBuilder()
//        des.append(info.infoDes)
//
//        println(des)
//        holder.title.text = title
//        holder.des.text = des
    }

    override fun getItemCount(): Int {
        return infoList.size
    }
}
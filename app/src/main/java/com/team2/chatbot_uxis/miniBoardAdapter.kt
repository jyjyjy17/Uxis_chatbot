package com.team2.chatbot_uxis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class miniBoardAdapter(val contents:ArrayList<String>):RecyclerView.Adapter<miniBoardAdapter.BoardViewHolder>(){
    class BoardViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var title = itemView.findViewById<TextView>(R.id.tv_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): miniBoardAdapter.BoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return miniBoardAdapter.BoardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contents.size
    }

    override fun onBindViewHolder(holder: miniBoardAdapter.BoardViewHolder, position: Int) {
        holder.title.text = contents[position]
    }

}
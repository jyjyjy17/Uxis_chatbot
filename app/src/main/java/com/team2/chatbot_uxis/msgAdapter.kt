package com.team2.chatbot_uxis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.msg_box.view.*

class msgAdapter(var datas:ArrayList<msgItem>):RecyclerView.Adapter<msgAdapter.ViewHolder>(){
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var name = view.tv_name
        var time = view.tv_time
        var message = view.tv_msg

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.msg_box,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = datas[position].name
        holder.time.text = datas[position].time
        holder.message.text = datas[position].message
    }

}
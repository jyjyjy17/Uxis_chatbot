package com.team2.chatbot_uxis

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.msg_box.view.*

class msgAdapter(var datas:ArrayList<msgItem>):RecyclerView.Adapter<msgAdapter.ViewHolder>(){
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var name = view.tv_name
        //var time = view.tv_time
        var message = view.tv_msg
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        lateinit var view:View
        var context:Context = parent.context

        if(viewType==0){
            view =LayoutInflater.from(parent.context).inflate(R.layout.msg_box,parent,false)
            return ViewHolder(view)
        }
        else{
            view =LayoutInflater.from(parent.context).inflate(R.layout.msg_box_bot,parent,false)
            return ViewHolder(view)
        }
//        var view = LayoutInflater.from(parent.context).inflate(R.layout.msg_box,parent,false)
//        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.name.text = datas[position].name
        holder.message.text = datas[position].message
    }

}
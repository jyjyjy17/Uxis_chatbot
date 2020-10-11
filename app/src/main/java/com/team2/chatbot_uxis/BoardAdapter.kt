package com.team2.chatbot_uxis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BoardAdapter(val BoardList:ArrayList<BoardData>):RecyclerView.Adapter<BoardAdapter.BoardViewHolder>(){
    class BoardViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var title = itemView.findViewById<TextView>(R.id.tv_title)
        var answer = itemView.findViewById<TextView>(R.id.tv_answer)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardAdapter.BoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return BoardViewHolder(view)
        //미리 정의 해 둔 list_item xml을 view로 만들어준다.
     }

    override fun getItemCount(): Int {
        return BoardList.size
    }

    override fun onBindViewHolder(holder: BoardAdapter.BoardViewHolder, position: Int) {
        holder.title.text = BoardList[position].board_title
        holder.answer.text = BoardList[position].board_condition

        //requestmanager 써야 함.

    }

}
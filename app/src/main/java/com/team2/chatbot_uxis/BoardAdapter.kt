package com.team2.chatbot_uxis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class BoardAdapter(val BoardList:ArrayList<BoardItem>):RecyclerView.Adapter<BoardAdapter.BoardViewHolder>(){
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
        //boardItem :title,answer,content로 구성.
        holder.title.text = BoardList[position].message
        if( BoardList[position].answerType ==0)
            holder.answer.text="미답변"
        else
            holder.answer.text="답변완료"

        //requestmanager 써야 함
        holder.itemView.setOnClickListener {
            itemClickListner.onClick(it, position)
        }
    }
    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    //클릭리스너 선언
    private lateinit var itemClickListner: ItemClickListener

    //클릭리스너 등록 매소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

}
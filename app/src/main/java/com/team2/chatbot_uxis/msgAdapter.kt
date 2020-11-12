package com.team2.chatbot_uxis

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.msg_box_answer.view.*
import org.json.JSONException
import org.json.JSONObject

class msgAdapter(private var context: Context, var datas:ArrayList<msgItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> ViewHolderQuestion(LayoutInflater.from(context).inflate(R.layout.msg_box_question,parent,false))
            1 -> ViewHolderAnswer(LayoutInflater.from(context).inflate(R.layout.msg_box_answer,parent,false))
            else -> ViewHolderAnswerWithCover(LayoutInflater.from(context).inflate(R.layout.msg_box_answer,parent,false))
        }
    }

    override fun getItemCount() = datas.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderQuestion -> {
                holder.itemView.run {
//                    tv_name.text = datas[position].name
                    tv_name.text = "질문"
                    tv_msg.text = datas[position].message
                }
            }
            is ViewHolderAnswer -> {
                holder.itemView.run {
                    tv_name.text = "답변"
                    tv_msg.text = datas[position].message
                }
            }
            else -> {
                holder.itemView.run {
                    tv_name.text = "답변 with Cover"
                    tv_msg.text = datas[position].message
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
//        return datas[position].viewType
        var data = datas[position]
        if ( data.name == "chatbot" ) {
            try {
                if (JSONObject(data.message).has("cover"))
                    return 2
            } catch (e: JSONException) {
                return 1
            }
            return 1
        } else {
            return 0
        }
    }

    class ViewHolderQuestion(view: View):RecyclerView.ViewHolder(view)
    class ViewHolderAnswerWithCover(view: View):RecyclerView.ViewHolder(view)
    class ViewHolderAnswer(view: View):RecyclerView.ViewHolder(view)

}
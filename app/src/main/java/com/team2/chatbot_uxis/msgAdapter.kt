package com.team2.chatbot_uxis

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.msg_box_answer.view.*
import kotlinx.android.synthetic.main.msg_box_answer.view.tv_name
import kotlinx.android.synthetic.main.msg_box_answer_cover.view.*
import org.json.JSONException
import org.json.JSONObject


class msgAdapter(private var context: Context, var datas:ArrayList<msgItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> ViewHolderQuestion(LayoutInflater.from(context).inflate(R.layout.msg_box_question,parent,false))
            1 -> ViewHolderAnswer(LayoutInflater.from(context).inflate(R.layout.msg_box_answer,parent,false))
            2 -> ViewHolderAnswerWithCover(LayoutInflater.from(context).inflate(R.layout.msg_box_answer_cover,parent,false))
            else -> ViewHolderAnswerWithCards(LayoutInflater.from(context).inflate(R.layout.msg_box_answer_cover,parent,false))
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
                    var message = datas[position].message
                    try {
                        message = JSONObject(datas[position].message).getString("description")
                    } catch (e: JSONException) {
                        println(e)
                    }
                    tv_name.text = "With Text"
                    tv_msg.text = message
                }
            }
            is ViewHolderAnswerWithCover -> {
                holder.itemView.run {
                    tv_name.text = "With Cover"
                    try {
                        var res = JSONObject(datas[position].message)
                        var cover = res.getJSONObject("cover")
                        imageTitle.text = cover.getString("title")
                        Picasso.with(context).load(Uri.parse(cover.getJSONObject("data").getString("imageUrl"))).into(image)
                        description.text = cover.getJSONObject("data").getString("description")
                        if ( res.has("contentTable") ) {
                            val urlIntent = Intent(Intent.ACTION_VIEW)
                            val linkData = res.getJSONArray("contentTable")
                                .getJSONArray(0)
                                .getJSONObject(0)
                                .getJSONObject("data")
                            val linkURL = linkData.getJSONObject("data")
                                .getJSONObject("action")
                                .getJSONObject("data")
                                .getString("url")
                            val linkTitle = linkData.getString("title")
                            link_btn.setOnClickListener {
                                urlIntent.data = Uri.parse(linkURL)
                                context.startActivity(urlIntent)
                            }
                            link_btn.text = linkTitle
                            link_btn.visibility = View.VISIBLE
                        } else {
                            link_btn.visibility = View.GONE
                        }

                    } catch (e: JSONException) {
                        println(e)
                    }
                }
            }
            else -> {
                holder.itemView.run {
                    tv_name.text = "With Cards"
                    try {
                        var res = JSONObject(datas[position].message)
                        var cover = res.getJSONObject("cover")
                        imageTitle.text = cover.getString("title")
                        Picasso.with(context).load(Uri.parse(cover.getJSONObject("data").getString("imageUrl"))).into(image)
                        description.text = cover.getJSONObject("data").getString("description")
                    } catch (e: JSONException) {
                        println(e)
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
//        return datas[position].viewType
        var data = datas[position]
        if ( data.name == "chatbot" ) {
            try {
                var jsonData = JSONObject(data.message)
                if (jsonData.has("cover"))
                    return 2
                else if (jsonData.has("cards"))
                    return 3
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
    class ViewHolderAnswerWithCards(view: View):RecyclerView.ViewHolder(view)

}
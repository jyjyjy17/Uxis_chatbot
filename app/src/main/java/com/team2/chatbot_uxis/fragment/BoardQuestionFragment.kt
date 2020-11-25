package com.team2.chatbot_uxis.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.team2.chatbot_uxis.BoardAdapter
import com.team2.chatbot_uxis.BoardItem
import com.team2.chatbot_uxis.R
import com.team2.chatbot_uxis.miniBoardAdapter
import kotlinx.android.synthetic.main.fragment_board_question.*
import kotlinx.android.synthetic.main.fragment_board_question.view.*
import kotlinx.android.synthetic.main.fragment_question.back_button
import java.util.*


class BoardQuestionFragment : Fragment(), View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var boardList :ArrayList<BoardItem>
    lateinit var navController: NavController
    lateinit var database : FirebaseDatabase
    lateinit var chatRef : DatabaseReference
    lateinit var key :String
    var contents:ArrayList<String> = arrayListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_board_question, container, false)
        var context =view.context


        viewManager= LinearLayoutManager(context)
        viewAdapter= miniBoardAdapter(contents)

        recyclerView=view.findViewById<RecyclerView>(R.id.miniRecyclerView).apply {
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            layoutManager =viewManager
            adapter=viewAdapter
        }
        var tv = view.questionTitle
        tv.text=arguments?.getString("title")
        key = arguments?.getString("key").toString()
        database = Firebase.database
        chatRef = database.getReference("Question/"+key)
        //답변 리사이클러 뷰

        chatRef.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val q: BoardItem =snapshot.getValue<BoardItem>() as BoardItem
                for(i in q.contents)
                    contents.add(i)
                recyclerView.adapter?.notifyDataSetChanged()
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        send_button.setOnClickListener(this)
        back_button.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.back_button->{
                navController.navigate(R.id.action_boardQuestionFragment_to_mainFragment)
            }
            R.id.send_button->{
                clickSend(v)
            }
        }
    }
    private fun clickSend(v: View) {
        var message = et.text.toString()
        val updateMap: MutableMap<String, Any> = HashMap()

        contents.add(message)
        updateMap["contents"] = contents
        chatRef.updateChildren(updateMap)
        recyclerView.adapter?.notifyDataSetChanged()

        //EditText에 있는 글씨 지우기
        et.setText("")

    }
}
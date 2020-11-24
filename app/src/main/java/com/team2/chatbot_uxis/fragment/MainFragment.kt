package com.team2.chatbot_uxis.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.team2.chatbot_uxis.*
import com.team2.chatbot_uxis.R
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(),View.OnClickListener {
    lateinit var navController: NavController
    lateinit var database : FirebaseDatabase
    lateinit var chatRef : DatabaseReference


    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var boardList :ArrayList<BoardItem>
    var keys :ArrayList<String> = arrayListOf()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        database = Firebase.database
        chatRef = database.getReference("Question")

        var view = inflater.inflate(R.layout.fragment_main,container,false)
        var context =view.context
        boardList= ArrayList<BoardItem>()

        viewManager=LinearLayoutManager(context)
        viewAdapter=BoardAdapter(boardList)

        recyclerView=view.findViewById<RecyclerView>(R.id.BoardRecyclerView).apply {
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            layoutManager =viewManager
            adapter=viewAdapter
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)
        question_button.setOnClickListener(this)
        chatbot_button.setOnClickListener(this)

        (viewAdapter as BoardAdapter).setItemClickListener(object : BoardAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                //data전송
                var bundle:Bundle =
                    bundleOf("title" to boardList[position].message,"key" to keys[position])

//                bundle.putString("title",boardList[position].message)
//                bundle.putString("content",boardList[position].content)
                navController.navigate(R.id.action_mainFragment_to_boardQuestionFragment,bundle)
            }
        })

        //boardlist에 레트로핏으로 질문/답변 가져와서 값 저장.
        //adapter연결
        //adapter.setonclicklistener 정의

        chatRef.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    val q:BoardItem=i.getValue<BoardItem>() as BoardItem
                    boardList.add(q)
                    keys.add(i.key.toString())
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
        })

    }

    override fun onClick(v: View?) {
       when(v?.id){
           R.id.question_button->{
                navController.navigate(R.id.action_mainFragment_to_questionFragment)
           }
           R.id.chatbot_button->{
               navController.navigate(R.id.action_mainFragment_to_chatbotFragment)
           }



       }
    }


}

package com.team2.chatbot_uxis.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.team2.chatbot_uxis.BoardItem
import com.team2.chatbot_uxis.R
import com.team2.chatbot_uxis.msgItem
import kotlinx.android.synthetic.main.fragment_chatbot.*
import kotlinx.android.synthetic.main.fragment_chatbot.back_button
import kotlinx.android.synthetic.main.fragment_question.*
import java.util.*
import kotlin.collections.ArrayList

class QuestionFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController
    lateinit var database : FirebaseDatabase //질문 등록을 위한 firebase
    lateinit var chatRef : DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        database = Firebase.database
        chatRef = database.getReference("Question")

       return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        back_button.setOnClickListener(this)
        qRegister_button.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.back_button->{
                navController.navigate(R.id.action_questionFragment_to_mainFragment)
            }
            R.id.qRegister_button->{
                clickRegister(v)
                navController.navigate(R.id.action_questionFragment_to_mainFragment)
            }
        }
    }

    private fun clickRegister(v: View) {
        //질문 등록시 사용하는 data BoardItem
        //필요한 var: message 정도임

        var message = et_q.text.toString()
        var answerType = 0;
        var contents:ArrayList<String> = ArrayList()

        var calendar = Calendar.getInstance()
        var time:String = calendar.get(Calendar.HOUR_OF_DAY).toString()+":"+calendar.get(Calendar.MINUTE)

        var msg = BoardItem(message,answerType,contents)
        chatRef.push().setValue(msg) //database에 방금 입력한 메세지 데이터 (node로) 추가

        //디비 등록 완료
        //챗봇에 질문 전송

    }
}
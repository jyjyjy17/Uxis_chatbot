package com.team2.chatbot_uxis.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.team2.chatbot_uxis.BoardAdapter
import com.team2.chatbot_uxis.R
import com.team2.chatbot_uxis.getBoardResponse
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(),View.OnClickListener {
    lateinit var navController: NavController
    lateinit var BoardList :ArrayList<getBoardResponse>
    lateinit var BoardAdapter:BoardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //boardlist에 레트로핏으로 질문/답변 가져와서 값 저장.
        //adapter연결
        //adapter.setonclicklistener 정의



        navController=Navigation.findNavController(view)

        question_button.setOnClickListener(this)
        chatbot_button.setOnClickListener(this)


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
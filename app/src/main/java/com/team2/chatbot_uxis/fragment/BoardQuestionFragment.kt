package com.team2.chatbot_uxis.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.team2.chatbot_uxis.R
import kotlinx.android.synthetic.main.fragment_board_question.view.*
import kotlinx.android.synthetic.main.fragment_question.*


class BoardQuestionFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_board_question, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var tv = view.questionTitle
        tv.text=arguments?.getString("title")

        val contents = view.answer_content
        contents.text = arguments?.getString("content")
        navController = Navigation.findNavController(view)

        back_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.back_button->{
                navController.navigate(R.id.action_boardQuestionFragment_to_mainFragment)
            }
        }
    }
}
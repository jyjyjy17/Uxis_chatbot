package com.team2.chatbot_uxis

data class getBoardResponse(
    var message : String,
    var data : ArrayList<BoardData>

)
data class BoardData(
    var board_title : String?,
    var board_content : String?,
    var board_answer :String,
    var board_condition:String
)
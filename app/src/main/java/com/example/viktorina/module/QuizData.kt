package com.example.viktorina.module

data class QuizData(
    var textQuestion :String,
    var imageDescription :String,
    var imageQuestion :Int,
    var answers : List<String>
)

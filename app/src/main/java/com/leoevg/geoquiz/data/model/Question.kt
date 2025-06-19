package com.leoevg.geoquiz.data.model

data class Question (
    val id: String,
    val rightAnswer: String,
    val hint: String,
    val answerOptions: List<AnswerOption>,
    val picturesUrls: List<String>
)
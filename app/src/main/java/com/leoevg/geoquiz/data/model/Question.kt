package com.leoevg.geoquiz.data.model

data class Question (
    val id: Int,
    val rightAnswer: Int,
    val hint: String,
    val answerOptions: List<AnswerOption>,
    val imageQuest: String
)
package com.leoevg.geoquiz.data.model

data class Question (
    val id: Int,
    val rightAnswer: String,
    val hint: String,
    val list: List<AnswerOption>,
    val imageQuest: String
)
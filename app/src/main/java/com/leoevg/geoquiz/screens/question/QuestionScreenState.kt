package com.leoevg.geoquiz.screens.question

data class QuestionScreenState (
    var isSilentModeEnabled: Boolean = true,
    var isNightModeEnabled: Boolean = true,
    var selectedAnswerOptionId: Int = -1
)
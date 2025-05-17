package com.leoevg.geoquiz.screens.question

data class QuestionScreenState (
    // val а не var - т.к. изменение через state Flow а не напрямую
    val isSilentModeEnabled: Boolean = true,
    val isNightModeEnabled: Boolean = true,
    val selectedAnswer: String = "",
    val selectedAnswerOptionId: Int? = null,  // TODO - временная затычка. пофиксить
    val error: String? = null,
)



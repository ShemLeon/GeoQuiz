package com.leoevg.geoquiz.screens.question

sealed interface QuestionScreenEvent {
    data class OptionSelected(val selectedOption: String): QuestionScreenEvent
    data object HintBtnClicked: QuestionScreenEvent
    data object ApplyBtnClicked: QuestionScreenEvent
    data object FinishBtnClicked: QuestionScreenEvent
    data object SilentModeBtnClicked: QuestionScreenEvent
    data object NightModeBtnClicked: QuestionScreenEvent
}
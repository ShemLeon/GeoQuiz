package com.leoevg.geoquiz.screens.question

import com.leoevg.geoquiz.data.model.Question

sealed interface QuestionScreenEvent {
    data class OptionSelected(val selectedOption: String): QuestionScreenEvent
    data object HintBtnClicked: QuestionScreenEvent
    data class  ApplyBtnClicked(val question: Question): QuestionScreenEvent
    data object FinishBtnClicked: QuestionScreenEvent
    data object ImageDoubleClicked: QuestionScreenEvent
    data object SilentModeBtnClicked: QuestionScreenEvent
    data object NightModeBtnClicked: QuestionScreenEvent
}
package com.leoevg.geoquiz.screens.rate
import com.leoevg.geoquiz.data.model.Question
import com.leoevg.geoquiz.screens.question.QuestionScreenEvent
import com.leoevg.geoquiz.screens.register.RegisterScreenEvent

sealed interface  RateScreenEvent {
    data class StarsChanged(val selectedStars: Int): RateScreenEvent

    data object SendContentBtnClicked: RateScreenEvent
    data object GoToQuizesBtnClicked: RateScreenEvent
}


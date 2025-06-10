package com.leoevg.geoquiz.screens.rate
import android.net.Uri
import com.leoevg.geoquiz.data.model.Question
import com.leoevg.geoquiz.screens.question.QuestionScreenEvent
import com.leoevg.geoquiz.screens.register.RegisterScreenEvent

sealed interface  RateScreenEvent {
    data class StarsChanged(val selectedStars: Int): RateScreenEvent
    data object GoToQuizzesBtnClicked: RateScreenEvent
    data object SendContentBtnClicked: RateScreenEvent
    data class CountryNameChanged(val countryName: String): RateScreenEvent
    data class ImagePicked(val imageUri: Uri): RateScreenEvent
    data object CountryBottomSheetRequested: RateScreenEvent
    data object CountryBottomSheetDismissed: RateScreenEvent
    data object SaveSuggestionBtnClicked: RateScreenEvent
}


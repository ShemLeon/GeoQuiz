package com.leoevg.geoquiz.screens.rate

import com.leoevg.geoquiz.screens.register.RegisterScreenState
import kotlinx.coroutines.flow.StateFlow

fun onEvent(event: RateScreenEvent){
    val state: StateFlow<RateScreenState> = _state
    // SOLID
    when(event){
        is RateScreenEvent.StarsSelected -> onStarsSelected(event.selectedStars)
        RateScreenEvent.SendContentBtnClicked -> onSendContentBtnClicked()

    }
}
package com.leoevg.geoquiz.screens.finish

sealed interface FinishScreenEvent {
    data object GoToQuizzesBtnClicked : FinishScreenEvent
    data object HelpProjectBtnClicked : FinishScreenEvent
}
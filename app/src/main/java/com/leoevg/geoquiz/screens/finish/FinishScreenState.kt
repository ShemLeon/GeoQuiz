package com.leoevg.geoquiz.screens.finish

data class FinishScreenState(
    var maxScore: Int? = null, // Это поле дублируется с maxScore в ViewModel, возможно, не нужно
    val isLoading: Boolean = false
)
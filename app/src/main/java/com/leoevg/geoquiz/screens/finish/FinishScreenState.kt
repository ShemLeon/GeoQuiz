package com.leoevg.geoquiz.screens.finish

data class FinishScreenState(
    var maxScore: Int? = null,
    val finalScore: Int = 0,
    val isLoading: Boolean = false
)
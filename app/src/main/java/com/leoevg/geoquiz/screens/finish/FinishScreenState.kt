package com.leoevg.geoquiz.screens.finish

// import com.google.common.cache.LoadingCache // Этот импорт здесь не нужен

data class FinishScreenState(
    var maxScore: Int? = null, // Это поле дублируется с maxScore в ViewModel, возможно, не нужно
    val isLoading: Boolean = false
)
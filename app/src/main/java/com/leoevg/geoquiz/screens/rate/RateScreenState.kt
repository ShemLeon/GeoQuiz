package com.leoevg.geoquiz.screens.rate
import android.net.Uri

data class RateScreenState(
    val stars: Int? = null,
    val isLoading: Boolean = false,
    val pickedImageUri: Uri? = null
)
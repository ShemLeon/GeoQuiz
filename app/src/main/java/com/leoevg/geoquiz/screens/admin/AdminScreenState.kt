package com.leoevg.geoquiz.screens.admin

data class AdminScreenState(
    var typeGame: String = "",
    var countryName: String = "",
    var error: String? = null,
    val isApproved: Boolean = false,
    val isRejected: Boolean = false,
    val isLoading: Boolean = false
)

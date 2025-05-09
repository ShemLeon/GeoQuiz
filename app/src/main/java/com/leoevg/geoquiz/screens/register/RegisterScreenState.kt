package com.leoevg.geoquiz.screens.register

data class RegisterScreenState(
    var nickname: String = "",
    var email: String = "",
    val password: String = "",
    var error: String? = null,
    var isRegisteredIn: Boolean = false,
    val isLoading: Boolean = false,
)

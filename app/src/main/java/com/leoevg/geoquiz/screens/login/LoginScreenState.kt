package com.leoevg.geoquiz.screens.login

data class LoginScreenState(
    var email: String = "",
    val password: String = "",
    var error: String? = null,
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false
)

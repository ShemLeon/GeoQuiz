package com.leoevg.geoquiz.screens.login

sealed interface LoginScreenEvent {
    data class  EmailChanged(val email: String): LoginScreenEvent
    data class  PasswordChanged(val password: String): LoginScreenEvent

    data object LoginBtnClicked: LoginScreenEvent

}

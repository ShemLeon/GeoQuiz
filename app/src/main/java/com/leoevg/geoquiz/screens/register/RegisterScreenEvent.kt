package com.leoevg.geoquiz.screens.register

sealed interface RegisterScreenEvent {
    data class  EmailChanged(val email: String): RegisterScreenEvent
    data class  PasswordChanged(val password: String): RegisterScreenEvent
    data class  NicknameChanged(val nickname: String): RegisterScreenEvent

    data object RegisterBtnClicked: RegisterScreenEvent

}

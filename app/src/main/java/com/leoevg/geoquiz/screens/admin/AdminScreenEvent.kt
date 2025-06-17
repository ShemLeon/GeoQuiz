package com.leoevg.geoquiz.screens.admin

sealed interface AdminScreenEvent {
    data object ApproveBtnClicked: AdminScreenEvent
    data object RejectBtnClicked: AdminScreenEvent

    data class  SuggestedTypeGame(val typeGame: String): AdminScreenEvent
    data class  SuggestedCountryName(val countryName: String): AdminScreenEvent
}
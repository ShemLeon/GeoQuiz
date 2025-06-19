package com.leoevg.geoquiz.screens.admin

sealed interface AdminScreenEvent {
    data object ApproveBtnClicked: AdminScreenEvent
    data class  SuggestedTypeGame(val typeGame: String): AdminScreenEvent
    data class  SuggestedCountryName(val countryName: String): AdminScreenEvent
    data object RejectSuggestionClicked: AdminScreenEvent
}
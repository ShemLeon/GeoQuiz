package com.leoevg.geoquiz.screens.admin

import com.leoevg.geoquiz.data.model.TypeGame

sealed interface AdminScreenEvent {
    data object ApproveBtnClicked: AdminScreenEvent
    data class  SuggestedTypeGame(val typeGame: String): AdminScreenEvent
    data class  SuggestedCountryName(val countryName: String): AdminScreenEvent
    data object RejectSuggestionClicked: AdminScreenEvent
    data object ChooseGameModeDialogDismissed: AdminScreenEvent
    data class ChooseGameModeDialogModesSelected(val selectedModesNames: List<String>): AdminScreenEvent
}
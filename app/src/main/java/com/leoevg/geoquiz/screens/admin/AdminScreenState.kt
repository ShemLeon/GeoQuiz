package com.leoevg.geoquiz.screens.admin

import com.leoevg.geoquiz.data.model.Suggestion

data class AdminScreenState(
    var typeGame: String = "",
    var countryName: String = "",
    val suggestionsList: List<Suggestion> = emptyList(),
    val currentSuggestion: Suggestion? = null,

    var error: String? = null,
    val isApproved: Boolean = false,
    val isRejected: Boolean = false,
    val isLoading: Boolean = false

)

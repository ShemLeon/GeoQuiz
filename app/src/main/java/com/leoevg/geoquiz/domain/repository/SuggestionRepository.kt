package com.leoevg.geoquiz.domain.repository

import com.leoevg.geoquiz.data.model.Suggestion

interface SuggestionRepository{
    fun createSuggestion(country: String, imageUrl: String)
    suspend fun loadSuggestions(): List<Suggestion>
    fun deleteSuggestion(id: String)
}
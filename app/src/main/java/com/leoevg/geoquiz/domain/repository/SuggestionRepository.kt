package com.leoevg.geoquiz.domain.repository

interface SuggestionRepository {
    fun createSuggestion(country: String, imageUrl: String)
}
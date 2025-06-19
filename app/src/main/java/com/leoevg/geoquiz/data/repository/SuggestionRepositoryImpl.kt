package com.leoevg.geoquiz.data.repository

import android.R.attr.country
import com.google.firebase.database.FirebaseDatabase
import com.leoevg.geoquiz.data.model.Suggestion
import com.leoevg.geoquiz.data.util.CHILD_COUNTRY_SUGGESTION
import com.leoevg.geoquiz.data.util.CHILD_IMAGE_URL_SUGGESTION
import com.leoevg.geoquiz.data.util.NODE_SUGGESTIONS
import com.leoevg.geoquiz.data.util.getDataOnce
import com.leoevg.geoquiz.domain.repository.SuggestionRepository
import java.util.UUID

class SuggestionRepositoryImpl: SuggestionRepository{
    override fun createSuggestion(country: String, imageUrl: String) {
        val suggestion = Suggestion(
            id = UUID.randomUUID().toString(),
            country = country,
            imageUrl = imageUrl
        )
        FirebaseDatabase.getInstance().reference
            .child(NODE_SUGGESTIONS).child(suggestion.id)
            .setValue(suggestion.getAsMap())
    }

    override suspend fun loadSuggestions(): List<Suggestion> {
        //смотрим что нам понапредлогали
        val suggestionsSnapshot =
            FirebaseDatabase.getInstance().reference
            .child(NODE_SUGGESTIONS).getDataOnce()
        val suggestionsList = mutableListOf<Suggestion>()
        suggestionsSnapshot.children.forEach { it
            val country = it.child(CHILD_COUNTRY_SUGGESTION).value.toString()
            val imageUrl = it.child(CHILD_IMAGE_URL_SUGGESTION).value.toString()
            suggestionsList.add(Suggestion(it.key.toString(), country, imageUrl))
        }
        return suggestionsList
    }

    override fun deleteSuggestion(id: String) {
        FirebaseDatabase.getInstance().reference
            .child(NODE_SUGGESTIONS).child(id).removeValue()
    }
}

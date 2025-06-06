package com.leoevg.geoquiz.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.leoevg.geoquiz.data.model.Suggestion
import com.leoevg.geoquiz.data.util.NODE_SUGGESTIONS
import com.leoevg.geoquiz.domain.repository.SuggestionRepository
import java.util.UUID

class SuggestionRepositoryImpl: SuggestionRepository{
    override fun createSuggestion(country: String, imageUrl: String) {
        val suggestion = Suggestion(
            id = UUID.randomUUID().toString(),
            country = country,
            imageUrl = imageUrl
        )
        FirebaseDatabase.getInstance().reference.child(NODE_SUGGESTIONS).child(suggestion.id).setValue(suggestion.getAsMap())
    }


}

package com.leoevg.geoquiz.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.leoevg.geoquiz.data.model.Suggestion
import com.leoevg.geoquiz.data.model.TypeGame
import com.leoevg.geoquiz.data.util.CHILD_COUNTRY_SUGGESTION
import com.leoevg.geoquiz.data.util.CHILD_IMAGE_URL_SUGGESTION
import com.leoevg.geoquiz.data.util.CHILD_PICTURES
import com.leoevg.geoquiz.data.util.CHILD_QUESTIONS
import com.leoevg.geoquiz.data.util.CHILD_RIGHT
import com.leoevg.geoquiz.data.util.CHILD_TYPE_GAME
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

    override suspend fun applySuggestion(
        suggestion: Suggestion,
        typeGamesNames: List<String>
    ) {
        val typeGamesSnapshot = FirebaseDatabase.getInstance().reference.child(CHILD_QUESTIONS).child(CHILD_TYPE_GAME).getDataOnce()
        typeGamesNames.forEach { currentTypeGameName ->
            typeGamesSnapshot.child(currentTypeGameName).applySuggestionForTypeGame(suggestion)
        }
    }

    private fun DataSnapshot.applySuggestionForTypeGame(suggestion: Suggestion) {
        val currentTypeGame = this.key ?: return
        this.children.forEach { questionSnapshot ->
            val currentQuestionKey = questionSnapshot.key
            if (currentQuestionKey == null) return@forEach

            if (currentQuestionKey != suggestion.country && questionSnapshot.child(CHILD_RIGHT).value?.toString() != suggestion.country)
                return@forEach

            val nextPictureId = questionSnapshot.child(CHILD_PICTURES).childrenCount + 1
            FirebaseDatabase.getInstance().reference.child(CHILD_QUESTIONS).child(CHILD_TYPE_GAME)
                .child(currentTypeGame).child(currentQuestionKey).child(CHILD_PICTURES).child(nextPictureId.toString()).setValue(suggestion.imageUrl)
            return
        }
    }
}

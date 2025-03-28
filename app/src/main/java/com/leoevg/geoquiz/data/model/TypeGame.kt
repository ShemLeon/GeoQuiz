package com.leoevg.geoquiz.data.model

import com.leoevg.geoquiz.R


class TypeGame(
    var typeGameId: Int,
    var typeGameName: String,
    var typeGameImg: Int
)

val typeGames = listOf(
    TypeGame(1, "Easy", R.drawable.card_easy),
    TypeGame(2, "Hard", R.drawable.card_hard),
    TypeGame(3, "Nightmare", R.drawable.card_nigthmare),
    TypeGame(4, "Clothing", R.drawable.card_clothing),
    TypeGame(5, "History", R.drawable.card_history),
    TypeGame(6, "Sport", R.drawable.card_sport)
)
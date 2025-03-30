package com.leoevg.geoquiz.data.model

import com.leoevg.geoquiz.R


class TypeGame(
    var typeGameId: Int,
    var typeGameNameResId: Int,
    var typeGameImg: Int
)

val typeGames = listOf(
    TypeGame(1, R.string.easy, R.drawable.card_easy),
    TypeGame(2, R.string.hard, R.drawable.card_hard),
    TypeGame(3, R.string.nightmare, R.drawable.card_nigthmare),
    TypeGame(4, R.string.clothing, R.drawable.card_clothing),
    TypeGame(5, R.string.history, R.drawable.card_history),
    TypeGame(6, R.string.sport, R.drawable.card_sport)
)

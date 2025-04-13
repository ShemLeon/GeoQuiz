package com.leoevg.geoquiz.data.model
import com.leoevg.geoquiz.R

data class TypeGame(
    var typeGameId: Int,
    var typeGameNameResId: Int,
    var typeGameImg: Int,
    var typeGameDescResId: Int
)

val typeGames = listOf(
    TypeGame(1, R.string.easy, R.drawable.card_easy, R.string.easyDesc),
    TypeGame(2, R.string.hard, R.drawable.card_hard, R.string.hardDesc),
    TypeGame(3, R.string.nightmare, R.drawable.card_nigthmare,R.string.nightmareDesc),
    TypeGame(4, R.string.clothing, R.drawable.card_clothing,R.string.clothingDesc),
    TypeGame(5, R.string.history, R.drawable.card_history,R.string.historyDesc),
    TypeGame(6, R.string.sport, R.drawable.card_sport,R.string.sportDesc)
)

package com.leoevg.geoquiz.data.model
import com.leoevg.geoquiz.R

data class TypeGame(
    var typeGameId: Int,
    var typeGameNameResId: Int,
    var typeGameImg: Int,
    var typeGameDescResId: Int,
    var typeGameQuestionCost: Double
)

val typeGames = listOf(
    TypeGame(1, R.string.easy, R.drawable.card_easy, R.string.easyDesc, 1.0),
    TypeGame(2, R.string.hard, R.drawable.card_hard, R.string.hardDesc, 2.0),
    TypeGame(3, R.string.nightmare, R.drawable.card_nigthmare,R.string.nightmareDesc,3.0),
    TypeGame(4, R.string.clothing, R.drawable.card_clothing,R.string.clothingDesc, 2.0),
    TypeGame(5, R.string.history, R.drawable.card_history,R.string.historyDesc, 2.0),
    TypeGame(6, R.string.sport, R.drawable.card_sport,R.string.sportDesc, 2.0)
)

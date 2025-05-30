package com.leoevg.geoquiz.data.model
import com.leoevg.geoquiz.R
import kotlinx.serialization.Serializable

@Serializable
data class TypeGame(
    val typeGameId: Int,
    val typeGameNameResId: Int,
    val typeGameImg: Int,
    val typeGameDescResId: Int,
    val typeGameQuestionCost: Int
)

val typeGames = listOf(
    TypeGame(1, R.string.easy,      R.drawable.card_easy,       R.string.easyDesc,      1),
    TypeGame(2, R.string.hard,      R.drawable.card_hard,       R.string.hardDesc,      2),
    TypeGame(3, R.string.nightmare, R.drawable.card_nigthmare,  R.string.nightmareDesc, 3),
    TypeGame(4, R.string.clothing,  R.drawable.card_clothing,   R.string.clothingDesc,  2),
    TypeGame(5, R.string.history,   R.drawable.card_history,    R.string.historyDesc,   2),
    TypeGame(6, R.string.sport,     R.drawable.card_sport,      R.string.sportDesc,     2)
)
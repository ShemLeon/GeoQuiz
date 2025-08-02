package com.leoevg.geoquiz.ui.components
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoevg.geoquiz.data.model.TypeGame
import com.leoevg.geoquiz.ui.theme.GeoQuizTheme
import com.leoevg.geoquiz.data.model.typeGames
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType

@Composable
fun GameModelItem(
    modifier: Modifier = Modifier,
    typeGame: TypeGame,
    isSelected: Boolean,
    onItemClicked: ()->Unit
){
    val hapticFeedback = LocalHapticFeedback.current

    Surface(
        modifier = modifier,
        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
        shape = RoundedCornerShape(25.dp),
        border = if (!isSelected) BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)) else null,
        onClick = {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
            onItemClicked()
        }
    ){
        Box(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 18.dp
            )
        )
            {
                Text(
                    text = stringResource(typeGame.typeGameNameResId),
                    color = if (isSelected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
            }
    }
}

@Composable
@Preview(showBackground = true)
fun GameModelItemPreview() {
    GeoQuizTheme(
        darkTheme = false
    ) {
        GameModelItem(
            typeGame = typeGames[0], // "Easy" режим
            isSelected = true,
            onItemClicked = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun GameModelItemDarkPreview() {
    GeoQuizTheme(
        darkTheme = true
    ) {
        GameModelItem(
            typeGame = typeGames[0], // "Easy" режим
            isSelected = true,
            onItemClicked = {}
        )
    }
}
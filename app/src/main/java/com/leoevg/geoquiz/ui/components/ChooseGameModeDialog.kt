package com.leoevg.geoquiz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.data.model.TypeGame
import com.leoevg.geoquiz.data.model.typeGames
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.leoevg.geoquiz.ui.theme.DarkGrey
import kotlin.Unit

@Composable
fun ChooseGameModeDialog(
    modifier: Modifier = Modifier,
    onModesSelected: (List<TypeGame>) -> Unit = {}
) {
    var selectedTypeGamesList by remember { mutableStateOf<List<TypeGame>>(emptyList()) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Dialog(
            onDismissRequest = { onModesSelected(selectedTypeGamesList) },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = true)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(300.dp)
                    .height(400.dp)
                    .padding(16.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(17.dp))
                    .padding(10.dp)
            ) {
                LazyColumn {
                    items(typeGames) { typeGame ->
                        TypeGameListItem(
                            typeGame = typeGame,
                            isSelected = typeGame in selectedTypeGamesList,
                        ) {
                            if (typeGame in selectedTypeGamesList) selectedTypeGamesList -= typeGame
                            else selectedTypeGamesList += typeGame
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TypeGameListItem(
    modifier: Modifier = Modifier,
    typeGame: TypeGame,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .background(DarkGrey, RoundedCornerShape(15.dp))
            .padding(15.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.Green
            )
        }
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = stringResource(typeGame.typeGameNameResId)
        )
    }
}
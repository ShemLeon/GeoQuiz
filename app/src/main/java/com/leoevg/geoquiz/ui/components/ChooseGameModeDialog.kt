package com.leoevg.geoquiz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.ui.components.LoadingDialog
import com.leoevg.geoquiz.ui.theme.GeoQuizTheme
import com.leoevg.geoquiz.data.model.TypeGame
import com.leoevg.geoquiz.data.model.typeGames
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlin.Unit

@Composable
fun ChooseGameModeDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    onModesSelected: (List<TypeGame>) -> Unit = {}
) {
//    var selectedTypeGamesList by remember { mutableStateListOf<TypeGame>() }
    var selectedTypeGamesList by remember { mutableStateListOf<TypeGame>() }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(dismissOnBackPress = false,
                dismissOnClickOutside = false)
        ) {
            LazyColumn {
                items(typeGames) { typeGame ->
                    TypeGameListItem(
                        typeGame = typeGame,
                        isSelected = typeGame in selectedTypeGamesList,
                    ) {
                        if (typeGame in selectedTypeGamesList) selectedTypeGamesList.remove(typeGame)
                        else selectedTypeGamesList.add(typeGame)
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
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
        ) {
            Text(text = stringResource(typeGame.typeGameNameResId))
            Text(text = stringResource(typeGame.typeGameDescResId))
        }
    }
}
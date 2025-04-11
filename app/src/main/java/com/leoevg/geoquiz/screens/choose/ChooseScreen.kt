package com.leoevg.geoquiz.screens.choose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.data.model.typeGames
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.ui.components.GameModelItem

@Composable
fun ChooseScreen(navigate: (NavigationPaths) -> Unit){
    var selectedTypeGame by remember { mutableStateOf(typeGames[0]) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = stringResource(R.string.choose_game_mode),
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 50.dp)
                    .fillMaxWidth()
            )
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            // shape = RoundedCornerShape(15.dp)
        ){
            items(typeGames){

                GameModelItem(
                    typeGame = it,
                    isSelected = selectedTypeGame.typeGameId==it.typeGameId
                ) {
                    selectedTypeGame = it
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ChooseScreenPreview(){
    ChooseScreen {  }
}
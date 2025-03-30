package com.leoevg.geoquiz.screens.choose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.data.model.TypeGame
import com.leoevg.geoquiz.data.model.typeGames
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.screens.login.LoginScreen

@Composable
fun ChooseScreen(navigate: (NavigationPaths) -> Unit){
    var selectTypeGame by remember { mutableStateOf(typeGames[0]) }




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
                modifier = Modifier.padding(top = 50.dp)
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ChooseScreenPreview(){
    ChooseScreen {  }
}
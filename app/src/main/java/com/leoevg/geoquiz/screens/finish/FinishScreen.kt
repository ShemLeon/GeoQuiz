package com.leoevg.geoquiz.screens.finish

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.screens.quiz.QuizScreen

@Composable
fun FinishScreen(navigate: (NavigationPaths) -> Unit){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){

        }
    }
}

@Composable
@Preview(showBackground = true)
fun QuizScreenPreview(){
    FinishScreen {  }
}
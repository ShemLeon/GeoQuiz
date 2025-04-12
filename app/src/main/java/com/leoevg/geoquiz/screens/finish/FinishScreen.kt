package com.leoevg.geoquiz.screens.finish

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.screens.quiz.QuizScreen
import com.leoevg.geoquiz.ui.theme.Blue

@Composable
fun FinishScreen(navigate: (NavigationPaths) -> Unit){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Icon(
                painter = painterResource(R.drawable.home_button),
                contentDescription = "home button icon",
                modifier = Modifier.size(24.dp)
            )
            Text(
                stringResource(R.string.quiz_results),
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal
            )
            Icon(
                painter = painterResource(R.drawable.gear_button),
                contentDescription = "home button icon",
                modifier = Modifier.size(24.dp)
            )


        }
        Text(
            stringResource(R.string.congratulations),
            modifier = Modifier.padding(top = 40.dp),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold

        )
        Text(
            stringResource(R.string.your_score_is),
            modifier = Modifier.padding(top = 20.dp),
            fontSize = 44.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            stringResource(R.string.number_points),
            modifier = Modifier.padding(top = 40.dp),
            fontSize = 140.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            stringResource(R.string.points),
            modifier = Modifier.padding(top = 10.dp),
            fontSize = 44.sp,
            fontWeight = FontWeight.Normal
        )

// Finish
        Button(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.99f)
                .padding(bottom = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            ),
            shape = RoundedCornerShape(15.dp),
            onClick = {

            }
        ) {
            Text(
                stringResource(R.string.finish),
                fontSize = 30.sp,
                fontWeight = FontWeight.Normal,

                )
        }

    }

}

@Composable
@Preview(showBackground = true)
fun QuizScreenPreview(){
    FinishScreen {  }
}
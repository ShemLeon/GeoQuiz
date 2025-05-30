package com.leoevg.geoquiz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.data.model.AnswerOption
import com.leoevg.geoquiz.screens.quiz.QuizScreen
import com.leoevg.geoquiz.ui.theme.Blue
import com.leoevg.geoquiz.ui.theme.GeoQuizTheme

@Composable
fun AnswerOptionItem(
    modifier: Modifier = Modifier,
    answerOption: AnswerOption,
    isSelected: Boolean = false,
    isAnswerRight: Boolean? = null,
    onClick: () -> Unit = {}
){
    val answerResultIndicationColor = if (isAnswerRight == true) Color.Green else Color.Red
    Row (
        modifier = modifier
            .border(
                2.dp,
                if (isAnswerRight != null) answerResultIndicationColor
                else if (isSelected) MaterialTheme.colorScheme.primary
                else Color.Transparent,
                RoundedCornerShape(15.dp)
            )
            .background(color = Color.Transparent)
            .padding(horizontal = 6.dp, vertical = 5.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,

    ){
        Icon(
            painter = painterResource(R.drawable.answer_option_logo),
            contentDescription = "answer opt icon_button",
            modifier = Modifier.size(14.dp),
            tint = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = answerOption.optAnswer,
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(start = 2.dp),
        )
    }
}


@Composable
@Preview(showBackground = true)
fun AnswerOptionItemPreview(){
    GeoQuizTheme (
        darkTheme = false
    ){
       AnswerOptionItem(
        answerOption = AnswerOption(0, "TestCountry"),
        isSelected = true
    )
    }
}

@Composable
@Preview(showBackground = true)
fun AnswerOptionItemDarkPreview(){
    GeoQuizTheme (
        darkTheme = true
    ){
        AnswerOptionItem(
            answerOption = AnswerOption(0, "TestCountry"),
            isSelected = true
        )
    }
}
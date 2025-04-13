package com.leoevg.geoquiz.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.leoevg.geoquiz.data.model.AnswerOption
import com.leoevg.geoquiz.screens.quiz.QuizScreen

@Composable
fun AnswerOptionItem(
    modifier: Modifier = Modifier,
    answerOption: AnswerOption,
    isSelected: Boolean = false
){
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        // modifier = Modifier.fillMaxSize()
    ){
        Icon(
            painter = painterResource(R.drawable.answer_option_logo),
            contentDescription = "answer opt icon_button",
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = answerOption.optAnswer,
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(start = 10.dp)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun AnswerOptionItemPreview(){
    AnswerOptionItem(answerOption = AnswerOption(0, "TestCountry"))
}
package com.leoevg.geoquiz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.data.model.AnswerOption
import com.leoevg.geoquiz.ui.theme.GeoQuizTheme
import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle

@Composable
fun AnswerOptionItem(
    modifier: Modifier = Modifier,
    answerOption: AnswerOption,
    isSelected: Boolean = false,
    isAnswerRight: Boolean? = null,
    onClick: () -> Unit = {}
){
    val answerResultIndicationColor = if (isAnswerRight == true) Color.Green else Color.Red
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                2.dp,
                if (isAnswerRight != null) answerResultIndicationColor
                else if (isSelected) MaterialTheme.colorScheme.primary
                else Color.Transparent,
                RoundedCornerShape(15.dp)
            )
            .clip(RoundedCornerShape(15.dp))
            .background(
                color = Color.Transparent
            )

            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 8.dp),

        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            painter = painterResource(R.drawable.answer_option_logo),
            contentDescription = "answer opt icon_button",
            modifier = Modifier
                .size(38.dp)
            ,
            tint = if (isAnswerRight != null) answerResultIndicationColor
                   else if (isSelected) MaterialTheme.colorScheme.primary
                   else MaterialTheme.colorScheme.onBackground
        )
        BasicText(
            text = answerOption.optAnswer,
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth(),
            maxLines = 1,
            autoSize = TextAutoSize.StepBased(
                minFontSize = 18.sp,
                maxFontSize = 28.sp
            ),
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Normal
            )
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
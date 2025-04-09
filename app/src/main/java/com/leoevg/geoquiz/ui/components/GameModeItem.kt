package com.leoevg.geoquiz.ui.components

import android.widget.AdapterView.OnItemSelectedListener
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.data.model.TypeGame

@Composable
fun GameModelItem(
    modifier: Modifier=Modifier,
    typeGame: TypeGame,
    isSelected: Boolean,
    onItemClicked: ()->Unit
){
    Box (
        modifier = modifier
            .background(if (isSelected) Color.Blue else Color.White, shape = RoundedCornerShape(3.dp))
            .padding(5.dp)
            .clickable { onItemClicked() }
    ){
        Text(
            stringResource(typeGame.typeGameNameResId),
            color = if (isSelected) Color.White else Color.Black
            )

    }

}
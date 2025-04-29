package com.leoevg.geoquiz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.leoevg.geoquiz.ui.theme.Blue

@Composable
fun LoadingDialog(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onDismiss: () -> Unit = {}
) {
    if (isLoading) {
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
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(16.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(17.dp))
                        .padding(10.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(150.dp)
                            .padding(16.dp),
                        color = Blue
                    )
                }
            }
        }
    }
}
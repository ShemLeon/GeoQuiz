package com.leoevg.geoquiz.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.ui.theme.GeoQuizTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoevg.geoquiz.ui.components.LoadingDialog
import kotlin.String
import com.leoevg.geoquiz.ui.theme.DarkGreen
import com.leoevg.geoquiz.ui.theme.DarkRed


@Composable
fun AdminScreen(
    navigate: (NavigationPaths) -> Unit
){
    val viewModel: AdminScreenViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LoadingDialog(isLoading = state.isLoading)

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        AdminScreenContent(
            modifier = Modifier.padding(it),
            navigate = navigate,
            state = state,
            onEvent = viewModel::onEvent,
            showSnackBar = { textLabel: String, actionLabel: String ->
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = textLabel,
                        actionLabel = actionLabel
                    )
                }
            }
        )
    }

}



@Composable
fun AdminScreenContent(
    modifier: Modifier = Modifier,
    state: AdminScreenState = AdminScreenState(),
    onEvent: (AdminScreenEvent) -> Unit,
    navigate: (NavigationPaths) -> Unit,
    showSnackBar: (String, String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "AdminScreen"
        )

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(bottom = 10.dp)
                .height(90.dp)
        ){
// Approve
            Button(
                modifier = Modifier
                    .fillMaxHeight(fraction = 0.9f)
                    .fillMaxWidth(fraction = 0.6f),
                //              .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkGreen
                ),
                contentPadding = PaddingValues(vertical = 15.dp),
                shape = RoundedCornerShape(25.dp),
                onClick = {
                    showSnackBar("Fields cannot be empty", "Close")
                    onEvent(AdminScreenEvent.ApproveBtnClicked)
                }
            ) {
                Text(
                    stringResource(R.string.approve),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }


 // Reject
            Button(
                modifier = Modifier
                    .fillMaxHeight(fraction = 0.9f)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkRed
                ),
                contentPadding = PaddingValues(vertical = 10.dp),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    showSnackBar("Fields cannot be empty", "Close")
                    onEvent(AdminScreenEvent.RejectBtnClicked)
                }
            ) {
                Text(
                    stringResource(R.string.reject),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
    }




    }
}

@Composable
@Preview(showBackground = true, uiMode = 1)
fun AdminScreenPreview(){
    GeoQuizTheme(
        darkTheme = false
    ) {
        AdminScreenContent(
            modifier = Modifier,
            state = AdminScreenState(),
            onEvent = {},
            navigate = {},
            showSnackBar = { textLabel: String, actionLabel: String -> }
        )
    }
}
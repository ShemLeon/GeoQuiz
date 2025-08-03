package com.leoevg.geoquiz.screens.rate

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.ui.theme.GeoQuizTheme
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.leoevg.geoquiz.ui.components.LoadingDialog
import androidx.compose.ui.platform.LocalConfiguration


@Composable
fun RateScreen(
    navigate: (NavigationPaths) -> Unit,
    popBackStack: () -> Unit
) {
    val viewModel: RateScreenViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    // 2. запросить у пользователя разрешение на галерею. через Activity For Result Launcher -
    // объект/диалоговое окно, которое запустим для получения результата. и передать 2 аргумента:
    // 1- контракт на действие - requestPermissionLauncher
    // 2 - что делать с результатом контракта
    // 3 - контракт на получение одного изображения - pickImageLauncher
    val context = LocalContext.current
    val pickImageLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            viewModel.onEvent(RateScreenEvent.ImagePicked(uri))
            viewModel.onEvent(RateScreenEvent.CountryBottomSheetRequested)
        } ?: run {
            Toast.makeText(context, context.getString(R.string.failed_to_get_image), Toast.LENGTH_LONG).show()
        }
    }
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            pickImageLauncher.launch("image/*")
        } else {
            Toast.makeText(context, context.getString(R.string.gallery_permission_needed), Toast.LENGTH_LONG).show()
        }
    }

    val checkGalleryPermissionAndPickImage = {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) Manifest.permission.READ_MEDIA_IMAGES else Manifest.permission.READ_EXTERNAL_STORAGE
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
            requestPermissionLauncher.launch(permission)
        else
            pickImageLauncher.launch("image/*")
    }

    LoadingDialog(isLoading = state.isLoading)
    RateScreenContent(
        navigate = navigate,
        state = state,
        onEvent = viewModel::onEvent,
        checkGalleryPermissionAndPickImage = checkGalleryPermissionAndPickImage
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RateScreenContent(
    modifier: Modifier = Modifier,
    state: RateScreenState = RateScreenState(),
    onEvent: (RateScreenEvent) -> Unit,
    navigate: (NavigationPaths) -> Unit,
    checkGalleryPermissionAndPickImage: () -> Unit
){
    if (state.countryBottomSheetRequested) {
        ModalBottomSheet(
            onDismissRequest = { onEvent(RateScreenEvent.CountryBottomSheetDismissed) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.country_name)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    value = state.countryName?:"",
                    onValueChange = {
                        // it - новое значение введенное юзером, евентом передаем его в viewModel
                        onEvent(RateScreenEvent.CountryNameChanged(it))
                    },
                    placeholder = { Text(stringResource(R.string.country_name)) },
                    shape = RoundedCornerShape(15.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Transparent,
                        disabledContainerColor = MaterialTheme.colorScheme.secondary,
                        focusedContainerColor = MaterialTheme.colorScheme.secondary,
                        errorContainerColor = MaterialTheme.colorScheme.secondary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                    )
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(top = 10.dp),
                    onClick = { onEvent(RateScreenEvent.SaveSuggestionBtnClicked) }
                ) {
                    Text(text = stringResource(R.string.save))
                }
            }
        }
    }

    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            stringResource(R.string.rate_us),
            modifier = Modifier
                .padding(top = 60.dp),
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
// Stars
        RatingViewStateful(
            maxRating = 5,
            onRatingChanged = { stars ->
                onEvent(RateScreenEvent.StarsChanged(stars))
            }
        )

// Bottom
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
// Btn give you content
            state.pickedImageUri?.let {
                // если изображение выбрано - отображаем его
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it)
                        .build(),
                    contentDescription = "Picked image",
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                )
                // действие выбора
            } ?: run {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.6f)
                        .padding(bottom = 20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    shape = RoundedCornerShape(15.dp),
                    onClick = { checkGalleryPermissionAndPickImage() }
                ) {
                    Box(
                        modifier = Modifier.height(150.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CameraAlt,
                            contentDescription = "camera icon",
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier
                                .size(150.dp)
                        )
                        Text(
                            stringResource(R.string.send_content).uppercase(),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }
            }

// btn back
            Button(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.99f)
                    .padding(bottom = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    navigate(NavigationPaths.Choose)
                }
            ) {
                Text(
                    stringResource(R.string.go_to_quizzes),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Composable
fun RatingViewStateful(
    maxRating: Int = 5,
    iconSizeRatio: Float = 0.15f,
    onRatingChanged: (Int) -> Unit = {}
) {
    val rating = remember { mutableStateOf(0) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val padding = 5.dp
    val availableWidth = screenWidth - (padding * 2)
    val iconSize = screenWidth * iconSizeRatio

    Row(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 5.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(maxRating) { index ->
            Icon(
                imageVector = if (index < rating.value) {
                    Icons.Filled.Star
                } else {
                    Icons.Filled.StarBorder
                },
                contentDescription = "Rate ${index + 1}",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(iconSize)
                    .clickable {
                        rating.value = index + 1
                        onRatingChanged(index + 1)
                    }
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun RateScreenPreview(){
    GeoQuizTheme(
        darkTheme = false
    ) {
        RateScreenContent(
            modifier = Modifier,
            state = RateScreenState(),
            onEvent = {},
            navigate = {},
            checkGalleryPermissionAndPickImage = {}
        )
    }
}

@Composable
@Preview(showBackground = false)
fun RateScreenDarkPreview(){
    GeoQuizTheme(
        darkTheme = true
    ) {
        RateScreenContent(
            modifier = Modifier,
            state = RateScreenState(),
            onEvent = {},
            navigate = {},
            checkGalleryPermissionAndPickImage = {}
        )
    }
}

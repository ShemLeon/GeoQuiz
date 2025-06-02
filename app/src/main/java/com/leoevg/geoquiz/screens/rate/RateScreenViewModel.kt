package com.leoevg.geoquiz.screens.rate

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.leoevg.geoquiz.navigation.NavigationPaths
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class RateScreenViewModel @Inject constructor( ): ViewModel( ){
    // state вьюхи
    private val _state = MutableStateFlow(RateScreenState())
    val state: StateFlow<RateScreenState> = _state

    private var navigateFunction: ((NavigationPaths) -> Unit)? = null
    fun setNavigateFunction(navigate: (NavigationPaths) -> Unit) {
        navigateFunction = navigate
    }

    fun onEvent(event: RateScreenEvent){
        // SOLID
        when(event){
            is RateScreenEvent.StarsChanged -> onStarsSelected(event.selectedStars)
            is RateScreenEvent.ImagePicked -> onImagePicked(event.imageUri)
            RateScreenEvent.SendContentBtnClicked -> onSendContentBtnClicked()
            RateScreenEvent.GoToQuizesBtnClicked -> onGoToQuizesBtnClicked()
        }
    }

    private fun onImagePicked(imageUri: Uri) {
        _state.update { it.copy(pickedImageUri = imageUri) }
    }

    private fun onStarsSelected(stars: Int) {  // Исправить сигнатуру
        _state.update { it.copy(stars = stars) }
    }

    private fun onSendContentBtnClicked() {

    }

    private fun onGoToQuizesBtnClicked(){

    }
}


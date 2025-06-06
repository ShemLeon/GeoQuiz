package com.leoevg.geoquiz.screens.rate

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoevg.geoquiz.domain.repository.FirebaseStorageRepository
import com.leoevg.geoquiz.navigation.NavigationPaths
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RateScreenViewModel @Inject constructor(
    private val firebaseStorageRepository: FirebaseStorageRepository
): ViewModel( ){
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
            RateScreenEvent.GoToQuizzesBtnClicked -> onGoToQuizesBtnClicked()
        }
    }

    private fun onImagePicked(imageUri: Uri, countryName: String = "israel") {
        _state.update { it.copy(pickedImageUri = imageUri) }

        viewModelScope.launch(Dispatchers.IO) {
            val downloadUrl = firebaseStorageRepository.uploadImage(countryName, imageUri)

        }
    }

    private fun onStarsSelected(stars: Int) {  // Исправить сигнатуру
        _state.update { it.copy(stars = stars) }
    }

    private fun onSendContentBtnClicked() {

    }

    private fun onGoToQuizesBtnClicked(){

    }
}


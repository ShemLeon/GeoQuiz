package com.leoevg.geoquiz.domain.repository

import android.net.Uri

interface FirebaseStorageRepository {
    suspend fun uploadImage(countryName: String, imageUri: Uri): String?
}
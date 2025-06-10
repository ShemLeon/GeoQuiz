package com.leoevg.geoquiz.data.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.leoevg.geoquiz.data.util.STORAGE_SUGGESTIONS_FOLDER
import com.leoevg.geoquiz.data.util.getCompletedResult
import com.leoevg.geoquiz.data.util.getTaskUriCompletedResult
import com.leoevg.geoquiz.domain.repository.FirebaseStorageRepository

class FirebaseStorageRepositoryImpl : FirebaseStorageRepository {
    override suspend fun uploadImage(countryName: String, imageUri: Uri): String? {
        try {
            FirebaseStorage
                .getInstance()
                .reference
                .child(STORAGE_SUGGESTIONS_FOLDER)
                .child(countryName)
                .putFile(imageUri)
                .getCompletedResult()
            val downloadUrl = FirebaseStorage
                .getInstance()
                .reference.child(STORAGE_SUGGESTIONS_FOLDER).child(countryName)
                .downloadUrl.getTaskUriCompletedResult()
            return downloadUrl.toString()
        } catch (e: RuntimeException) {
            Log.e("FirebaseStorageRepositoryImpl", "Got RuntimeException: ${e.message}")
            return null
        }
    }
}
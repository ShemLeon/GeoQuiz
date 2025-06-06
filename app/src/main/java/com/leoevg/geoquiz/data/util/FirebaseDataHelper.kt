package com.leoevg.geoquiz.data.util
import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun DatabaseReference.getDataOnce(): DataSnapshot{
    return suspendCancellableCoroutine { continuation ->
        this.addListenerForSingleValueEvent(object: ValueEventListener{
            // получаем "снимок" нашей БД и достаем оттуда все данные
            override fun onDataChange(snapshot: DataSnapshot) {
                continuation.resume(snapshot)
            }

            override fun onCancelled(error: DatabaseError) {
                continuation.resumeWithException(RuntimeException(error.message))
            }
        })
    }
}

suspend fun Task<AuthResult>.getCompletedResult(): AuthResult? {
    // асинхронная таска в Firebase
    return suspendCancellableCoroutine { continuation ->
        this.addOnCompleteListener {
            if (it.isSuccessful)
                continuation.resume(it.result)
            // it - выполненная таски
            else
                continuation.resume(null)
        }
    }
}

suspend fun UploadTask.getCompletedResult(): UploadTask.TaskSnapshot {
    return suspendCancellableCoroutine { continuation ->
        this.addOnCompleteListener {
            if (it.isSuccessful)
                continuation.resume(it.result)
            else
                continuation.resumeWithException(RuntimeException("Failed to upload image to Firebase Storage"))
        }
    }
}

suspend fun Task<Uri>.getCompletedResult(): Uri {
    return suspendCancellableCoroutine { continuation ->
        this.addOnCompleteListener {
            if (it.isSuccessful)
                continuation.resume(it.result)
            else
                continuation.resumeWithException(RuntimeException("Failed to get Firebase Storage downloadUrl for uploaded image"))
        }
    }
}
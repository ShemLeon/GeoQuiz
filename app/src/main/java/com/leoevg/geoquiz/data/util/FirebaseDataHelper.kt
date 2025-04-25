package com.leoevg.geoquiz.data.util
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
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

suspend fun Task<AuthResult>.getCompletedResult(): AuthResult {
    return suspendCancellableCoroutine { continuation ->
        this.addOnCompleteListener {
            continuation.resume(it.result)
        }
    }
}
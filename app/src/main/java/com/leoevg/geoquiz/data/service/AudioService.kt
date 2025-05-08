package com.leoevg.geoquiz.data.service

import android.content.Context
import android.media.MediaPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AudioService @Inject constructor(
    @ApplicationContext private val context: Context
){
    private var mediaPlayer: MediaPlayer? = null

    fun playSound(resId: Int){
        // Освобождаем ресурсы предыдущего проигрывателя, если был включен
        releaseMediaPlayer()
        // Создаем новый проигрыватель
        mediaPlayer = MediaPlayer.create(context, resId)

        mediaPlayer?.setOnCompletionListener {
            // автоматическое освобождение ресурсов после окончания
            releaseMediaPlayer()
        }

        // Начинаем воспроизведение
        mediaPlayer?.start()
    }

    fun releaseMediaPlayer() {
        mediaPlayer?.apply {
            if (isPlaying) {
                stop()
            }
            release()
        }
        mediaPlayer = null
    }
}
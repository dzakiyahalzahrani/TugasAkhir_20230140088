package com.example.tugasakhir_20230140088.utils

import android.content.Context
import android.media.MediaPlayer


object AmbiencePlayer {

    private var mediaPlayer: MediaPlayer? = null

    fun start(context: Context, soundResId: Int) {
        stop()

        mediaPlayer = MediaPlayer.create(context, soundResId).apply {
            isLooping = true
            setVolume(0.6f, 0.6f)
            start()
        }
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
package com.google.micoff

import android.content.Context
import android.media.AudioManager
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MicrophoneViewModel(private val context: Context) : ViewModel() {
    private lateinit var audioManager: AudioManager
    private val _isMuted = MutableStateFlow(false)
    val isMuted = _isMuted.asStateFlow()

    fun initializeAudioManager() {
        audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        _isMuted.value = audioManager.isMicrophoneMute
    }

    fun toggleMicrophoneMute() {
        if (::audioManager.isInitialized) {
            val newMuteState = !_isMuted.value
            audioManager.isMicrophoneMute = newMuteState
            _isMuted.value = newMuteState
        }
    }
}
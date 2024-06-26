package com.google.micoff

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
class MicrophoneControlScreen(private val viewModel: MicrophoneViewModel) {

    @Composable
    fun Content() {
        val permissionState = rememberPermissionState(Manifest.permission.MODIFY_AUDIO_SETTINGS)
        val isMuted by viewModel.isMuted.collectAsState()

        LaunchedEffect(permissionState.status.isGranted) {
            if (permissionState.status.isGranted) {
                viewModel.initializeAudioManager()
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (permissionState.status) {
                is PermissionStatus.Granted -> {
                    Button(
                        onClick = { viewModel.toggleMicrophoneMute() }
                    ) {
                        Text(if (isMuted) "Turn On Mic" else "Turn Off Mic")
                    }
                }
                is PermissionStatus.Denied -> {
                    Button(
                        onClick = { permissionState.launchPermissionRequest() }
                    ) {
                        Text("Request Audio Settings Permission")
                    }
                }
            }
        }
    }
}
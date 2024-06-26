package com.google.micoff

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MicrophoneViewModel(get()) }
}
package com.example.hellokmp

import androidx.compose.ui.window.ComposeUIViewController
import com.example.hellokmp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}
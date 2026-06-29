package com.example.hellokmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.hellokmp.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "HelloKMP",
        ) {
            App()
        }
    }
}

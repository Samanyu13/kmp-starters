package com.example.hellokmp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "HelloKMP",
    ) {
        val batteryManager = remember { BatteryManagerImpl() }
        App(batteryManager)
    }
}

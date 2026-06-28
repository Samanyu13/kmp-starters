package com.example.hellokmp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    val batteryManager = remember { BatteryManagerImpl() }
    App(batteryManager)
}
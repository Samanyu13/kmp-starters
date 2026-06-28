package com.example.hellokmp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun App(batteryManager: BatteryManager) {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val batteryLevel by remember { mutableStateOf(batteryManager.getBatteryLevel()) }
            
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Battery Level:")
                Text(
                    text = if (batteryLevel >= 0) "$batteryLevel%" else "Unknown",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}

package com.example.hellokmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val batteryManager = remember { BatteryManagerImpl(context) }
            App(batteryManager)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    val context = LocalContext.current
    App(BatteryManagerImpl(context))
}

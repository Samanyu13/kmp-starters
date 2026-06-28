package com.example.hellokmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hellokmp.shared.generated.resources.Res
import hellokmp.shared.generated.resources.compose_text
import hellokmp.shared.generated.resources.logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun App(batteryManager: BatteryManager) {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        val batteryValue by remember { mutableStateOf(batteryManager.getBatteryLevel()) }
        Column(
            modifier = Modifier.fillMaxSize().safeDrawingPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    showContent = !showContent
                },
            ) {
                Text("Click Me")
            }

            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.width(200.dp).height(164.dp),
                        painter = painterResource(Res.drawable.logo),
                        contentDescription = null
                    )
                    val text = stringResource(Res.string.compose_text)
                    Text("$text $greeting; Battery -> $batteryValue")
                }
            }
        }
    }
}

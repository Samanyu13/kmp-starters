package com.example.hellokmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hellokmp.dependencies.TestViewModel
import com.example.hellokmp.presentation.CensorViewModel
import com.example.hellokmp.presentation.DataStoreViewModel
import hellokmp.shared.generated.resources.Res
import hellokmp.shared.generated.resources.compose_text
import hellokmp.shared.generated.resources.logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun App() {
    val batteryManager = koinInject<BatteryManager>()
    MaterialTheme {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        label = { Text("Home") },
                        selected = currentDestination?.hierarchy?.any { it.route == "home" } == true,
                        onClick = {
                            navController.navigate("home") {
                                popUpTo(navController.graph.findStartDestination().displayName) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.BatteryFull, contentDescription = null) },
                        label = { Text("Battery") },
                        selected = currentDestination?.hierarchy?.any { it.route == "battery" } == true,
                        onClick = {
                            navController.navigate("battery") {
                                popUpTo(navController.graph.findStartDestination().displayName) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Security, contentDescription = null) },
                        label = { Text("Censor") },
                        selected = currentDestination?.hierarchy?.any { it.route == "censor" } == true,
                        onClick = {
                            navController.navigate("censor") {
                                popUpTo(navController.graph.findStartDestination().displayName) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Save, contentDescription = null) },
                        label = { Text("DataStore") },
                        selected = currentDestination?.hierarchy?.any { it.route == "datastore" } == true,
                        onClick = {
                            navController.navigate("datastore") {
                                popUpTo(navController.graph.findStartDestination().displayName) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = "battery") {
                    BatteryHandler(batteryManager)
                }

                composable(route = "home") {
                    TestRepoHandler()
                }

                composable(route = "censor") {
                    Censor()
                }

                composable(route = "datastore") {
                    DataStoreScreen()
                }
            }
        }
    }
}

@Composable
fun DataStoreScreen() {
    val viewModel = koinViewModel<DataStoreViewModel>()
    val counter by viewModel.counter.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Counter: $counter")
        Button(onClick = { viewModel.increment() }) {
            Text("Increment and save")
        }
    }
}

@Composable
fun Censor() {
    val viewModel = koinViewModel<CensorViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        TextField(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            value = state.uncensoredText,
            onValueChange = { viewModel.onUncensoredTextChange(it) },
            placeholder = { Text("Uncensored Text") }
        )
        Button(onClick = {
            viewModel.censorText()
        }) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(15.dp),
                    strokeWidth = 1.dp,
                    color = Color.White
                )
            } else {
                Text("Censor!")
            }
        }
        state.censoredText?.let {
            Text(it)
        }
        state.errorMessage?.let {
            Text(it.name, color = Color.Red)
        }
    }
}

@Composable
fun TestRepoHandler() {
    val viewModel = koinViewModel<TestViewModel>()
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = viewModel.getHelloWorld()
        )
    }
}

@Composable
fun BatteryHandler(batteryManager: BatteryManager) {
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

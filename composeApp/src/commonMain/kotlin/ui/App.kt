package ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import ui.home.HomeScreen

@Composable
fun App() {
    // Implement TabNavigatorScreen
    Navigator(HomeScreen())
}

package ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import ui.home.HomeTab
import ui.settings.SettingsTab

@Composable
fun App() {
    Navigator(TabNavigatorScreen())
}

class TabNavigatorScreen : Screen {
    @Composable
    override fun Content() {
        TabNavigatorContent()
    }

    @Composable
    fun TabNavigatorContent() {
        TabNavigator(
            HomeTab,
            tabDisposable = {
                TabDisposable(
                    navigator = it,
                    tabs = listOf(HomeTab, SettingsTab)
                )
            }
        ) { tabNavigator ->
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = tabNavigator.current.options.title) }
                    )
                },
                content = {
                    Box(modifier = Modifier.padding(it)) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    BottomNavigation {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(SettingsTab)
                    }
                }
            )
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current

        BottomNavigationItem(
            selected = tabNavigator.current.key == tab.key,
            onClick = { tabNavigator.current = tab },
            icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
        )
    }
}

package ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import ui.settings.changeusername.ChangeUserNameScreen
import utils.getResult

class SettingsScreen : Screen {
    @Composable
    override fun Content() {
        SettingsContent()
    }
}

@Composable
fun SettingsContent() {
    val navigator = LocalNavigator.current
    var userName by remember { mutableStateOf("Test") }
    navigator?.getResult<String>("newname")?.value?.let {
        userName = it
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Hello, $userName",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier.fillMaxWidth()
                .height(80.dp)
                .padding(0.dp, 16.dp),
            onClick = { navigator?.push(ChangeUserNameScreen()) }) {
            Text("Change username")
        }
    }
}

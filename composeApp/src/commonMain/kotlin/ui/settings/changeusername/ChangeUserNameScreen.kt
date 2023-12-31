package ui.settings.changeusername

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import utils.popWithResult

class ChangeUserNameScreen : Screen {
    @Composable
    override fun Content() {
        ChangeUserNameContent()
    }
}

@Composable
fun ChangeUserNameContent() {
    val navigator = LocalNavigator.current
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp, 0.dp)
    ) {
        Text(
            modifier = Modifier.padding(0.dp, 16.dp),
            text = "Change username",
            fontSize = 20.sp
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { newText ->
                text = newText
            }
        )
        Button(modifier = Modifier.fillMaxWidth()
            .height(80.dp)
            .padding(0.dp, 16.dp), onClick = {
            navigator?.popWithResult(text.text, "newname")
        }) {
            Text(text = "Confirm")
        }
    }
}

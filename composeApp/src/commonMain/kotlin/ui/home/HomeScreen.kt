package ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import ui.home.details.HomeDetailsScreen

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        HomeContent()
    }

    @Composable
    fun HomeContent() {
        val navigator = LocalBottomSheetNavigator.current
        val screenModel = rememberScreenModel { HomeScreenModel() }

        LaunchedEffect(Unit) {
            screenModel.getNews()
        }

        val state by screenModel.state.collectAsState()
        when (val result = state) {
            is HomeScreenModel.State.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is HomeScreenModel.State.Success -> {
                LazyColumn(contentPadding = PaddingValues(16.dp, 8.dp)) {
                    items(result.news) {
                        Card(
                            modifier = Modifier.fillMaxWidth()
                                .wrapContentHeight()
                                .padding(0.dp, 8.dp)
                                .clickable { navigator.show(HomeDetailsScreen(it)) },
                            elevation = 4.dp
                        ) {
                            Row(modifier = Modifier.padding(12.dp)) {
                                KamelImage(
                                    modifier = Modifier.width(120.dp)
                                        .height(67.dp),
                                    resource = asyncPainterResource(it.image),
                                    contentDescription = ""
                                )
                                Column(modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 0.dp)) {
                                    Text(
                                        text = it.title,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(Bold.weight)
                                    )
                                    Text(
                                        modifier = Modifier.padding(0.dp, 12.dp, 0.dp, 0.dp),
                                        text = it.subtitle
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

package ui.home.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import models.News

class HomeDetailsScreen(private val news: News) : Screen {
    @Composable
    override fun Content() {
        HomeDetailsContent()
    }

    @Composable
    fun HomeDetailsContent() {
        Column(
            modifier = Modifier.wrapContentHeight()
        ) {
            KamelImage(
                modifier = Modifier.height(200.dp),
                contentScale = ContentScale.Crop,
                resource = asyncPainterResource(news.image),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.padding(16.dp),
                text = news.title,
                fontSize = 16.sp,
                fontWeight = FontWeight(FontWeight.Bold.weight)
            )
            Text(
                modifier = Modifier.padding(16.dp, 0.dp),
                text = news.subtitle
            )
            Divider(color = Color.White, thickness = 16.dp)
        }
    }
}
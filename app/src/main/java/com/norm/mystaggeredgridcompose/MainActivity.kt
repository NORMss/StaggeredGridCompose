package com.norm.mystaggeredgridcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.norm.mystaggeredgridcompose.ui.theme.MyStaggeredGridComposeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val items = (1..100).map {
            ListItem(
                height = Random.nextInt(100, 300).dp,
                color = Color(
                    Random.nextInt(0xFFFFFFF),
                ).copy(alpha = 1f),
            )
        }

        setContent {
            MyStaggeredGridComposeTheme {
                LazyVerticalStaggeredGrid(
//                    columns = StaggeredGridCells.Fixed(2),
                    columns = StaggeredGridCells.Adaptive(128.dp),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalItemSpacing = 16.dp
                ) {
                    items(items) { item ->
                        RandomColorBox(item = item)
                    }
                }
            }
        }
    }
}

data class ListItem(
    val height: Dp,
    val color: Color,
)

@Composable
fun RandomColorBox(
    item: ListItem
) {
    Box(
        modifier = Modifier
            .height(item.height)
            .clip(RoundedCornerShape(10.dp))
            .background(item.color)
    )
}
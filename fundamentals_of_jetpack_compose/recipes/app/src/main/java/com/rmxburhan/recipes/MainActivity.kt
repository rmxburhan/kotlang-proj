package com.rmxburhan.recipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rmxburhan.recipes.data.Recipe
import com.rmxburhan.recipes.data.strawberryCake
import com.rmxburhan.recipes.ui.theme.RecipesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipesTheme {
            }
        }
    }
}

@Composable
fun MainFragment(recipe: Recipe) {
    Box {
        Content(recipe)
        ParallaxToolbar(recipe)
    }
}

@Composable
fun ParallaxToolbar(recipe: Recipe) {
}

@Composable
fun Content(recipe: Recipe) {
}

@Preview(showBackground = true, widthDp = 380)
@Composable
fun PreviewMainFragment() {
    RecipesTheme {
        MainFragment(strawberryCake)
    }
}
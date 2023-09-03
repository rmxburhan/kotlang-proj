package com.rmxburhan.accounting_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rmxburhan.accounting_app.ui.theme.RecipesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    setContent {
            RecipesAppTheme {

            }
        }
    }
}
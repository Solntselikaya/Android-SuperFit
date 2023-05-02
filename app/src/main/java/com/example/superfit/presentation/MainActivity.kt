package com.example.superfit.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.superfit.R
import com.example.superfit.presentation.authorization.AuthorizationScreen
import com.example.superfit.presentation.ui.theme.SuperFitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SuperFit)
        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )
        setContent {
            SuperFitTheme {
                AuthorizationScreen()
            }
        }
    }
}
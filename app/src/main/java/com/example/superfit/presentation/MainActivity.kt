package com.example.superfit.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.superfit.R
import com.example.superfit.navigation.Navigation
import com.example.superfit.presentation.ui.theme.SuperFitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SuperFit)
        // if you want keyboard to squash layout
        // set decorFitsSystemWindows to 'true'
        // !!but then you need to redo layout!!
        // because of spacer you can't see bottom button
        // also you wouldn't need navbars padding anymore
        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )
        setContent {
            SuperFitTheme {
                Navigation()
            }
        }
    }
}
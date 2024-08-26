package com.example.notification_lighting.features.notification_lighting.presentation.screens.lighting_settings_screen.pager_screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun LightingPreviewScreen(navController: NavHostController) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // TODO
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun Preview() {
    LightingPreviewScreen(navController = rememberNavController());
}
package com.example.notification_lighting.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notification_lighting.features.notification_lighting.presentation.screens.settings_screen.SettingsScreen

@Composable
fun SetUpNavigationGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationGraph.ScreenSettings
    ) {
        composable<NavigationGraph.ScreenSettings> {
            SettingsScreen(navController = navHostController)
        }
    }
}
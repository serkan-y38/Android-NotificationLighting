package com.example.notification_lighting.core.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notification_lighting.features.notification_lighting.presentation.screens.exclude_app_screen.ExcludeAppScreen
import com.example.notification_lighting.features.notification_lighting.presentation.screens.lighting_settings_screen.NotificationLightingSettingsScreen
import com.example.notification_lighting.features.notification_lighting.presentation.screens.settings_screen.SettingsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetUpNavigationGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationGraph.ScreenSettings
    ) {
        composable<NavigationGraph.ScreenSettings> {
            SettingsScreen(navController = navHostController)
        }
        composable<NavigationGraph.ScreenNotificationLightingSettings> {
            NotificationLightingSettingsScreen(navController = navHostController)
        }
        composable<NavigationGraph.ScreenExcludeApp> {
            ExcludeAppScreen(navController = navHostController)
        }
    }
}
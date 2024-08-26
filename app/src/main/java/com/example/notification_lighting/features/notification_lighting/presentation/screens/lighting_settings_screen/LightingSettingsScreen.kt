package com.example.notification_lighting.features.notification_lighting.presentation.screens.lighting_settings_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.notification_lighting.features.notification_lighting.presentation.screens.lighting_settings_screen.pager_screens.LightingOptionsScreen
import com.example.notification_lighting.features.notification_lighting.presentation.screens.lighting_settings_screen.pager_screens.LightingPreviewScreen

@Composable
fun NotificationLightingSettingsScreen(navController: NavHostController) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 2 }
    )
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
    ) { page ->
        when (page) {
            0 -> LightingOptionsScreen(navController = navController)
            1 -> LightingPreviewScreen(navController = navController)
        }
    }
}

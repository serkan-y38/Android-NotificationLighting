package com.example.notification_lighting.features.notification_lighting.presentation.screens.lighting_settings_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.notification_lighting.features.notification_lighting.presentation.screens.lighting_settings_screen.pager_screens.LightingOptionsScreen
import com.example.notification_lighting.features.notification_lighting.presentation.screens.lighting_settings_screen.pager_screens.LightingPreviewScreen

@Composable
fun NotificationLightingSettingsScreen(
    navController: NavHostController,
    viewModel: LightingSettingsViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 2 }
    )

    HorizontalPager(
        userScrollEnabled = false,
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 ->
                LightingOptionsScreen(
                    navController = navController,
                    viewModel = viewModel,
                    pagerState = pagerState
                )

            1 ->
                LightingPreviewScreen(
                    viewModel = viewModel,
                    pagerState = pagerState
                )
        }
    }
}

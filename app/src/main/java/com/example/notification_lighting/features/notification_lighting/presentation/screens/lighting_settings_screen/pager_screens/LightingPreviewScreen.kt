package com.example.notification_lighting.features.notification_lighting.presentation.screens.lighting_settings_screen.pager_screens

import android.app.Activity
import android.view.WindowManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.notification_lighting.features.notification_lighting.presentation.screens.common.AnimatedBorder
import com.example.notification_lighting.features.notification_lighting.presentation.screens.lighting_settings_screen.LightingSettingsViewModel

@Composable
fun LightingPreviewScreen(viewModel: LightingSettingsViewModel) {

    val activity = LocalContext.current as Activity
    WindowCompat.setDecorFitsSystemWindows(activity.window, false)

    WindowInsetsControllerCompat(activity.window, activity.window.decorView.rootView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.navigationBars())
        controller.hide(WindowInsetsCompat.Type.statusBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }

    activity.window.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )

    val optionsState = viewModel.state.value

    var cornerRadiusSize by remember {
        mutableIntStateOf(optionsState.screenCornerRadiusSize)
    }

    var cornerBorderThickness by remember {
        mutableIntStateOf(optionsState.borderThickness)
    }

    var animationFrequency by remember {
        mutableIntStateOf(optionsState.animationFrequency)
    }

    var animationDuration by remember {
        mutableIntStateOf(optionsState.animationDuration)
    }

    var iconSize by remember {
        mutableIntStateOf(optionsState.iconSize)
    }

    LaunchedEffect(optionsState) {
        cornerRadiusSize = optionsState.screenCornerRadiusSize
        cornerBorderThickness = optionsState.borderThickness
        animationFrequency = optionsState.animationFrequency
        animationDuration = optionsState.animationDuration
        iconSize = optionsState.iconSize
    }

    val colorList: MutableList<Color> = mutableListOf()
    for (i in 1..animationFrequency) {
        colorList.add(MaterialTheme.colorScheme.primary)
        colorList.add(Color.Black)
    }

    AnimatedBorder(
        gradientColors = colorList,
        shape = RoundedCornerShape(size = cornerRadiusSize.dp),
        borderThickness = cornerBorderThickness.dp,
        animationDuration = animationDuration,
        content = {
            Box(modifier = Modifier.fillMaxSize(), content = {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "img",
                    modifier = Modifier
                        .width(iconSize.dp)
                        .height(iconSize.dp)
                        .align(Alignment.Center)
                )
            })
        }
    )
}
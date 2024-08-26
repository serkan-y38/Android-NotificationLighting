package com.example.notification_lighting.features.notification_lighting.presentation.screens.lighting_settings_screen.pager_screens

import android.app.Activity
import android.view.WindowManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavHostController
import com.example.notification_lighting.features.notification_lighting.presentation.screens.lighting_settings_screen.LightingSettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LightingOptionsScreen(
    navController: NavHostController, viewModel: LightingSettingsViewModel
) {
    val activity = LocalContext.current as Activity
    WindowCompat.setDecorFitsSystemWindows(activity.window, false)

    WindowInsetsControllerCompat(
        activity.window,
        activity.window.decorView.rootView
    ).let { controller ->
        controller.show(WindowInsetsCompat.Type.navigationBars())
        controller.show(WindowInsetsCompat.Type.statusBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }

    activity.window.clearFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )

    val optionsState = viewModel.state.value

    var sliderPositionCornerRadiusSize by remember {
        mutableFloatStateOf(optionsState.screenCornerRadiusSize.toFloat())
    }

    var sliderPositionCornerBorderThickness by remember {
        mutableFloatStateOf(optionsState.borderThickness.toFloat())
    }

    var sliderPositionAnimationFrequency by remember {
        mutableFloatStateOf(optionsState.animationFrequency.toFloat())
    }

    var sliderPositionAnimationDuration by remember {
        mutableFloatStateOf(optionsState.animationDuration.toFloat())
    }

    var sliderPositionIconSize by remember {
        mutableFloatStateOf(optionsState.iconSize.toFloat())
    }

    LaunchedEffect(optionsState) {
        sliderPositionCornerRadiusSize = optionsState.screenCornerRadiusSize.toFloat()
        sliderPositionCornerBorderThickness = optionsState.borderThickness.toFloat()
        sliderPositionAnimationFrequency = optionsState.animationFrequency.toFloat()
        sliderPositionAnimationDuration = optionsState.animationDuration.toFloat()
        sliderPositionIconSize = optionsState.iconSize.toFloat()
    }

    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back"
                )
            }
        }, title = { Text(text = "Options") })
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(all = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Animation Corner Radius Size",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = sliderPositionCornerRadiusSize.toInt().toString()
                        )
                    }
                    Slider(
                        value = sliderPositionCornerRadiusSize, onValueChange = {
                            sliderPositionCornerRadiusSize = it
                            viewModel.setScreenCornerRadiusSize(size = sliderPositionCornerRadiusSize.toInt())
                        }, colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.primary,
                            activeTrackColor = MaterialTheme.colorScheme.primary,
                            inactiveTrackColor = MaterialTheme.colorScheme.secondary,
                        ), valueRange = 16f..64f
                    )
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Animation Border Thickness",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = sliderPositionCornerBorderThickness.toInt().toString()
                        )
                    }
                    Slider(
                        value = sliderPositionCornerBorderThickness, onValueChange = {
                            sliderPositionCornerBorderThickness = it
                            viewModel.setBorderThickness(thickness = sliderPositionCornerBorderThickness.toInt())
                        }, colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.primary,
                            activeTrackColor = MaterialTheme.colorScheme.primary,
                            inactiveTrackColor = MaterialTheme.colorScheme.secondary,
                        ), valueRange = 4f..12f
                    )

                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Animation Frequency",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = sliderPositionAnimationFrequency.toInt().toString()
                        )
                    }
                    Slider(
                        value = sliderPositionAnimationFrequency, onValueChange = {
                            sliderPositionAnimationFrequency = it
                            viewModel.setAnimationFrequency(repetitionNum = sliderPositionAnimationFrequency.toInt())
                        }, colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.primary,
                            activeTrackColor = MaterialTheme.colorScheme.primary,
                            inactiveTrackColor = MaterialTheme.colorScheme.secondary,
                        ), valueRange = 1f..64f
                    )

                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Animation Duration",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = sliderPositionAnimationDuration.toInt().toString()
                        )
                    }

                    Slider(
                        value = sliderPositionAnimationDuration,
                        onValueChange = {
                            sliderPositionAnimationDuration = it
                            viewModel.setAnimationDuration(duration = sliderPositionAnimationDuration.toInt())
                        },
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.primary,
                            activeTrackColor = MaterialTheme.colorScheme.primary,
                            inactiveTrackColor = MaterialTheme.colorScheme.secondary,
                        ),
                        valueRange = 4000f..10000f,
                        steps = ((10000f - 4000f) / 1000).toInt() - 1 // steps = total range / step size - 1
                    )
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Center Icon Size", style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = sliderPositionIconSize.toInt().toString()
                        )
                    }
                    Slider(
                        value = sliderPositionIconSize, onValueChange = {
                            sliderPositionIconSize = it
                            viewModel.setIconSize(size = sliderPositionIconSize.toInt())
                        }, colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.primary,
                            activeTrackColor = MaterialTheme.colorScheme.primary,
                            inactiveTrackColor = MaterialTheme.colorScheme.secondary,
                        ), valueRange = 16f..96f
                    )
                }
            }
        }
    }
}
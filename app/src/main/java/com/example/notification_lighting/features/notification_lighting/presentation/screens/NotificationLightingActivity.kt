package com.example.notification_lighting.features.notification_lighting.presentation.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.palette.graphics.Palette
import com.example.notification_lighting.R
import com.example.notification_lighting.features.notification_lighting.presentation.screens.common.AnimatedBorder
import com.example.notification_lighting.features.notification_lighting.presentation.screens.lighting_settings_screen.LightingSettingsViewModel
import com.example.notification_lighting.features.notification_lighting.presentation.ui.theme.NotificationLightingTheme
import com.example.notification_lighting.features.notification_lighting.service.NotificationListenerService
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationLightingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setShowWhenLocked(true)
        setTurnScreenOn(true)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        WindowInsetsControllerCompat(window, window.decorView.rootView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.navigationBars())
            controller.hide(WindowInsetsCompat.Type.statusBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        val viewModel: LightingSettingsViewModel by viewModels()

        val packageName = intent.getStringExtra(NotificationListenerService.PACKAGE_NAME)
        val icon = packageManager.getApplicationIcon(packageName!!)

        val vibrantColor = getVibrantColor(icon.toBitmap())
        val blackColor = Color.Black

        setContent {
            NotificationLightingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
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

                    if (cornerRadiusSize != 0 && cornerBorderThickness != 0
                        && animationFrequency != 0 && animationDuration != 0 && iconSize != 0) {

                        val colorList: MutableList<Color> = mutableListOf()
                        for (i in 1..animationFrequency) {
                            colorList.add(vibrantColor)
                            colorList.add(blackColor)
                        }
                        AnimatedBorder(
                            gradientColors = colorList,
                            shape = RoundedCornerShape(size = cornerRadiusSize.dp),
                            borderThickness = cornerBorderThickness.dp,
                            animationDuration = animationDuration,
                            content = {
                                Box(modifier = Modifier.fillMaxSize(), content = {
                                    Image(
                                        modifier = Modifier
                                            .width(iconSize.dp)
                                            .height(iconSize.dp)
                                            .align(Alignment.Center),
                                        painter = rememberDrawablePainter(drawable = icon),
                                        contentDescription = "app icon"
                                    )
                                })
                            }
                        )
                        Handler(Looper.getMainLooper()).postDelayed(
                            { finish() },
                            animationDuration.toLong()
                        )
                    }
                }
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun getVibrantColor(bm: Bitmap): Color =
        Color(Palette.from(bm).generate().getVibrantColor(R.color.black))
}


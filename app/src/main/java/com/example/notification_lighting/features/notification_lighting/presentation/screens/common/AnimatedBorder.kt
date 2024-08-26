package com.example.notification_lighting.features.notification_lighting.presentation.screens.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp

@Composable
fun AnimatedBorder(
    gradientColors: List<Color>,
    shape: Shape,
    content: @Composable () -> Unit,
    borderThickness: Dp,
    animationDuration: Int
) {
    val infiniteTransaction = rememberInfiniteTransition(label = "infinite color transaction")

    val degrees by infiniteTransaction.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        label = "infinite colors",
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration, easing = LinearEasing
            ), repeatMode = RepeatMode.Restart
        )
    )
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black),
        shape = shape,
        content = {
            Surface(
                Modifier
                    .fillMaxSize()
                    .padding(borderThickness)
                    .drawWithContent {
                        rotate(degrees = degrees) {
                            drawCircle(
                                brush = Brush.sweepGradient(gradientColors),
                                radius = 2000f,
                                blendMode = BlendMode.SrcIn
                            )
                        }
                        drawContent()
                    }, color = Color.Black, shape = shape, content = { content() })
        })
}
package com.example.notification_lighting.core.navigation

import kotlinx.serialization.Serializable

class NavigationGraph {
    @Serializable
    object ScreenSettings

    @Serializable
    object ScreenNotificationLightingSettings
}
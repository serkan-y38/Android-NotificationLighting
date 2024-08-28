package com.example.notification_lighting.features.notification_lighting.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PackageModel(
    val packageName: String,
    val appName: String,
    val isAppExcluded: Boolean
)

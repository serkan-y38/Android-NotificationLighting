package com.example.notification_lighting.features.notification_lighting.domain.repository.local

import kotlinx.coroutines.flow.Flow

interface LightingDataStoreRepository {
    fun getScreenCornerRadiusSize(): Flow<Int>

    suspend fun setScreenCornerRadiusSize(size: Int)

    fun getAnimationFrequency(): Flow<Int>

    suspend fun setAnimationFrequency(repetitionNum: Int)

    fun getAnimationDuration(): Flow<Int>

    suspend fun setAnimationDuration(duration: Int)

    fun getBorderThickness(): Flow<Int>

    suspend fun setBorderThickness(thickness: Int)

    fun getIconSize(): Flow<Int>

    suspend fun setIconSize(size: Int)

    fun getExcludedPackages(): Flow<String>

    suspend fun setExcludedPackages(packages: String)
}
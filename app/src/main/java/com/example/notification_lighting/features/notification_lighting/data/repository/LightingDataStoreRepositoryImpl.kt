package com.example.notification_lighting.features.notification_lighting.data.repository

import com.example.notification_lighting.features.notification_lighting.data.sources.local.data_store.LightingDataStoreManager
import com.example.notification_lighting.features.notification_lighting.domain.repository.local.LightingDataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LightingDataStoreRepositoryImpl @Inject constructor(
    private val dataStoreManager: LightingDataStoreManager
) : LightingDataStoreRepository {

    override fun getScreenCornerRadiusSize(): Flow<Int> {
        return dataStoreManager.getScreenCornerRadiusSize()
    }

    override suspend fun setScreenCornerRadiusSize(size: Int) {
        dataStoreManager.setScreenCornerRadiusSize(size)
    }

    override fun getAnimationFrequency(): Flow<Int> {
        return dataStoreManager.getAnimationFrequency()
    }

    override suspend fun setAnimationFrequency(repetitionNum: Int) {
        dataStoreManager.setAnimationFrequency(repetitionNum)
    }

    override fun getAnimationDuration(): Flow<Int> {
        return dataStoreManager.getAnimationDuration()
    }

    override suspend fun setAnimationDuration(duration: Int) {
        dataStoreManager.setAnimationDuration(duration)
    }

    override fun getBorderThickness(): Flow<Int> {
        return dataStoreManager.getBorderThickness()
    }

    override suspend fun setBorderThickness(thickness: Int) {
        dataStoreManager.setBorderThickness(thickness)
    }

    override fun getIconSize(): Flow<Int> {
        return dataStoreManager.getIconSize()
    }

    override suspend fun setIconSize(size: Int) {
        dataStoreManager.setIconSize(size)
    }

    override fun getExcludedPackages(): Flow<String> {
        return dataStoreManager.getExcludedPackages()
    }

    override suspend fun setExcludedPackages(packages: String) {
        dataStoreManager.setExcludedPackages(packages)
    }
}
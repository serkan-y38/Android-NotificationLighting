package com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store

import com.example.notification_lighting.features.notification_lighting.domain.repository.local.LightingDataStoreRepository
import javax.inject.Inject

class SetScreenCornerRadiusSizeUseCase @Inject constructor(
    private val repository: LightingDataStoreRepository
) {
    suspend operator fun invoke(size: Int) = repository.setScreenCornerRadiusSize(size = size)
}
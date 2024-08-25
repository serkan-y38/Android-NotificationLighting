package com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store

import com.example.notification_lighting.features.notification_lighting.domain.repository.local.LightingDataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetScreenCornerRadiusSizeUseCase @Inject constructor(
    private val repository: LightingDataStoreRepository
) {
    operator fun invoke(): Flow<Int> = repository.getScreenCornerRadiusSize()
}
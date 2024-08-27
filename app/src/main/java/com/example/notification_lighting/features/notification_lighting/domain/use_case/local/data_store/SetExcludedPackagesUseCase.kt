package com.example.notification_lighting.features.notification_lighting.domain.use_case.local.data_store

import com.example.notification_lighting.features.notification_lighting.domain.repository.local.LightingDataStoreRepository
import javax.inject.Inject

class SetExcludedPackagesUseCase @Inject constructor(
    private val repository: LightingDataStoreRepository
) {
    suspend operator fun invoke(packages: String) =
        repository.setExcludedPackages(packages = packages)
}
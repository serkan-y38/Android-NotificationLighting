package com.example.notification_lighting.features.notification_lighting.di

import com.example.notification_lighting.features.notification_lighting.data.repository.LightingDataStoreRepositoryImpl
import com.example.notification_lighting.features.notification_lighting.data.sources.local.data_store.LightingDataStoreManager
import com.example.notification_lighting.features.notification_lighting.domain.repository.local.LightingDataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideLightingDataStoreRepository(lightingDataStoreManager: LightingDataStoreManager): LightingDataStoreRepository =
        LightingDataStoreRepositoryImpl(lightingDataStoreManager)

}
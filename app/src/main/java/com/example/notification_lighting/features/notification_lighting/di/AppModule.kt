package com.example.notification_lighting.features.notification_lighting.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.notification_lighting.features.notification_lighting.data.repository.LightingDataStoreRepositoryImpl
import com.example.notification_lighting.features.notification_lighting.data.sources.local.data_store.LightingDataStoreManager
import com.example.notification_lighting.features.notification_lighting.domain.repository.local.LightingDataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideLightingDataStore(@ApplicationContext context: Context) = PreferenceDataStoreFactory.create(
        produceFile = { context.preferencesDataStoreFile(LightingDataStoreManager.DATA_STORE_NAME) }
    )

    @Provides
    @Singleton
    fun provideLightingDataStoreManager(dataStore: DataStore<Preferences>) =
        LightingDataStoreManager(dataStore)

    @Singleton
    @Provides
    fun provideLightingDataStoreRepository(lightingDataStoreManager: LightingDataStoreManager): LightingDataStoreRepository =
        LightingDataStoreRepositoryImpl(lightingDataStoreManager)
}
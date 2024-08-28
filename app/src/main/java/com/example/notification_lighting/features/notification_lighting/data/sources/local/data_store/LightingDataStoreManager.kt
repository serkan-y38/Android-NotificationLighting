package com.example.notification_lighting.features.notification_lighting.data.sources.local.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LightingDataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getScreenCornerRadiusSize(): Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[CORNER_RADIUS_SIZE_KEY] ?: 32
        }

    suspend fun setScreenCornerRadiusSize(size: Int) {
        context.dataStore.edit { preferences ->
            preferences[CORNER_RADIUS_SIZE_KEY] = size
        }
    }

    fun getAnimationFrequency(): Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[ANIMATION_FREQUENCY_KEY] ?: 1
        }

    suspend fun setAnimationFrequency(repetitionNum: Int) {
        context.dataStore.edit { preferences ->
            preferences[ANIMATION_FREQUENCY_KEY] = repetitionNum
        }
    }

    fun getAnimationDuration(): Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[ANIMATION_DURATION_KEY] ?: 5000
        }

    suspend fun setAnimationDuration(duration: Int) {
        context.dataStore.edit { preferences ->
            preferences[ANIMATION_DURATION_KEY] = duration
        }
    }

    fun getBorderThickness(): Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[BORDER_THICKNESS_KEY] ?: 6
        }

    suspend fun setBorderThickness(thickness: Int) {
        context.dataStore.edit { preferences ->
            preferences[BORDER_THICKNESS_KEY] = thickness
        }
    }

    fun getIconSize(): Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[ICON_SIZE_KEY] ?: 64
        }

    suspend fun setIconSize(size: Int) {
        context.dataStore.edit { preferences ->
            preferences[ICON_SIZE_KEY] = size
        }
    }

    fun getExcludedPackages(): Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[EXCLUDED_PACKAGES_KEY]
                ?: "[{\"packageName\":\"empty\",\"appName\":\"empty\",\"isAppExcluded\":true}]"
        }

    suspend fun setExcludedPackages(packages: String) {
        context.dataStore.edit { preferences ->
            preferences[EXCLUDED_PACKAGES_KEY] = packages
        }
    }

    companion object {
        private const val DATA_STORE_NAME = "lighting_data_store"
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_NAME)

        private val CORNER_RADIUS_SIZE_KEY = intPreferencesKey("screen_corner_radius_size")
        private val EXCLUDED_PACKAGES_KEY = stringPreferencesKey("excluded_packages")
        private val ICON_SIZE_KEY = intPreferencesKey("icon_size")
        private val BORDER_THICKNESS_KEY = intPreferencesKey("screen_border_size")
        private val ANIMATION_FREQUENCY_KEY = intPreferencesKey("animation_frequency")
        private val ANIMATION_DURATION_KEY = intPreferencesKey("animation_duration")
    }
}
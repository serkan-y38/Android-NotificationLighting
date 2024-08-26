package com.example.notification_lighting.features.notification_lighting.data.sources.local.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LightingDataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    fun getScreenCornerRadiusSize(): Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[CORNER_RADIUS_SIZE_KEY] ?: 32
        }

    suspend fun setScreenCornerRadiusSize(size: Int) {
        dataStore.edit { preferences ->
            preferences[CORNER_RADIUS_SIZE_KEY] = size
        }
    }

    fun getAnimationFrequency(): Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[ANIMATION_FREQUENCY_KEY] ?: 1
        }

    suspend fun setAnimationFrequency(repetitionNum: Int) {
        dataStore.edit { preferences ->
            preferences[ANIMATION_FREQUENCY_KEY] = repetitionNum
        }
    }

    fun getAnimationDuration(): Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[ANIMATION_DURATION_KEY] ?: 5000
        }

    suspend fun setAnimationDuration(duration: Int) {
        dataStore.edit { preferences ->
            preferences[ANIMATION_DURATION_KEY] = duration
        }
    }

    fun getBorderThickness(): Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[BORDER_THICKNESS_KEY] ?: 6
        }

    suspend fun setBorderThickness(thickness: Int) {
        dataStore.edit { preferences ->
            preferences[BORDER_THICKNESS_KEY] = thickness
        }
    }

    fun getIconSize(): Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[ICON_SIZE_KEY] ?: 32
        }

    suspend fun setIconSize(size: Int) {
        dataStore.edit { preferences ->
            preferences[ICON_SIZE_KEY] = size
        }
    }

    companion object {
        const val DATA_STORE_NAME = "lighting_data_store";
        private val CORNER_RADIUS_SIZE_KEY = intPreferencesKey("screen_corner_radius_size")
        private val ICON_SIZE_KEY = intPreferencesKey("icon_size")
        private val BORDER_THICKNESS_KEY = intPreferencesKey("screen_border_size")
        private val ANIMATION_FREQUENCY_KEY = intPreferencesKey("animation_frequency")
        private val ANIMATION_DURATION_KEY = intPreferencesKey("animation_duration")
    }
}